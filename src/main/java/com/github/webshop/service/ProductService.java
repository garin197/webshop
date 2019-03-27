package com.github.webshop.service;

import com.github.webshop.pojo.PrdtImage;
import com.github.webshop.pojo.Product;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public interface ProductService {
    List<Product> getProductListWithLimit(HttpServletRequest request);

    int addProduct(HttpServletRequest request);

    int delProduct(HttpServletRequest request);

    int updateProduct(HttpServletRequest request) throws Exception;

    Product findProductById(HttpServletRequest request);

    List<Product> getProductListByStatement(HttpServletRequest request);

    int addImage(Integer productId,Integer type,String imgUrl);

    Integer getRowCount();

    List<PrdtImage> getImgList(HttpServletRequest request);

    List<PrdtImage> get_img_product_category_list(HttpServletRequest request);

    int delOneImage(HttpServletRequest request);

    int delAllImage(HttpServletRequest request);

    List<Product> sort_stock(HttpServletRequest request);

    List get_index_product(HttpServletRequest request);

    Product get_product_detail(HttpServletRequest request);

    String getProductNameById(HttpServletRequest request);
}
