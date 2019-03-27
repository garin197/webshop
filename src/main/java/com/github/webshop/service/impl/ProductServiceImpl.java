package com.github.webshop.service.impl;

import com.github.webshop.dao.ImageMapper;
import com.github.webshop.dao.ProductMapper;
import com.github.webshop.pojo.PrdtImage;
import com.github.webshop.pojo.Product;
import com.github.webshop.pojo.Row;
import com.github.webshop.service.ProductService;
import com.github.webshop.util.MyUtil;
import com.github.webshop.util.RowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ImageMapper imageMapper;

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
        return productMapper.get_index_product(row, categoryName);
    }

    @Override
    public Product get_product_detail(HttpServletRequest request) {
        String productId = request.getParameter("pid");
        return productMapper.findById(new Integer(productId));
    }

        /**
         * 存货排序
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
     * @param request
     * @return
     */
    @Override
    public Product findProductById(HttpServletRequest request) {
        return null;
    }

    /**
     * 根据商品id获取商品的名称
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
