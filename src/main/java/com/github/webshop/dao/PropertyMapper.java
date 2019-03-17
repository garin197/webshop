package com.github.webshop.dao;

import com.github.webshop.pojo.Property;
import com.github.webshop.pojo.Row;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PropertyMapper {

    int add(Property property);

    int update(Property property);

    int delete(Integer id);

    List<Property> findAllWithCategory(Integer integer);

    int total();

    Property findById(Integer id);

    int findByName(String name);

    List<Property> findLikeName(String name);

    List<Property> findWithLimit(Row row);
}