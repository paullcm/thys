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
@Table(name = "pictures")
public class Pictures  implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @Id
    private Integer picId;
    /**
     * 商品id
     */
    @Column(name="item_id")
    private String itemId;
    /**
     * 图片下载地址
     */
    @Column(name="pic_url")
    private String picUrl;
    /**
     * 图片本地目录
     */
    @Column(name="pic_dir")
    private String picDir;
    /**
     * 图片级别:0 正常,1 暴恐违禁,2 文本色情,3 政治敏感,4 恶意推广,5 低俗辱骂
     */
    @Column(name="pic_ai_type")
    private Integer picAiType;


    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public Integer getPicId() {
        return this.picId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicDir(String picDir) {
        this.picDir = picDir;
    }

    public String getPicDir() {
        return this.picDir;
    }

    public void setPicAiType(Integer picAiType) {
        this.picAiType = picAiType;
    }

    public Integer getPicAiType() {
        return this.picAiType;
    }
}
