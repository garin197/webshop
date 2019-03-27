/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.github.webshop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PropertyvalueMapper {
    int modify_property_value(@Param("productId") Integer productId,@Param("propertyId") Integer propertyId, @Param("propertyValue") String propertyValue);
    int add_property_value(@Param("productId") Integer productId,@Param("propertyId") Integer propertyId, @Param("propertyValue") String propertyValue);
    List select_property_value(@Param("productId") Integer productId,@Param("categoryId") Integer categoryId);
}
