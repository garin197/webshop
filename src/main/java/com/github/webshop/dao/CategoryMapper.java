package com.github.webshop.dao;

import com.github.webshop.pojo.Category;
import com.github.webshop.pojo.Product;
import com.github.webshop.pojo.Row;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int add(Category category);

    int update(Category category);

    int delete(Integer id);

    Category findById(Integer id);

    List<Category> findAll();

    int total();

    int findByName(String name);

    List<Category> findLikeName(String name);

    List<Category> findWithLimit(Row row);

    List<Product> select_all_by_categoryName_with_product(String categoryName);

    List<Product> select_all_by_categoryName_with_product_with_pagination(@Param("categoryName") String categoryName,@Param("start") Integer start,@Param("limit") Integer limit);
}