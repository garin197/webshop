/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.github.webshop.dao;

import com.github.webshop.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insert(Order order);

    int update(Order order);

    List find_all();

    Integer row_count();

    Integer row_count_of_status(String status);

    Integer row_count_of_deliver(String deliver);

    Integer row_count_of_status_deliver(@Param("status")String status,@Param("deliver") String deliver);

    List find_all_by_status_pagination(@Param("status") String status, @Param("start") Integer start, @Param("limit") Integer limit);

    List find_all_by_deliver_pagination(@Param("deliver") String deliver, @Param("start") Integer start, @Param("limit") Integer limit);

    List find_all_by_status_and_deliver_pagination(@Param("status") String status, @Param("deliver") String deliver, @Param("start") Integer start, @Param("limit") Integer limit);

    Order find_by_orderId(Integer orderId);

    int delete_by_orderId(Integer orderId);

    List find_all_by_user_id(Integer uid);

    Order find_one_by_orderId_and_userId(@Param("orderId") Integer orderId, @Param("userId") Integer userId);

    Order find_one_by_id(Integer orderId);

    int update_status(@Param("orderId") Integer orderId, @Param("statusValue") String statusValue);

    int update_deliver(@Param("orderId") Integer orderId, @Param("deliverValue") String deliverValue);

    int update_paydate(@Param("orderId") Integer orderId, @Param("paydate") String paydate);

    int update_deliveryDate(@Param("orderId") Integer orderId, @Param("deliveryDate") String deliveryDate);

    int update_confirmDate(@Param("orderId") Integer orderId, @Param("confirmData") String confirmData);
}
