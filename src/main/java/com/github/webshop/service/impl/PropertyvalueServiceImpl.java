/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.github.webshop.service.impl;

import com.github.webshop.dao.PropertyvalueMapper;
import com.github.webshop.service.PropertyvalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PropertyvalueServiceImpl implements PropertyvalueService {

    @Autowired
    private PropertyvalueMapper propertyvalueMapper;

    /**
     * 添加一个属性值,若果更新失败就执行插入数据操作
     *
     * @param request 隐藏参数：{ productId商品ID propertyId属性ID value属性值 }
     * @return
     */
    @Override
    public int handlePropertyValue(HttpServletRequest request) {
        Integer propertyId = new Integer(request.getParameter("propertyId"));
        Integer productId = new Integer(request.getParameter("productId"));
        String propertyValue = request.getParameter("propertyValue");
        int flag = -1;
        flag = propertyvalueMapper.modify_property_value(productId, propertyId, propertyValue);//更新
        if (flag <= 0) {
            flag = propertyvalueMapper.add_property_value(productId, propertyId, propertyValue);//插入、添加
        }
        return flag;
    }

    /**
     * 获取属性以及属性值
     *
     * @param request
     * @return
     */
    @Override
    public List get_propertyvalue_list_by(HttpServletRequest request) {
        Integer productId = new Integer(request.getParameter("pid"));
        Integer categoryId = new Integer(request.getParameter("cid"));
        return propertyvalueMapper.select_property_value(productId, categoryId);
    }
}
