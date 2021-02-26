package com.atguigu.crowd.entity.po;

public class AdvertisementPO {
    private Integer id;

    private String advertisementDesc;

    private String advertisementUrl;

    private String advertisementPic;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdvertisementDesc() {
        return advertisementDesc;
    }

    public void setAdvertisementDesc(String advertisementDesc) {
        this.advertisementDesc = advertisementDesc == null ? null : advertisementDesc.trim();
    }

    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl == null ? null : advertisementUrl.trim();
    }

    public String getAdvertisementPic() {
        return advertisementPic;
    }

    public void setAdvertisementPic(String advertisementPic) {
        this.advertisementPic = advertisementPic == null ? null : advertisementPic.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}