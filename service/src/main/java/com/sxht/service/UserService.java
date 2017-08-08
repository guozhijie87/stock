package com.sxht.service;

import com.sxht.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lmm on 2017/1/9.
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     */
    public void insert(User user);

    /**
     *  修改用户
     * @param user
     */
    public void update(User user);

    /**
     * 删除用户
     * @param id
     */
    public void remove(String id);

    /**
     * 按照id查询用户
     * @param id
     * @return
     */
    public User get(String id);

    /**
     * 按照条件分页查询
     * @param index
     * @param size
     * @return
     */
    public List<User> getUsers(int index, int size);

    /**
     * 按照角色id查询用户信息
     * @param index
     * @param size
     * @param roleId
     * @return
     */
    public Map<String,Object> getUsers(int index, int size, String roleId);

    /**
     *
     * @param index
     * @param size
     * @param where
     * @return
     */
    public Map<String,Object> getUsersByWhere(int index, int size, String where);

    /**
     * 查询角色下的用户信息
     * @param roleId
     * @return
     */
    public List<User> getUserByRoleId(String roleId);

    public List<User> getUsers(Map<String, Object> mapData);

    public int getCountUser(Map<String, Object> mapData);

    public List<User> getNotUserByRoleId(String roleId);

}
