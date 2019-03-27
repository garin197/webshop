package com.github.webshop.service.impl;

import com.github.webshop.dao.PropertyMapper;
import com.github.webshop.pojo.Property;
import com.github.webshop.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分类服务类
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> FindPropertyOnLike(String statement) {
        return null;
    }

    @Override
    public int FindPropertyByName(HttpServletRequest request) {
        return 0;
    }

    @Override
    public int AddProperty(HttpServletRequest request) {
        Integer i= (Integer) request.getAttribute("categoryId");
        String s=request.getParameter("propertyName");
        Property p=new Property();
        p.setCategoryId(i);
        p.setPropertyName(s);
        return propertyMapper.add(p);
    }

    @Override
    public int EditProperty(HttpServletRequest request) {
        String categoryId=request.getParameter("categoryId");
        String propertyId=request.getParameter("propertyId");
        String propertyName=request.getParameter("propertyName");
        Property property=new Property();
        property.setPropertyName(propertyName);
        property.setCategoryId(new Integer(categoryId));
        property.setPropertyId(new Integer(propertyId));
        return propertyMapper.update(property);
    }

    @Override
    public List<Property> getPropertyList(HttpServletRequest request) {
        return null;
    }

    @Override
    public List<Property> getPropertyListByCategoryId(Integer cid) {
        return propertyMapper.findAllWithCategory(cid);
    }


    /**
     * 获取指定的分类的属性总数
     * @param request
     * @return 指定的分类的属性总数
     */
    @Override
    public Integer getRowCountByCategoryId(HttpServletRequest request) {
        return propertyMapper.get_row_count_byCategoryId(new Integer(request.getParameter("cid")));
    }

    @Override
    public int deleteProperty(Integer id) {
        return propertyMapper.delete(id);
    }
}
