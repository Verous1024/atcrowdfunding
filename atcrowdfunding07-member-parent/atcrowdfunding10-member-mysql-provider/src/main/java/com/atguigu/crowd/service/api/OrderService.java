package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.vo.AddressVO;
import com.atguigu.crowd.entity.vo.OrderProjectVO;
import com.atguigu.crowd.entity.vo.OrderVO;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-25 上午 09:53
 */
public interface OrderService {
    OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

    List<AddressVO> getAddressVOList(Integer memberId);

    void saveAddress(AddressVO addressVO);

    void saveOrder(OrderVO orderVO);

    void deleteMyOrderById(Integer orderId);

    void sendMyOrder(Integer orderId);

    void confirmMyReceipt(Integer orderId);
}
