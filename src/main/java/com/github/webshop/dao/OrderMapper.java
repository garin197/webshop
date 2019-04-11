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

    Order find_by_orderId(Integer orderId);

    int delete_by_orderId(Integer orderId);

    List find_all_by_user_id(Integer uid);

    Order find_one_by_orderId_and_userId(@Param("orderId") Integer orderId,@Param("userId") Integer userId);

    Order find_one_by_id(Integer orderId);

}
