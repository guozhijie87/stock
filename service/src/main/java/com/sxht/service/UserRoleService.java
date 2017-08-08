package com.sxht.service;

import com.sxht.model.UserRole;

import java.util.List;

/**
 * Created by yanjinxing on 2017-01-19.
 */
public interface UserRoleService {
    /**
     * 添加用户权限关联
     * @param userRole
     */
    public void insert(UserRole userRole);

    /**
     *  修改用户权限关联
     * @param userRole
     */
    public void update(UserRole userRole);

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
    public UserRole get(String id);

    /**
     * 按照条件分页查询
     * @param index
     * @param size
     * @return
     */
    public List<UserRole> getUsers(int index, int size);

    /**
     * 给用户赋值角色
     * @param roles
     */
    public void setRoles(String roles, String userid);

    /**
     * 给角色赋用户
     * @param roleId
     * @param userids
     */
    public void setUsers(String roleId, String userids);

    /**
     * 按照roleId删除
     * @param roleId
     */
    public void removeByRoleId(String roleId);
}
