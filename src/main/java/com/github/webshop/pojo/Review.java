/*
 * Copyright © github@garin197 五邑大学
 */

package com.github.webshop.pojo;

public class Review {
    //评论id
    private Integer reviewId;
    //用户id
    private Integer userId;
    //商品id
    private Integer productId;
    //评论正文
    private String content;
    //评论创建日期
    private String createDate;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
