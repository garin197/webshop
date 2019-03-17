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
    public int EditProperty(Property property) {
        return 0;
    }

    @Override
    public List<Property> getPropertyList(HttpServletRequest request) {
        return null;
    }

    @Override
    public List<Property> getPropertyListByCategoryId(Integer cid) {
        return propertyMapper.findAllWithCategory(cid);
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int deleteProperty(Integer id) {
        return propertyMapper.delete(id);
    }
}
