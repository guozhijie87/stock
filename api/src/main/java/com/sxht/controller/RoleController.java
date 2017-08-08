package com.sxht.controller;

import com.sxht.model.Feature;
import com.sxht.model.Role;
import com.sxht.model.User;
import com.sxht.service.*;
import com.sxht.common.web.Result;
import com.sxht.common.web.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lmm on 2017/1/16.
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleFeatureService roleFeatureService;
    @Autowired
    UserService userService;
    @Autowired
    FeatureService featureService;

    @PostMapping("add")
    public Result add(Role o) {
        try {
            roleService.insert(o);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("update")
    public Result update(Role o) {
        try {
            roleService.update(o);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("delete")
    public Result delete(String id) {
        try {
            roleService.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("get")
    public Result get(String id) {
        try {
            Role o = roleService.get(id);
            return Result.success(o);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("getNotRolesByUserId")
    public Result getNotRolesByUserId(String userId) {
        try {
            List<Role> list = roleService.getNotRolesByUserId(userId);
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("all")
    public Result all() {
        try {
            List<Role> list = roleService.getAll();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("getRoles")
    public Result getRoles(HttpServletRequest request, @RequestBody Map<String, Object> mapData) {
        try {
            System.out.print("执行角色查询操作！！");
            System.out.print(mapData.toString() + "--------------------");
            Map<String,Object> retult=new HashMap<String,Object>();
            int index=(int)mapData.get("index");
            int size=(int)mapData.get("size");
            int offset = index * size;
            mapData.put("offset",offset);
            List<Role> roles= roleService.getRoles(mapData);
            int count=roleService.getCountRole(mapData);
            retult.put("list",roles);
            retult.put("count",count);
            return Result.success(retult);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 给角色赋用户
     *
     * @param roleId
     * @param userids
     * @return
     */
    @PostMapping(value = "setUsers")
    public Result setUsers(String roleId, String userids) {

        try {
            System.out.print("执行角色赋用户操作！！");
            //判断roles不为空
            if (StringUtils.isEmpty(roleId)) {
                return Result.error("roleId is null");
            }
            userRoleService.setUsers(roleId, userids);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 给角色赋功能
     *
     * @param roleId
     * @param features
     * @return
     */
    @PostMapping(value = "setFeatures")
    public Result setFeature(String roleId, String features) {

        try {
            System.out.print("执行角色赋功能操作！！");
            //判断roleid不为空
            if (StringUtils.isEmpty(roleId)) {
                return Result.error("roleId is null");
            }
            roleFeatureService.setFeatures(roleId, features);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("tree")
    public Result tree() {
        try {
            Set<TreeNode> nodes = roleService.tree();

            return Result.success(nodes);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("getTreeByUser")
    public Result getTreeByUser(String userId) {
        try {
            Set<TreeNode> nodes = roleService.getTreeByUser(userId);
            return Result.success(nodes);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping(value = "getUsersByRoleId")
    public Result getUsersByRoleId(int index, int size, String roleId) {

        try {
            System.out.print("执行查询角色下用户信息功能操作！！");
            //判断roleid不为空
            if (StringUtils.isEmpty(roleId)) {
                return Result.error("roleId is null");
            }
            Map<String,Object> map=userService.getUsers(index,size,roleId);
            return Result.success(map);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 查询角色下的用户信息
     * @param roleId
     * @return
     */
    @PostMapping(value = "getUserByRoleId")
    public Result getUserByRoleId(String roleId) {

        try {
            System.out.print("执行查询角色下用户信息功能操作！！");
            //判断roleid不为空
            if (StringUtils.isEmpty(roleId)) {
                return Result.error("roleId is null");
            }
            List<User> listUser=userService.getUserByRoleId(roleId);
            return Result.success(listUser);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping(value = "getNotUserByRoleId")
    public Result getNotUsesrByRoleId(String roleId) {

        try {
            //判断roleid不为空
            if (StringUtils.isEmpty(roleId)) {
                return Result.error("roleId is null");
            }
            List<User> listUser=userService.getNotUserByRoleId(roleId);
            return Result.success(listUser);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 查询角色下的功能信息
     * @param roleId
     * @return
     */
    @PostMapping(value = "getFeatureByRoleId")
    public Result getFeatureByRoleId(String roleId) {

        try {
            System.out.print("执行查询角色下功能信息功能操作！！");
            //判断roleid不为空
            if (StringUtils.isEmpty(roleId)) {
                return Result.error("roleId is null");
            }
            List<Feature> listUser=featureService.getFeaturesByRole(roleId);
            return Result.success(listUser);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping(value = "getNotFeatureByRoleId")
    public Result getNotFeatureByRoleId(String roleId) {

        try {
            System.out.print("执行查询角色下功能信息功能操作！！");
            //判断roleid不为空
            if (StringUtils.isEmpty(roleId)) {
                return Result.error("roleId is null");
            }
            List<Feature> listUser=featureService.getNotFeatureByRoleId(roleId);
            return Result.success(listUser);
        } catch (Exception e) {
            return Result.error(e);
        }
    }


}
