package com.sxht.model;

public class RoleFeatureKey {
    private String featureid;

    private String roleid;

    public String getFeatureid() {
        return featureid;
    }

    public void setFeatureid(String featureid) {
        this.featureid = featureid == null ? null : featureid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }
}