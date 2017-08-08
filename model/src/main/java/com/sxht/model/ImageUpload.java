package com.sxht.model;

import java.util.Date;

/**
 * Created by LJ on 2017/3/24.
 */
public class ImageUpload {
    private Long imageid;

    private Long productid;

    private String url;

    private String description;

    private Date createtime;

    public Long getImageid() {
        return imageid;
    }

    public void setImageid(Long imageid) {
        this.imageid = imageid;
    }

    public Long getUserid() {
        return productid;
    }

    public void setUserid(Long productid) {
        this.productid = productid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
