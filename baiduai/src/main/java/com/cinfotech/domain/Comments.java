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
@Table(name = "comments")
public class Comments  implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @Id
    private Integer cmId;
    /**
     * 评论商品id
     */
    @Column(name="item_id")
    private String itemId;
    /**
     * 评论者
     */
    @Column(name="cm_author")
    private String cmAuthor;
    /**
     * 评论时间
     */
    @Column(name="cm_time")
    private Date cmTime;
    /**
     * 评论内容
     */
    @Column(name="cm_detail")
    private String cmDetail;
    /**
     * 评论级别:1好评,2中评,3差评
     */
    @Column(name="cm_type")
    private Integer cmType;
    /**
     * 评论级别:0 正常,1 暴恐违禁,2 文本色情,3 政治敏感,4 恶意推广,5 低俗辱骂
     */
    @Column(name="cm_ai_type")
    private Integer cmAiType;


    public void setCmId(Integer cmId) {
        this.cmId = cmId;
    }

    public Integer getCmId() {
        return this.cmId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setCmAuthor(String cmAuthor) {
        this.cmAuthor = cmAuthor;
    }

    public String getCmAuthor() {
        return this.cmAuthor;
    }

    public void setCmTime(Date cmTime) {
        this.cmTime = cmTime;
    }

    public Date getCmTime() {
        return this.cmTime;
    }

    public void setCmDetail(String cmDetail) {
        this.cmDetail = cmDetail;
    }

    public String getCmDetail() {
        return this.cmDetail;
    }

    public void setCmType(Integer cmType) {
        this.cmType = cmType;
    }

    public Integer getCmType() {
        return this.cmType;
    }

    public void setCmAiType(Integer cmAiType) {
        this.cmAiType = cmAiType;
    }

    public Integer getCmAiType() {
        return this.cmAiType;
    }
}
