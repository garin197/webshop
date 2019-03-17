package com.github.webshop.service;

import com.github.webshop.pojo.Property;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public interface PropertyService {

    List<Property> FindPropertyOnLike(String statement);

    int FindPropertyByName(HttpServletRequest request);

    int AddProperty(HttpServletRequest request);

    int EditProperty(Property property);

    List<Property> getPropertyList(HttpServletRequest request);

    List<Property> getPropertyListByCategoryId(Integer cid);

    int getRowCount();

    int deleteProperty(Integer id);
}
