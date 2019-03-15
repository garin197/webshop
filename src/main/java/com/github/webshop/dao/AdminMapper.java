package com.github.webshop.dao;

import com.github.webshop.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    int add(Admin admin);

    int update(Admin admin);

    int delete(Integer id);

    Admin findById(Integer id);

    List<Admin> findAll();

    Admin findByName(String name);

    Admin FindByNameAndPassword(Admin admin);
}
