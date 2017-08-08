package com.sxht.service;

import com.sxht.model.RoleFeature;

import java.util.List;

/**
 * Created by lmm on 2017/1/19.
 */
public interface RoleFeatureService {
    void insert(RoleFeature m);

    void update(RoleFeature m);

    void delete(String roleId, String featureId);

    RoleFeature get(String roleId, String featureId);

    List<RoleFeature> getAll();

    int removeByRoleId(String roleId);

    /**
     * 给角色赋功能
     * @param roleId
     * @param features
     */
    void setFeatures(String roleId, String features);
}
