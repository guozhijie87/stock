package com.sxht.service;

import com.sxht.model.Feature;
import com.sxht.common.web.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * Created by lmm on 2017/1/9.
 */
public interface FeatureService {
    void insert(Feature f);

    void update(Feature f);

    void delete(String id);

    Feature get(String id);

    List<Feature> getFeatures(int index, int size);

    List<TreeNode> tree(boolean checkIsMenu);

    List<TreeNode> getTreeByUser(String userId);

    List<TreeNode> getTreeByRole(String roleId);

    List<Feature> getFeaturesByRole(String roleId);

    List<Feature> getFeaturesByUser(String userId);

    List<Feature> getNotFeaturesByUserId(String userId);

    List<Feature> getNotFeatureByRoleId(String roleId);

    List<Feature> getFeatures(Map<String, Object> mapData);

    int getCountFeature(Map<String, Object> mapData);

}
