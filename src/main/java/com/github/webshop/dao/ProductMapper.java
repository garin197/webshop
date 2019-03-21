package com.github.webshop.dao;

import com.github.webshop.pojo.Product;
import com.github.webshop.pojo.Row;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 添加商品记录
     *
     * @param product
     * @return 商品的主键值id
     */
    Integer add(Product product);

    Integer delete(Integer productId);

    Integer update(Product product);

    Integer getRowCount();

    List<Product> findAllWithLimit(Row row);

    List<Product> findAllWithCategoryByProductId(Integer productId);

    Product findById(Integer productId);

    List<Product> findAllByStatements(String statement);

    List<Product> sortStock(@Param("field") String field,@Param("order") String order,@Param("row") Row row);
}
