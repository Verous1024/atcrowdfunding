package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.po.*;
import com.atguigu.crowd.entity.vo.AddressVO;
import com.atguigu.crowd.entity.vo.OrderProjectVO;
import com.atguigu.crowd.entity.vo.OrderVO;
import com.atguigu.crowd.mapper.AddressPOMapper;
import com.atguigu.crowd.mapper.OrderPOMapper;
import com.atguigu.crowd.mapper.OrderProjectPOMapper;
import com.atguigu.crowd.mapper.ProjectPOMapper;
import com.atguigu.crowd.service.api.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-25 上午 09:53
 */
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Autowired
    private AddressPOMapper addressPOMapper;

    @Autowired
    private ProjectPOMapper projectPOMapper;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {
        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    @Override
    public List<AddressVO> getAddressVOList(Integer memberId) {
        AddressPOExample example = new AddressPOExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        List<AddressPO> addressPOS = addressPOMapper.selectByExample(example);
        ArrayList<AddressVO> addressVOS = new ArrayList<>();
        for (AddressPO addressPO : addressPOS) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO,addressVO);
            addressVOS.add(addressVO);
        }
        return addressVOS;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly = false,rollbackFor = Exception.class)
    @Override
    public void saveAddress(AddressVO addressVO) {
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO,addressPO);
        addressPOMapper.insert(addressPO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly = false,rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderVO orderVO) {
        //1、保存订单
        OrderPO orderPO = new OrderPO();
        BeanUtils.copyProperties(orderVO, orderPO);
        logger.info(orderPO.toString());
        OrderProjectPO orderProjectPO = new OrderProjectPO();
        OrderProjectVO orderProjectVO = orderVO.getOrderProjectVO();
        BeanUtils.copyProperties(orderProjectVO,orderProjectPO);
        orderPO.setStatus(0); //设置订单未未发货状态
        orderPOMapper.insert(orderPO);
        //2、订单对应的产品关联
        Integer id = orderPO.getId();
        orderProjectPO.setOrderId(id);
        orderProjectPOMapper.insert(orderProjectPO);
        //3、增加对应产品的筹集金额
        Integer projectId = orderVO.getProjectId();
        Integer returnCount = orderProjectVO.getReturnCount();
        Integer supportPrice = orderProjectVO.getSupportPrice();
        Integer totalSummary = returnCount * supportPrice;  //单价 * 金额
        ProjectPO currentprojectPO = projectPOMapper.selectByPrimaryKey(projectId);
        Long oldsupportmoney = currentprojectPO.getSupportmoney();
        currentprojectPO.setSupportmoney(totalSummary+oldsupportmoney);
        Integer oldsupporter = currentprojectPO.getSupporter();
        currentprojectPO.setSupporter(oldsupporter+1);
        projectPOMapper.updateByPrimaryKey(currentprojectPO);
        /*        projectPOMapper.updateSupportMoneyById(projectId,totalSummary,oldsupportmoney);*/
        //3、增加对应产品的支持人数
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly = false,rollbackFor = Exception.class)
    @Override
    public void deleteMyOrderById(Integer orderId) {
        orderPOMapper.deleteByPrimaryKey(orderId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly = false,rollbackFor = Exception.class)
    @Override
    public void sendMyOrder(Integer orderId) {
        OrderPO orderPO = new OrderPO();
        orderPO.setId(orderId);
        orderPO.setStatus(1);
        orderPOMapper.updateByPrimaryKeySelective(orderPO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly = false,rollbackFor = Exception.class)
    @Override
    public void confirmMyReceipt(Integer orderId) {
        OrderPO orderPO = new OrderPO();
        orderPO.setId(orderId);
        orderPO.setStatus(2);
        orderPOMapper.updateByPrimaryKeySelective(orderPO);
    }
}
