package com.sxht.service;

import com.sxht.model.Role;
import com.sxht.common.web.TreeNode;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lmm on 2017/1/9.
 */
public interface RoleService {
    void insert(Role o);

    void update(Role o);

    void delete(String id);

    Role get(String id);

    List<Role> getAll();

    Set<TreeNode> tree();

    Set<TreeNode> getTreeByUser(String userId);

    List<Role> getRolesByUser(String userId);

    List<Role> getNotRolesByUserId(String userId);

    public List<Role> getRoles(Map<String, Object> mapData);

    public int getCountRole(Map<String, Object> mapData);
}
