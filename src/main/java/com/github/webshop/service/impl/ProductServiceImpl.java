package com.github.webshop.service.impl;

import com.github.webshop.dao.ImageMapper;
import com.github.webshop.dao.OrderItemMapper;
import com.github.webshop.dao.OrderMapper;
import com.github.webshop.dao.ProductMapper;
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
        Integer flag=-1;
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
     * @param session
     * @param request
     * @return
     */
    @Override
    public List getOrdersList(HttpSession session, HttpServletRequest request) {
        Integer userId= (Integer) session.getAttribute("currentUserId");
        return orderMapper.find_all_by_user_id(userId);
    }

    @Override
    public List getOrderItemList(HttpSession session, HttpServletRequest request) {
        Integer userId= (Integer) session.getAttribute("currentUserId");
        return orderItemMapper.find_all_by_userId(userId);
    }

    /**
     * 请求获取某个订单信息
     * @param request
     * @return
     */
    @Override
    public Order getOrderByOrderId_UserId(HttpServletRequest request) {
        String orderId=request.getParameter("orderId");
        String userId=request.getParameter("userId");

        return orderMapper.find_one_by_orderId_and_userId(new Integer(orderId),new Integer(userId));
    }

    @Override
    public String get_imgUrl(Integer productId) {
        return imageMapper.find_imgUrl_by_productId(productId);
    }

    /**
     * 立即购买service
     * @param request
     * @param session
     * @return
     */
    @Override
    public int buy_one(HttpServletRequest request, HttpSession session) {
        String address=request.getParameter("address");
        String post=request.getParameter("post");
        String receiver=request.getParameter("receiver");
        String mobile=request.getParameter("mobile");
        String comment=request.getParameter("comment");
        String orderCode=MyUtil.getDateId();//订单号
        Integer userId= (Integer) session.getAttribute("currentUserId");
        String createDate=MyUtil.getFormatDate();//创建日期
        String status="未付款";
        Order order=new Order();
        order.setAddress(address);
        order.setCreateDate(createDate);
        order.setPhone(mobile);
        order.setPost(post);
        order.setReceiver(receiver);
        order.setStatus(status);
        order.setUserMessage(comment);
        order.setUid(new Integer(userId));
        order.setOrderCode(orderCode);
        orderMapper.insert(order);//请求新增订单，并返回订单id
        Integer orderId=order.getOrderId();
        OrderItem orderItem=new OrderItem();
        String productId=request.getParameter("productId");
        String num=request.getParameter("num");
        orderItem.setNumber(new Integer(num));
        orderItem.setProductId(new Integer(productId));
        orderItem.setUserId(userId);
        orderItem.setOrderId(orderId);
        int n=productMapper.updateStock(new Integer(productId),new Integer(num));
        while(n<=0){
            n=productMapper.updateStock(new Integer(productId),new Integer(num));
        }
        return orderItemMapper.add_orderItem(orderItem);
    }

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

    @Override
    public int addProduct(HttpServletRequest request) {
        /**
         * 添加一个商品记录
         *
         * @param request
         * @return 商品记录的主键id
         */
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
