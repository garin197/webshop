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

    Integer getRowCountWithCategoty(String categoryName);

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

    List get_product_list_by_categoryName(String categoryName);

    List get_all_product_list(Integer page, Integer rows);

    List get_product_list_by_categoryName_with_pagination(String categoryName, Integer page, Integer rows);

    List get_product_list_like_productName(String productName, Integer page, Integer rows);

    //Order
    List get_all_orders();

    List getOrdersList(HttpSession session, HttpServletRequest request);

    Order getOrderByOrderId_UserId(HttpServletRequest request);

    int delOrder(Integer orderId, Integer orderItemId) throws Exception;

    List getOrderItemList(HttpSession session, HttpServletRequest request);

    int setOrderStatus(Integer orderId, String value);

    List get_order_list_by_deliver(String deliver, Integer page, Integer limit);

    List get_order_list_by_status_deliver(String status, String deliver, Integer page, Integer limit);

    List get_order_list_by_status(String status, Integer page, Integer limit);

    int setOrderDeliver(Integer orderId, String value);

    Integer get_order_rows_count();

    Integer get_order_rows_count_of_status(String status);

    Integer get_order_rows_count_of_deliver(String deliver);

    Integer get_order_rows_count_of_status_and_deliver(String status,String deliver);
    //Order

    //review
    int add_review(String ordercreateDate,String content,Integer productId,Integer userId);
    List get_review(Integer pid);
    Integer get_review_rows_count_by_productId(Integer pid);
    //review
}
