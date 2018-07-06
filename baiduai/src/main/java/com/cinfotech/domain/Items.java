/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2018
 */

package com.cinfotech.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.*;
import java.util.*;

@Entity
@Table(name = "items")
public class Items  implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @Id
    private String itemId;
    /**
     * 爬取时间
     */
    @Column(name="item_time")
    private Date itemTime;
    /**
     * 商品url
     */
    @Column(name="item_url")
    private String itemUrl;
    /**
     * 商品名称
     */
    @Column(name="item_name")
    private String itemName;
    /**
     * 店铺名
     */
    @Column(name="item_shop")
    private String itemShop;
    /**
     * 商品价格
     */
    @Column(name="item_price")
    private Double itemPrice;
    /**
     * 商品销量
     */
    @Column(name="item_sale_num")
    private Integer itemSaleNum;
    /**
     * 商品评论数
     */
    @Column(name="item_comment_num")
    private Integer itemCommentNum;
    /**
     * 商品库存
     */
    @Column(name="item_stock")
    private Integer itemStock;
    /**
     * 商品描述
     */
    @Column(name="item_detail")
    private String itemDetail;
    /**
     * 商品所在地区
     */
    @Column(name="item_area")
    private String itemArea;


    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemTime(Date itemTime) {
        this.itemTime = itemTime;
    }

    public Date getItemTime() {
        return this.itemTime;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemUrl() {
        return this.itemUrl;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemShop(String itemShop) {
        this.itemShop = itemShop;
    }

    public String getItemShop() {
        return this.itemShop;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemSaleNum(Integer itemSaleNum) {
        this.itemSaleNum = itemSaleNum;
    }

    public Integer getItemSaleNum() {
        return this.itemSaleNum;
    }

    public void setItemCommentNum(Integer itemCommentNum) {
        this.itemCommentNum = itemCommentNum;
    }

    public Integer getItemCommentNum() {
        return this.itemCommentNum;
    }

    public void setItemStock(Integer itemStock) {
        this.itemStock = itemStock;
    }

    public Integer getItemStock() {
        return this.itemStock;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getItemDetail() {
        return this.itemDetail;
    }

    public void setItemArea(String itemArea) {
        this.itemArea = itemArea;
    }

    public String getItemArea() {
        return this.itemArea;
    }
}
