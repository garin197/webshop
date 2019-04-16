package com.github.webshop.service.impl;

import com.github.webshop.dao.*;
import com.github.webshop.pojo.*;
import com.github.webshop.service.ProductService;
import com.github.webshop.util.MyUtil;
import com.github.webshop.util.RowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    /**
     * 获取评论数
     */
    @Override
    public Integer get_review_rows_count_by_productId(Integer pid) {
        return reviewMapper.rows_count_of_productId(pid);
    }

    /**
     * 获取评论
     * @param pid
     * @return
     */
    @Override
    public List get_review(Integer pid) {
        return reviewMapper.select_all_by_productId(pid);
    }

    /**
     * 添加评论
     * @param ordercreateDate
     * @param content
     * @param productId
     * @param userId
     * @return
     */
    @Override
    public int add_review(String ordercreateDate,String content, Integer productId, Integer userId) {
        Review review=new Review();
        review.setContent(content);
        review.setUserId(userId);
        review.setProductId(productId);
        review.setCreateDate(ordercreateDate);
        return reviewMapper.insert_one(review);
    }

    /**
     * 根据参数status和deliver的状态来查找订单
     * @param status
     * @param deliver
     * @return
     */
    @Override
    public Integer get_order_rows_count_of_status_and_deliver(String status, String deliver) {
        return null;
    }

    /**
     * 根据status和deliver的状态查找订单
     * @param status
     * @param deliver
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List get_order_list_by_status_deliver(String status, String deliver, Integer page, Integer limit) {
        Row row=new Row(page,limit);
        return orderMapper.find_all_by_status_and_deliver_pagination(status,deliver,row.getStart(),limit);
    }

    /**
     * 获取status参数指定的订单列表
     * @param status
     * @return
     */
    @Override
    public List get_order_list_by_status(String status, Integer page, Integer limit) {
        Row row=new Row(page,limit);
        return orderMapper.find_all_by_status_pagination(status,row.getStart(),limit);
    }

    @Override
    public List get_order_list_by_deliver(String deliver, Integer page, Integer limit) {
        Row row=new Row(page,limit);
        return orderMapper.find_all_by_deliver_pagination(deliver,row.getStart(),limit);
    }

    /**
     * 更新发货状态
     * 更新发货日期
     *
     * @param orderId
     * @param value
     * @return
     */
    @Override
    public int setOrderDeliver(Integer orderId, String value) {
        orderMapper.update_deliveryDate(orderId, MyUtil.getFormatDate());
        return orderMapper.update_deliver(orderId, value);
    }

    /**
     * 获取所有定的详细信息
     * 日期降序排序
     *
     * @return
     */
    @Override
    public List get_all_orders() {
        return orderMapper.find_all();
    }

    /**
     * 获取order表的所有行数
     *
     * @return
     */
    @Override
    public Integer get_order_rows_count() {
        return orderMapper.row_count();
    }

    /**
     * 获取参数指定的status值的所有order的行数
     *
     * @param status
     * @return
     */
    @Override
    public Integer get_order_rows_count_of_status(String status) {
        return orderMapper.row_count_of_status(status);
    }

    /**
     * 获取参数指定的deliver值所有的order的行数
     *
     * @param deliver
     * @return
     */
    @Override
    public Integer get_order_rows_count_of_deliver(String deliver) {
        return orderMapper.row_count_of_deliver(deliver);
    }

    /**
     * 模糊搜索商品名匹配的商品
     *
     * @param productName
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List get_product_list_like_productName(String productName, Integer page, Integer rows) {
        Row row = new Row(page, rows);
        productName = "%" + productName + "%";
        return productMapper.select_like_productName(productName, row.getStart(), row.getLimit());
    }

    @Override
    public List<Product> getProductListWithLimit(HttpServletRequest request) {
        return null;
    }

    /**
     * 获取所有分页内商品分类封面
     *
     * @param request
     * @return
     */
    @Override
    public List<PrdtImage> get_img_product_category_list(HttpServletRequest request) {
        return imageMapper.get_img_product_category_list(RowUtil.getRow(request));
    }

    /**
     * 更新库存信息
     *
     * @param pid
     * @param number
     * @return
     */
    @Override
    public int update_stock(Integer pid, Integer number) {
        Integer currentStockNumber = productMapper.getStock(pid);
        Integer flag = -1;
        if (currentStockNumber >= number) {
            flag = productMapper.updateStock(pid, number);
        }
        return flag;
    }

    /**
     * 添加商品、图片、指定封面type=1
     *
     * @param productId
     * @param type
     * @param imgUrl
     * @return
     */
    @Override
    public int addImage(Integer productId, Integer type, String imgUrl) {
        PrdtImage image = new PrdtImage();
        image.setProductId(productId);
        image.setImgType(type);
        image.setImgUrl(imgUrl);
        return imageMapper.add(image);
    }

    /**
     * 通过产品id(productId) 查找所有的图片信息
     *
     * @param request
     * @return
     */
    @Override
    public List<PrdtImage> getImgList(HttpServletRequest request) {
        return imageMapper.findByProductId(new Integer(request.getParameter("productId")));
    }

    @Override
    public int delOneImage(HttpServletRequest request) {
        return 0;
    }

    List get_orders(HttpSession session, HttpServletRequest request) {
        return null;
    }

    /**
     * 请求获取当前用户所有订单
     *
     * @param session
     * @param request
     * @return
     */
    @Override
    public List getOrdersList(HttpSession session, HttpServletRequest request) {
        Integer userId = (Integer) session.getAttribute("currentUserId");
        return orderMapper.find_all_by_user_id(userId);
    }

    @Override
    public List getOrderItemList(HttpSession session, HttpServletRequest request) {
        Integer userId = (Integer) session.getAttribute("currentUserId");
        return orderItemMapper.find_all_by_userId(userId);
    }

    /**
     * 请求获取某个订单信息
     *
     * @param request
     * @return
     */
    @Override
    public Order getOrderByOrderId_UserId(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        String userId = request.getParameter("userId");
        return orderMapper.find_one_by_orderId_and_userId(new Integer(orderId), new Integer(userId));
    }

    @Override
    public String get_imgUrl(Integer productId) {
        return imageMapper.find_imgUrl_by_productId(productId);
    }

    /**
     * 获取参数指定的分类的商品的总数量
     *
     * @param categoryName
     * @return
     */
    @Override
    public Integer getRowCountWithCategoty(String categoryName) {
        return productMapper.getRowCountWithCategory(categoryName);
    }


    /**
     * 获取所有商品的列表
     *
     * @return
     */
    @Override
    public List get_all_product_list(Integer page, Integer rows) {
        Row row = new Row(page, rows);
        return productMapper.select_all_pagination(row.getStart(), row.getLimit());
    }

    /**
     * 删除一个订单项
     *
     * @param orderId
     * @param orderItemId
     * @return
     */
    @Override
    public int delOrder(Integer orderId, Integer orderItemId) throws Exception {
        if (orderMapper.delete_by_orderId(orderId) <= 0) {//code值：1
            throw new Exception("删除订单表失败");
        }
//        if (orderItemMapper.delete_one_by_id(orderItemId) <= 0) {//code值：2
//            throw new Exception("删除订单项表失败");
//        }
        return 1;
    }

    /**
     * 更新订单的状态
     * 更新支付日期
     * 并且更新库存
     *
     * @param orderId
     * @return
     */
    @Override
    public int setOrderStatus(Integer orderId, String value) {
        if (value.equals("已付款") || value == "已付款") {

            orderMapper.update_paydate(orderId, MyUtil.getFormatDate());
        }else {

            orderMapper.update_confirmDate(orderId,MyUtil.getFormatDate());
        }
        return orderMapper.update_status(orderId, value);
    }

    /**
     * 立即购买service
     *
     * @param request
     * @param session
     * @return
     */
    @Override
    public int buy_one(HttpServletRequest request, HttpSession session) {
        String address = request.getParameter("address");
        String post = request.getParameter("post");
        String receiver = request.getParameter("receiver");
        String mobile = request.getParameter("mobile");
        String comment = request.getParameter("comment");
        String orderCode = MyUtil.getDateId();//订单号
        Integer userId = (Integer) session.getAttribute("currentUserId");
        String createDate = MyUtil.getFormatDate();//创建日期
        String status = "未付款";
        String deliver = "未发货";
        Order order = new Order();
        order.setAddress(address);
        order.setCreateDate(createDate);
        order.setPhone(mobile);
        order.setPost(post);
        order.setReceiver(receiver);
        order.setStatus(status);
        order.setUserMessage(comment);
        order.setUid(new Integer(userId));
        order.setOrderCode(orderCode);
        order.setDeliver(deliver);
        orderMapper.insert(order);//请求新增订单，并返回订单id
        Integer orderId = order.getOrderId();
        OrderItem orderItem = new OrderItem();
        String productId = request.getParameter("productId");
        String num = request.getParameter("num");
        orderItem.setNumber(new Integer(num));
        orderItem.setProductId(new Integer(productId));
        orderItem.setUserId(userId);
        orderItem.setOrderId(orderId);
        // TODO: 2019/4/11 等人付完款再同步 
        // TODO: 2019/4/11 搜索
//        int n=productMapper.updateStock(new Integer(productId),new Integer(num));
//        while(n<=0){
//            n=productMapper.updateStock(new Integer(productId),new Integer(num));
//        }
        return orderItemMapper.add_orderItem(orderItem);
    }

    /**
     * 根据分类的名称获取商品列表
     * 分页获取
     *
     * @param categoryName
     * @return
     */

    @Override
    public List get_product_list_by_categoryName_with_pagination(String categoryName, Integer page, Integer rows) {
        Row row = new Row(page, rows);
//        categoryName="'"+categoryName+"'";
        return categoryMapper.select_all_by_categoryName_with_product_with_pagination(categoryName, row.getStart(), row.getLimit());
    }

    /**
     * 根据分类的名称获取商品列表
     *
     * @param categoryName
     * @return
     */

    @Override
    public List get_product_list_by_categoryName(String categoryName) {
        return categoryMapper.select_all_by_categoryName_with_product(categoryName);
    }

    /**
     * 删除所有图片
     * 没用
     *
     * @param request
     * @return
     */
    @Override
    public int delAllImage(HttpServletRequest request) {
        return 0;
    }

    @Override
    public int updateProduct(HttpServletRequest request) throws Exception {
        String productName = request.getParameter("productName");
        String subTitle = request.getParameter("subTitle");
        String originalPrice = request.getParameter("originalPrice");
        String promotePrice = request.getParameter("promotePrice");
        String stock = request.getParameter("stock");
        String productId = request.getParameter("productId");
        Product product = new Product();
        product.setProductId(new Integer(productId));
        if (subTitle != null && subTitle != "") {
            product.setSubTitle(subTitle);
        }
        if (productName != null && productName != "") {
            product.setProductName(productName);
        }
        if (stock != null && stock != "") {
            product.setStock(new Integer(stock));
        }
        if (promotePrice != null && promotePrice != "") {
            product.setPromotePrice(new Float(promotePrice));
        }
        if (originalPrice != null && originalPrice != "") {
            product.setOriginalPrice(new Float(originalPrice));
        }
        return productMapper.update(product);
    }

    /**
     * 添加一个商品记录
     *
     * @param request
     * @return 商品记录的主键id
     */
    @Override
    public int addProduct(HttpServletRequest request) {
        String productName = request.getParameter("productName");
        String productSubTitle = request.getParameter("productSubTitle");
        String originalPrice = request.getParameter("originalPrice");
        String promotePrice = request.getParameter("promotePrice");
        String categoryId = request.getParameter("category");//传过来的值是categoryId
        String stock = request.getParameter("stock");
        Product product = new Product();
        product.setCategoryId(new Integer(categoryId));
        product.setOriginalPrice(new Float(originalPrice));
        product.setProductName(productName);
        product.setPromotePrice(new Float(promotePrice));
        product.setStock(new Integer(stock));
        product.setSubTitle(productSubTitle);
        product.setCreateDate(MyUtil.getFormatDate());
        productMapper.add(product);
        return product.getProductId();
    }

    /**
     * 获取商品总行数
     *
     * @return
     */
    @Override
    public Integer getRowCount() {
        return productMapper.getRowCount();
    }

    /**
     * 获取封面的商品信息
     * 填充封面
     */
    @Override
    public List get_index_product(HttpServletRequest request) {
        String categoryName = request.getParameter("categoryName");
        Integer page = new Integer(request.getParameter("page"));
        Integer limit = new Integer(request.getParameter("limit"));
        Row row = new Row(page, limit);

        return productMapper.get_index_product(row.getStart(), row.getLimit(), categoryName);
    }

    /**
     * 获取商品的详情
     *
     * @param request
     * @return
     */
    @Override
    public Product get_product_detail(HttpServletRequest request) {
        String productId = request.getParameter("pid");
        return productMapper.findById(new Integer(productId));
    }

    /**
     * 存货排序
     *
     * @param request
     * @return
     */
    @Override
    public List<Product> sort_stock(HttpServletRequest request) {
        String field = request.getParameter("field");//排序字段
        String order = request.getParameter("order");//排序方式
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        Row row = new Row(Integer.parseInt(page), Integer.parseInt(limit));

        return productMapper.sortStock(field, order, row);
    }

    /**
     * 删除商品、连带所有图片
     * 删除本地文件
     *
     * @param request
     * @return
     */
    @Override
    public int delProduct(HttpServletRequest request) {
        Integer productId = new Integer(request.getParameter("productId"));
        MyUtil.delUploadFile(imageMapper.findByProductId(productId));//删除本地文件
        imageMapper.deleteAll(productId);//删除数据库中记录
        return productMapper.delete(productId);
    }

    /**
     * 通过pid查找商品
     *
     * @param request
     * @return
     */
    @Override
    public Product findProductById(HttpServletRequest request) {
        return null;
    }

    /**
     * 根据商品id获取商品的名称
     *
     * @param request
     * @return
     */
    @Override
    public String getProductNameById(HttpServletRequest request) {
        return productMapper.findNameById(new Integer(request.getParameter("pid")));
    }


    @Override
    public List<Product> getProductListByStatement(HttpServletRequest request) {
        String statement = "%" + request.getParameter("statement") + "%";
        return productMapper.findAllByStatements(statement);
    }
}
