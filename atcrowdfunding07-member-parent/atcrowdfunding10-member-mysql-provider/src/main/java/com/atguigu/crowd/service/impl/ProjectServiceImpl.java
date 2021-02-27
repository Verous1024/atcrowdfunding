package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.po.*;
import com.atguigu.crowd.entity.vo.*;
import com.atguigu.crowd.mapper.*;
import com.atguigu.crowd.service.api.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-23 下午 01:19
 */
@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectPOMapper projectPOMapper;
    @Autowired
    private ProjectItemPicPOMapper projectItemPicPOMapper;
    @Autowired
    private MemberLaunchInfoPOMapper memberLaunchInfoPOMapper;
    @Autowired
    private MemberConfirmInfoPOMapper memberConfirmInfoPOMapper;
    @Autowired
    private ReturnPOMapper returnPOMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class, readOnly = false)
    @Override
    public void saveProject(ProjectVO projectVO, Integer memberId) {
        //1、保存projectPO对象
        ProjectPO projectPO = new ProjectPO();
        BeanUtils.copyProperties(projectVO, projectPO);
        projectPO.setMemberid(memberId);
        projectPO.setCreatedate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //1.1 保存projectPO的同时还需要获取其id
        Integer projectId = projectPOMapper.insertSelective(projectPO);
        //2、保存项目的、分类的关联关系信息
        List<Integer> typeIdList = projectVO.getTypeIdList();
        projectPOMapper.insertTypeRelationship(typeIdList, projectId);
        //3、保存项目中详情图片的路径信息
        List<Integer> tagIdList = projectVO.getTagIdList();
        projectPOMapper.insertTagRelationship(tagIdList, projectId);
        //4、保存项目中详情图片的路径信息
        List<String> detailPicturePathList = projectVO.getDetailPicturePathList();
        projectItemPicPOMapper.insertPathList(projectId, detailPicturePathList);
        //5、保存项目发起人的信息
        MemberLauchInfoVO memberLauchInfoVO = projectVO.getMemberLauchInfoVO();
        MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();
        BeanUtils.copyProperties(memberLauchInfoVO, memberLaunchInfoPO);
        memberLaunchInfoPO.setMemberid(memberId);
        memberLaunchInfoPOMapper.insert(memberLaunchInfoPO);
        //6、保存项目回报信息
        List<ReturnVO> returnVOList = projectVO.getReturnVOList();
        List<ReturnPO> returnPOS = new ArrayList<>();
        for (ReturnVO returnvo : returnVOList) {
            ReturnPO returnPO = new ReturnPO();
            BeanUtils.copyProperties(returnvo, returnPO);
            returnPOS.add(returnPO);
        }
        returnPOMapper.insertReturnPOBatch(returnPOS, projectId);
        //7、保存项目的确认信息
        MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
        MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
        BeanUtils.copyProperties(memberConfirmInfoVO, memberConfirmInfoPO);
        memberConfirmInfoPO.setMemberid(memberId);
        memberConfirmInfoPOMapper.insert(memberConfirmInfoPO);
    }

    @Override
    public List<PortalTypeVO> getPortalTypeVO() {
        return projectPOMapper.selectPortalTypeVOList();
    }

    @Override
    public DetailProjectVO getDetailProjectVO(Integer projectId) {
        // 1.查询得到 DetailProjectVO对象
        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);
        // 2.根据 status确定  statusText
        Integer status = detailProjectVO.getStatus();
        switch (status) {
            case 0:
                detailProjectVO.setStatusText("审核中");
                break;
            case 1:
                detailProjectVO.setStatusText("众筹中");
                break;
            case 2:
                detailProjectVO.setStatusText("众筹成功");
                break;
            case 3:
                detailProjectVO.setStatusText("已关闭");
                break;
            default:
                break;
        }
        // 3.根据 deployeDate计算lastDay
        // 2020-10-15
        String deployDate = detailProjectVO.getDeployDate();
        //获取当前日期
        Date currentDay = new Date();
        //把众筹日期解析成  Date类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date deployDay = format.parse(deployDate);
            //获取当前当前日期的时间戳
            long currentTimeStamp = currentDay.getTime();
            //获取众筹日期的时间戳
            long deployTimeStamp = deployDay.getTime();
            //两个时间戳相减计算当前已经过去的时间
            long pastDays = (currentTimeStamp - deployTimeStamp) / 1000 / 60 / 60 / 24;
            //获取总的众筹天数
            Integer totalDays = detailProjectVO.getDay();
            //使用总的众筹天数减去已经过去的天数得到剩余天数
            Integer lastDay = (int) (totalDays - pastDays);
            detailProjectVO.setLastDay(lastDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailProjectVO;
    }

    @Override
    public List<VipProtalProjectVO> getVipProject(Integer vipLevel) {
        return projectPOMapper.selectVipProject(vipLevel);
    }

    @Override
    public List<ProjectPO> getAllProject(Integer typeId, Integer status, Integer orderType) {
        return projectPOMapper.selectAllProjectWithType(typeId,status,orderType);
    }

    @Override
    public List<MySupportVO> getMySupport(Integer memberId) {
        return projectPOMapper.selectMysupport(memberId);
    }

    @Override
    public List<ProjectPO> getMyProject(Integer memberId) {
        ProjectPOExample projectPOExample = new ProjectPOExample();
        projectPOExample.createCriteria().andMemberidEqualTo(memberId);
        return projectPOMapper.selectByExample(projectPOExample);
    }

    @Override
    public List<ProjectPO> getMyFocus(Integer memberId) {
        return projectPOMapper.selectMyFocus(memberId);
    }

    //如果是保存，需要填写修改事务等级
}
