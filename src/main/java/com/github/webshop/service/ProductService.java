package com.github.webshop.service;

import com.github.webshop.pojo.Order;
import com.github.webshop.pojo.PrdtImage;
import com.github.webshop.pojo.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProductService {
    List<Product> getProductListWithLimit(HttpServletRequest request);

    int addProduct(HttpServletRequest request);

    int delProduct(HttpServletRequest request);

    int updateProduct(HttpServletRequest request) throws Exception;

    Product findProductById(HttpServletRequest request);

    List<Product> getProductListByStatement(HttpServletRequest request);

    int addImage(Integer productId, Integer type, String imgUrl);

    Integer getRowCount();

    List<PrdtImage> getImgList(HttpServletRequest request);

    List<PrdtImage> get_img_product_category_list(HttpServletRequest request);

    String get_imgUrl(Integer productId);

    int delOneImage(HttpServletRequest request);

    int delAllImage(HttpServletRequest request);

    List<Product> sort_stock(HttpServletRequest request);

    List get_index_product(HttpServletRequest request);

    Product get_product_detail(HttpServletRequest request);

    String getProductNameById(HttpServletRequest request);

    int update_stock(Integer pid, Integer number);

    int buy_one(HttpServletRequest request, HttpSession session);

    //Order
    List getOrdersList(HttpSession session, HttpServletRequest request);

    Order getOrderByOrderId_UserId(HttpServletRequest request);

    int delOrder(Integer orderId,Integer orderItemId) throws Exception;

    List getOrderItemList(HttpSession session, HttpServletRequest request);

    int setOrderStatus(Integer orderId,String value);
    //Order
}
