package com.github.webshop.dao;

import com.github.webshop.pojo.PrdtImage;
import com.github.webshop.pojo.Row;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    Integer add(PrdtImage prdtImage);

    Integer deleteOne(Integer prdtImageId);

    Integer deleteAll(Integer productId);

    Integer update(PrdtImage prdtImage);

    PrdtImage findById(Integer prdtImageId);

    PrdtImage findByType(Integer imgType);

    List<PrdtImage> findByProductId(Integer productId);

    List<PrdtImage> get_img_product_category_list(Row row);//获取type为1的图片连带产品和分类

//    List<PrdtImage> findAllByStatements(PrdtImage prdtImage, @Param("beginDate") String beginDate, @Param("endDate") String endDate);
//    List<PrdtImage> findAllWithLimit(Row row);
}
