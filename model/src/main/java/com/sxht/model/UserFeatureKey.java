package com.sxht.model;

public class UserFeatureKey {
    private String userid;

    private String featureid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getFeatureid() {
        return featureid;
    }

    public void setFeatureid(String featureid) {
        this.featureid = featureid == null ? null : featureid.trim();
    }
}