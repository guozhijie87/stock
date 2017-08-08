package com.sxht.service;

import com.sxht.model.UserFeature;

import java.util.List;

/**
 * Created by yanjinxing on 2017-01-19.
 */
public interface UserFeatureService {
    /**
     * 添加用户权限关联
     * @param userFeature
     */
    public void insert(UserFeature userFeature);

    /**
     *  修改用户权限关联
     * @param userFeature
     */
    public void update(UserFeature userFeature);

    /**
     * 删除用户权限关联
     * @param userid
     */
    public void remove(String userid);

    /**
     * 按照id查询用户权限关联
     * @param id
     * @return
     */
    public UserFeature get(String id);

    /**
     * 按照条件分页查询
     * @param index
     * @param size
     * @return
     */
    public List<UserFeature> getUsers(int index, int size);

    /**
     * 给用户赋功能
     * @param features
     */
    public void setFeatures(String features, String userid);
}
