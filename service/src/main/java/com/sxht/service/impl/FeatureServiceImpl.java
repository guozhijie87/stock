package com.sxht.service.impl;

import com.sxht.dao.FeatureMapper;
import com.sxht.dao.RoleFeatureMapper;
import com.sxht.dao.UserFeatureMapper;
import com.sxht.model.*;
import com.sxht.service.FeatureService;
import com.sxht.common.web.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by lmm on 2017/1/16.
 */
@Service
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    FeatureMapper featureMapper;
    @Autowired
    UserFeatureMapper userFeatureMapper;
    @Autowired
    RoleFeatureMapper roleFeatureMapper;


    @Override
    public void insert(Feature f) {
        featureMapper.insert(f);
    }

    @Override
    public void update(Feature f) {
        featureMapper.updateByPrimaryKey(f);
    }

    @Override
    public void delete(String id) {
        userFeatureMapper.deleteByFeatureId(id);
        roleFeatureMapper.deleteByFeatureId(id);
        featureMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Feature get(String id) {
        return featureMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Feature> getFeatures(int index, int size) {
        return featureMapper.getFeatures(index, size);
    }

    @Override
    public List<TreeNode> tree(boolean checkIsMenu) {
        FeatureExample e = new FeatureExample();
        e.createCriteria().andIsmenuEqualTo(checkIsMenu);
        e.setOrderByClause("id asc");
        List<Feature> all = featureMapper.selectByExample(e);

        return this.buildTree(all);
    }

    public List<TreeNode> getTreeByUser(String userId) {
        List<Feature> source = featureMapper.getFeaturesByUser(userId);

        return this.buildTree(source);
    }

    public List<TreeNode> getTreeByRole(String roleId) {
        List<Feature> source = featureMapper.getFeaturesByRole(roleId);

        return this.buildTree(source);
    }

    protected List<TreeNode> buildTree(List<Feature> source) {
        List<TreeNode> nodes = new ArrayList<>();

        Stream<Feature> roots = source
                .stream()
                .filter(f ->
                        f.getParentid() == null
                                || f.getParentid().isEmpty());

        roots.forEach(f -> {
                    TreeNode node =
                            new TreeNode(
                                    f.getId(),
                                    f.getName(),
                                    f.getUrl(),
                                    null,
                                    null);

                    Set<TreeNode> childs = setTreeChild(node.getId(), source);
                    node.setChilds(childs);

                    nodes.add(node);
                }
        );

        return nodes;
    }


    @Override
    public List<Feature> getFeaturesByRole(String roleId) {
        return featureMapper.getFeaturesByRole(roleId);
    }

    @Override
    public List<Feature> getFeaturesByUser(String userId) {
        return featureMapper.getFeaturesByUser(userId);
    }

    @Override
    public List<Feature> getNotFeaturesByUserId(String userId) {
        List<Feature> notFeatures = new ArrayList<Feature>();
        FeatureExample e = new FeatureExample();
        List<Feature> allFeatures = featureMapper.selectByExample(e);
        List<Feature> hasFeatures = featureMapper.getFeaturesByUser(userId);
        for (int i = 0; i < allFeatures.size(); i++) {
            int count = 0;
            for (int j = 0; j < hasFeatures.size(); j++) {
                if (allFeatures.get(i).getId().equals(hasFeatures.get(j).getId())) {
                    count++;
                }
            }
            if (count == 0) {
                notFeatures.add(allFeatures.get(i));
            }
        }
        return notFeatures;
    }

    @Override
    public List<Feature> getNotFeatureByRoleId(String roleId) {
        List<Feature> notFeatures = new ArrayList<Feature>();
        FeatureExample e = new FeatureExample();
        List<Feature> allFeatures = featureMapper.selectByExample(e);
        List<Feature> hasFeatures = featureMapper.getFeaturesByRole(roleId);
        for (int i = 0; i < allFeatures.size(); i++) {
            int count = 0;
            for (int j = 0; j < hasFeatures.size(); j++) {
                if (allFeatures.get(i).getId().equals(hasFeatures.get(j).getId())) {
                    count++;
                }
            }
            if (count == 0) {
                notFeatures.add(allFeatures.get(i));
            }
        }
        return notFeatures;
    }

    @Override
    public List<Feature> getFeatures(Map<String, Object> mapData) {
        FeatureExamplePage e = new FeatureExamplePage();
        e.setIndex((int) mapData.get("offset"));
        e.setSize((int) mapData.get("size"));
        e.setOrderByClause("id");
        FeatureExamplePage.Criteria c = e.or();
        //c.andFirstnameLike("%"+(String)mapData.get("name")+"%");
        if (mapData.get("name") != null && !"".equals(mapData.get("name"))) {
            c.andNameLike("%" + (String) mapData.get("name") + "%");
        }
        List<Feature> features = featureMapper.selectByExamplePage(e);
        return features;
    }

    @Override
    public int getCountFeature(Map<String, Object> mapData) {
        FeatureExamplePage e = new FeatureExamplePage();
        e.setIndex((int) mapData.get("offset"));
        e.setSize((int) mapData.get("size"));
        e.setOrderByClause("id");
        FeatureExamplePage.Criteria c = e.or();
        //c.andFirstnameLike("%"+(String)mapData.get("name")+"%");
        if (mapData.get("name") != null && !"".equals(mapData.get("name"))) {
            c.andNameLike("%" + (String) mapData.get("name") + "%");
        }
        int count = featureMapper.selectCountByExamplePage(e);
        return count;
    }

    protected Set<TreeNode> setTreeChild(String id, List<Feature> sources) {
        Set<TreeNode> nodes = new HashSet<>();

        Stream<Feature> list = sources.stream()
                .filter(f -> id.equals(f.getParentid()));

        list.forEach(
                f -> {
                    TreeNode node =
                            new TreeNode(
                                    f.getId(),
                                    f.getName(),
                                    f.getUrl(),
                                    null,
                                    null);

                    Set<TreeNode> childs = setTreeChild(node.getId(), sources);
                    node.setChilds(childs);

                    nodes.add(node);
                }
        );

        return nodes;
    }
}
