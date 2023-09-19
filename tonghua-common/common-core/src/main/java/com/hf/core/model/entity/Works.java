package com.hf.userplatform.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Works implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作品ID
     */
    private Integer id;

    /**
     * 作者（用户ID）
     */
    private Integer author;

    /**
     * 文案
     */
    private String caption;

    /**
     * 标签
     */
    private String tags;

    /**
     * 图像集（多个在线链接，以JSON形式存储）
     */
    private String imageCollection;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 创建时间
     */
    private Date gtmCreate;

    /**
     * 修改时间
     */
    private Date gtmModify;

    /**
     * 删除状态 (0 表示未删除，1 表示已删除)
     */
    private int deleted;

    // Getter and Setter methods for each field

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(String imageCollection) {
        this.imageCollection = imageCollection;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getGtmCreate() {
        return gtmCreate;
    }

    public void setGtmCreate(Timestamp gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    public Date getGtmModify() {
        return gtmModify;
    }

    public void setGtmModify(Date gtmModify) {
        this.gtmModify = gtmModify;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
