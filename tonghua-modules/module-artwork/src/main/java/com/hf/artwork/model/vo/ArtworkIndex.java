package com.hf.artwork.model.vo;

public class ArtworkIndex {

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
     * 主图链接
     */
    private String indexLink;

    /**
     * 点赞数
     */
    private Long likes;

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

    public String getIndexLink() {
        return indexLink;
    }

    public void setIndexLink(String indexLink) {
        this.indexLink = indexLink;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
}
