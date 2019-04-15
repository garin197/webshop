/*
 * Copyright © github@garin197 五邑大学
 */

package com.github.webshop.dao;

import com.github.webshop.pojo.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int insert_one(Review review);
    Integer rows_count_of_productId(Integer productId);
    List select_all_by_productId(Integer productId);

}
