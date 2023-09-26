package com.hf.artwork.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ArtworkVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作品ID
     */
    private Long id;

    /**
     * 作者（用户ID）
     */
    private String authorId;

    /**
     * 作者头像
     */
    private String authorAvatar;

    /**
     * 作者昵称
     */
    private String username;

    /**
     * 文案
     */
    private String caption;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 图像集（多个在线链接，以JSON形式存储）
     */
    private List<String> imageCollection;

    /**
     * 发布时间
     */
    private Date publishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(List<String> imageCollection) {
        this.imageCollection = imageCollection;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "ArtworkVO{" +
                "id=" + id +
                ", authorId='" + authorId + '\'' +
                ", authorAvatar='" + authorAvatar + '\'' +
                ", username='" + username + '\'' +
                ", caption='" + caption + '\'' +
                ", tags=" + tags +
                ", imageCollection=" + imageCollection +
                ", publishTime=" + publishTime +
                '}';
    }
}
