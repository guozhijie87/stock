package com.sxht.service.impl;

import com.sxht.dao.RoleFeatureMapper;
import com.sxht.model.RoleFeature;
import com.sxht.model.RoleFeatureExample;
import com.sxht.model.RoleFeatureKey;
import com.sxht.service.RoleFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lmm on 2017/1/19.
 */
@Service
public class RoleFeatureServiceImpl implements RoleFeatureService {
    @Autowired
    RoleFeatureMapper roleFeatureMapper;

    @Override
    public void insert(RoleFeature m) {
        roleFeatureMapper.insert(m);
    }

    @Override
    public void update(RoleFeature m) {
        roleFeatureMapper.updateByPrimaryKey(m);
    }

    @Override
    public void delete(String roleId, String featureId) {
        roleFeatureMapper.deleteByPrimaryKey(this.getKey(roleId, featureId));
    }


    @Override
    public RoleFeature get(String roleId, String featureId) {
        return roleFeatureMapper.selectByPrimaryKey(this.getKey(roleId, featureId));
    }

    @Override
    public List<RoleFeature> getAll() {
        RoleFeatureExample e = new RoleFeatureExample();
        return roleFeatureMapper.selectByExample(e);
    }

    @Override
    public int removeByRoleId(String roleId) {
        return roleFeatureMapper.deleteByRoleId(roleId);
    }

    @Override
    public void setFeatures(String roleId, String features) {
        removeByRoleId(roleId);
        if(features!=null&&!"".equals(features)){
            String featureId[]=features.split(",");
            RoleFeature roleFeature=null;
            for(int i=0;i<featureId.length;i++){
                roleFeature=new RoleFeature();
                roleFeature.setRoleid(roleId);
                roleFeature.setFeatureid(featureId[i]);
                roleFeature.setVisible(true);
                roleFeature.setEnable(true);
                insert(roleFeature);
            }
        }
    }


    protected RoleFeatureKey getKey(String roleId, String featureId) {
        RoleFeatureKey key = new RoleFeatureKey();
        key.setFeatureid(featureId);
        key.setRoleid(roleId);

        return key;
    }
}
