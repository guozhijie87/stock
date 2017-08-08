package com.sxht.service.impl;

import com.sxht.dao.UserFeatureMapper;
import com.sxht.model.UserFeature;
import com.sxht.service.UserFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanjinxing on 2017-01-19.
 */
@Service
public class UserFeatureServiceImpl implements UserFeatureService {

    @Autowired
    private UserFeatureMapper userFeatureMapper;

    @Override
    public void insert(UserFeature userFeature) {
        userFeatureMapper.insert(userFeature);
    }

    @Override
    public void update(UserFeature userFeature) {

    }

    @Override
    public void remove(String userid) {
        userFeatureMapper.deleteByUserId(userid);
    }

    @Override
    public UserFeature get(String id) {
        return null;
    }

    @Override
    public List<UserFeature> getUsers(int index, int size) {
        return null;
    }

    @Override
    public void setFeatures(String features,String userid) {
        remove(userid);
        if(features!=null&&!"".equals(features)){
            String featureId[]=features.split(",");
            UserFeature userFeature=null;
            for(int i=0;i<featureId.length;i++){
                userFeature=new UserFeature();
                userFeature.setUserid(userid);
                userFeature.setFeatureid(featureId[i]);
                userFeature.setVisible(true);
                userFeature.setEnable(true);
                insert(userFeature);
            }
        }
    }
}
