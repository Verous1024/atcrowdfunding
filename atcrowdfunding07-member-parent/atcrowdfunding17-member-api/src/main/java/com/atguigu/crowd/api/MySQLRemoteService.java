package com.atguigu.crowd.api;

import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.po.ProjectPO;
import com.atguigu.crowd.entity.vo.*;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 01:03
 */
@FeignClient("atguigu-crowd-mysql")
public interface MySQLRemoteService {

    @RequestMapping("/get/project/detail/remote/{projectId}")
    ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId);


    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);

    @RequestMapping("/save/member/remote")
    ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);

    //注意：实体类需要添加@RequestBody，单个的需要添加@RequestParam
    @RequestMapping("/save/project/vo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);

    @RequestMapping("/get/portal/type/project/data/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

    @RequestMapping("/get/order/project/vo/remote")
    ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId") Integer projectId, @RequestParam("returnId") Integer returnId);

    @RequestMapping("/get/address/vo/remote")
    ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId);

    @RequestMapping("/save/address/remote")
    ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO);


    @RequestMapping("/save/order/remote")
    ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO);

    @RequestMapping("/get/vip/project/data/remote")
    ResultEntity<List<VipProtalProjectVO>> getVipProjectDataRemote();

    @RequestMapping("/get/all/project/with/type")
    ResultEntity<List<ProjectPO>> getAllProjectWithType(
            @RequestParam(value = "typeId", required = false) Integer typeId,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "orderType", required = false) Integer orderType);


    @RequestMapping("/update/member")
    ResultEntity<String> updateMember(@RequestBody MemberPO loginMember);

    @RequestMapping("/get/my/support")
    ResultEntity<List<MySupportVO>> getMySupport(@RequestParam("memberId") Integer memberId);

    @RequestMapping("/get/my/focus")
    ResultEntity<List<ProjectPO>> getMyFocus(@RequestParam("memberId") Integer memberId);

    @RequestMapping("/get/my/project")
    ResultEntity<List<ProjectPO>> getMyProject(@RequestParam("memberId") Integer memberId);

    @RequestMapping("/delete/my/order/remote")
    ResultEntity<String> deleteMyOrderRemote(@RequestParam("orderId") Integer orderId);

    @RequestMapping("/unsubscribe/remote")
    ResultEntity<String> unsubscribeRemote(@RequestParam("projectId") Integer projectId,@RequestParam("memberId") Integer memberId);

    @RequestMapping("/delete/my/project/by/id/remote")
    ResultEntity<String> deleteMyProjectByIdRemote(@RequestParam("projectId") Integer projectId);

    @RequestMapping("/is/has/follow")
    ResultEntity<Integer> isHasFollow(@RequestParam("projectId")Integer projectId,@RequestParam("id") Integer memberId);

    @RequestMapping("/subscribe/remote")
    ResultEntity<String> subscribeRemote(@RequestParam("projectId") Integer projectId,@RequestParam("memberId") Integer memberId);
}
