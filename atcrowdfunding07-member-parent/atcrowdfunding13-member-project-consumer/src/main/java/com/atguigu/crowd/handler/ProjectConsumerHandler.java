package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.config.OSSProperties;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.po.ProjectPO;
import com.atguigu.crowd.entity.vo.*;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-23 下午 04:44
 */
@Controller
public class ProjectConsumerHandler {

    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    private Logger logger = LoggerFactory.getLogger(ProjectConsumerHandler.class);

    @RequestMapping("/send/my/order/{orderId}/{projectId}")
    public String sendMyOrder(@PathVariable("orderId")Integer orderId,
                              @PathVariable("projectId")Integer projectId) {
        ResultEntity<String> resultEntity = mySQLRemoteService.sendMyOrderRemote(orderId);

        return "redirect:http://localhost/project/supporter/project/return/"+projectId;
    }

    @RequestMapping("/supporter/project/return/{projectId}")
    public String supporterProjecyReturn(@PathVariable("projectId") Integer projectId,Model model) {
        ResultEntity<List<SupporterAddressReturnVO>> resultEntity =  mySQLRemoteService.getSupporterAddressReturn(projectId);
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            model.addAttribute("supporters", resultEntity.getData());
            model.addAttribute("projectId", projectId);
        }
        return "project-supporter";
    }

    @RequestMapping("/launch/project/page")
    public String getMyLanuchInfo(HttpSession session,Model model) {
        MemberPO loginMember =(MemberPO) session.getAttribute("loginMember");
        Integer memberId = loginMember.getId();
        ResultEntity<MemberLauchInfoVO> resultEntity =  mySQLRemoteService.getMyLanuchInfo(memberId);
        model.addAttribute("launchInfo",resultEntity.getData());
        return "project-launch";

    }

    @ResponseBody
    @RequestMapping("unsubscribe/to/this/product")
    public ResultEntity<String> unsubscribeThisProject(@RequestParam("projectId")Integer projectId,HttpSession session) {
        MemberPO loginMember = (MemberPO) session.getAttribute("loginMember");
        ResultEntity<String> resultEntity = mySQLRemoteService.unsubscribeRemote(projectId, loginMember.getId());
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            return ResultEntity.successWithoutData();
        }
        return ResultEntity.failed("失败");
    }

    @ResponseBody
    @RequestMapping("subscribe/to/this/product")
    public ResultEntity<String> subscribeThisProject(@RequestParam("projectId")Integer projectId,HttpSession session) {
        MemberPO loginMember = (MemberPO) session.getAttribute("loginMember");
        Integer memberId = loginMember.getId();
        logger.info("projectId="+projectId);
        logger.info("memberId="+memberId);
        ResultEntity<String> resultEntity = mySQLRemoteService.subscribeRemote(projectId,memberId );
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            return ResultEntity.successWithoutData();
        }
        return ResultEntity.failed("失败");
    }


    @RequestMapping("/get/all/project")
    public String getAllProject(
            @RequestParam(value = "typeId", required = false) Integer typeId,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "orderType", required = false) Integer orderType,
            Model model) {
        logger.info("typeId="+typeId);
        logger.info("status="+status);
        logger.info("orderType="+orderType);
        ResultEntity<List<ProjectPO>> allProjectWithType = mySQLRemoteService.getAllProjectWithType(typeId, status, orderType);
        if (ResultEntity.SUCCESS.equals(allProjectWithType.getResult())) {
            List<ProjectPO> data = allProjectWithType.getData();
            model.addAttribute("allFindProject", data);
            if(typeId==null){typeId=100;}
            if(status==null){status=100;}
            if(orderType==null){orderType=100;}
            model.addAttribute("typeId", typeId);
            model.addAttribute("status", status);
            model.addAttribute("orderType", orderType);
            model.addAttribute("size", data.size());
        }
        return "project-all";
    }


    @RequestMapping("/get/project/detail/{projectId}")
    public String getProjectDetail(@PathVariable("projectId") Integer projectId, Model model,HttpSession session) {
        ResultEntity<DetailProjectVO> resultEntity = mySQLRemoteService.getDetailProjectVORemote(projectId);
        MemberPO loginMember = (MemberPO) session.getAttribute("loginMember");
        ResultEntity<Integer> resultEntity1 = mySQLRemoteService.isHasFollow(projectId,loginMember.getId());
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            DetailProjectVO detailProjectVO = resultEntity.getData();
            model.addAttribute("detailProjectVO", detailProjectVO);
            model.addAttribute("isFollow", resultEntity1.getData());
        }
        return "project-show-detail";
    }


    @RequestMapping("/create/confirm")
    public String saveConfirm(ModelMap modelMap, HttpSession session, MemberConfirmInfoVO memberConfirmInfoVO) {
        // 1.从 Session域读取之前临时存储的 ProjectVO对象
        ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);
        logger.info("确认项目的内容、回报的project：" + projectVO);
        // 2.如果 projectVO为  null
        if (projectVO == null) {
            throw new RuntimeException(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
        }
        // 3.将确认信息数据设置到 projectVO对象中
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);
        // 4.从 Session域读取当前登录的用户
        MemberPO memberLoginVO = (MemberPO) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);
        Integer memberId = memberLoginVO.getId();
        // 5.调用远程方法保存 projectVO对象
        ResultEntity<String> saveResultEntity = mySQLRemoteService.saveProjectVORemote(projectVO, memberId);
        // 6.判断远程的保存操作是否成功
        String result = saveResultEntity.getResult();
        if (ResultEntity.FAILED.equals(result)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveResultEntity.getMessage());
            return "project-confirm";
        }
        // 7.将临时的 ProjectVO对象从 Session域移除
        session.removeAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);
        // 8.如果远程保存成功则跳转到最终完成页面
        return "redirect:http://localhost:80/project/create/success";
    }


    @ResponseBody
    @RequestMapping("/create/save/return.json")
    public ResultEntity<String> saveReturn(ReturnVO returnVO, HttpSession session) {
        try {
            System.out.println(returnVO);
            // 1.从 session域中读取之前缓存的 ProjectVO对象
            ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);
            // 2.判断 projectVO是否为 null
            if (projectVO == null) {
                return ResultEntity.failed(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
            }
            // 3.从 projectVO对象中获取存储回报信息的集合
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();
            // 4.判断 returnVOList集合是否有效
            if (returnVOList == null || returnVOList.size() == 0) {
                // 5.创建集合对象对 returnVOList进行初始化
                returnVOList = new ArrayList<>();
                // 6.为了让以后能够正常使用这个集合，设置到 projectVO对象中
                projectVO.setReturnVOList(returnVOList);
            }
            // 7.将收集了表单数据的 returnVO对象存入集合
            returnVOList.add(returnVO);
            // 8.把数据有变化的 ProjectVO对象重新存入 Session域，以确保新的数据最终能够存入 Redis
            session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);
            // 9.所有操作成功完成返回成功
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/create/upload/return/picture.json")
    public ResultEntity<String> uploadReturnPicture(
            //接收用户上传的文件
            @RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {
        // 1.执行文件上传
        ResultEntity<String> uploadReturnPicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecert(),
                returnPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                returnPicture.getOriginalFilename());
        // 2.返回上传的结果
        return uploadReturnPicResultEntity;
    }


    @RequestMapping("/create/project/information")
    public String saveProjectBasicInfo(
            //接收除了上传图片之外的其他普通数据
            ProjectVO projectVO,
            //接收上传的头图
            MultipartFile headerPicture,
            //接收上传的详情图片
            List<MultipartFile> detailPictureList,
            //用来将收集了一部分数据的  ProjectVO对象存入  Session域
            HttpSession session,
            //用来在当前操作失败后返回上一个表单页面时携带提示消息
            ModelMap modelMap
    ) throws IOException {

        System.out.println("第一个对象" + projectVO);
        System.out.println("第二个对象" + headerPicture);
        System.out.println("第三个对象" + detailPictureList);
        //一、完成头图上传
        // 1.获取当前 headerPicture对象是否为空
        boolean headerPictureIsEmpty = headerPicture.isEmpty();
        if (headerPictureIsEmpty) {
            // 2.如果没有上传头图则返回到表单页面并显示错误消息
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,
                    CrowdConstant.MESSAGE_HEADER_PIC_EMPTY);
            return "project-launch";
        }
        // 3.如果用户确实上传了有内容的文件，则执行上传
        ResultEntity<String> uploadHeaderPicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecert(),
                headerPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                headerPicture.getOriginalFilename());
        String result = uploadHeaderPicResultEntity.getResult();

        // 4.判断头图是否上传成功
        if (ResultEntity.SUCCESS.equals(result)) {

            // 5.如果成功则从返回的数据中获取图片访问路径
            String headerPicturePath = uploadHeaderPicResultEntity.getData();
            // 6.存入 ProjectVO对象中
            projectVO.setHeaderPicturePath(headerPicturePath);
        } else {
            // 7.如果上传失败则返回到表单页面并显示错误消息
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,
                    CrowdConstant.MESSAGE_HEADER_PIC_UPLOAD_FAILED);
            return "project-launch";
        }
        //二、上传详情图片
        // 1.创建一个用来存放详情图片路径的集合
        List<String> detailPicturePathList = new ArrayList<String>();
        // 2.检查 detailPictureList是否有效
        if (detailPictureList == null || detailPictureList.size() == 0) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,
                    CrowdConstant.MESSAGE_DETAIL_PIC_EMPTY);
            return "project-launch";
        }

        // 3.遍历 detailPictureList集合
        for (MultipartFile detailPicture : detailPictureList) {
            // 4.当前 detailPicture是否为空
            if (detailPicture.isEmpty()) {
                // 5.检测到详情图片中单个文件为空也是回去显示错误消息
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,
                        CrowdConstant.MESSAGE_DETAIL_PIC_EMPTY);
                return "project-launch";
            }
            // 6.执行上传
            ResultEntity<String> detailUploadResultEntity = CrowdUtil.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecert(),
                    detailPicture.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    detailPicture.getOriginalFilename());
            // 7.检查上传结果
            String detailUploadResult = detailUploadResultEntity.getResult();
            if (ResultEntity.SUCCESS.equals(detailUploadResult)) {
                String detailPicturePath = detailUploadResultEntity.getData();
                // 8.收集刚刚上传的图片的访问路径
                detailPicturePathList.add(detailPicturePath);
            } else {
                // 9.如果上传失败则返回到表单页面并显示错误消息
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,
                        CrowdConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILED);
                return "project-launch";
            }
        }
        // 10.将存放了详情图片访问路径的集合存入 ProjectVO中
        projectVO.setDetailPicturePathList(detailPicturePathList);
        //三、后续操作
        // 1.将 ProjectVO对象存入 Session域
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);
        // 2.以完整的访问路径前往下一个收集回报信息的页面
        return "redirect:http://localhost:80/project/return/info/page";
    }


}
