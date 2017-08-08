package com.sxht.controller;

import com.sxht.model.Feature;
import com.sxht.model.Role;
import com.sxht.model.User;
import com.sxht.service.*;
import com.sxht.util.SessionVariable;
import com.sxht.common.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmm on 2017/1/16.
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserFeatureService userFeatureService;
    @Autowired
    RoleService roleService;
    @Autowired
    FeatureService featureService;


    /**
     * 用户添加
     *
     * @param user（用户实体）
     * @return
     */
    @PostMapping(value = "add")
    public Result add(User user) {

        try {
            System.out.print("执行用户添加操作！！");
            //判断userid是否为空
            if (StringUtils.isEmpty(user.getUserid())) {
                return Result.error("userid is null");
            }
            //判断password是否为空
            if (StringUtils.isEmpty(user.getPassword())) {
                return Result.error("password is null");
            }
            userService.insert(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping(value = "update")
    public Result update(User user) {
        try {
            userService.update(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 用户删除
     *
     * @param userid
     * @return
     */
    @PostMapping(value = "delete")
    public Result delete(String userid) {
        try {
            //判断userid是否为空
            if (StringUtils.isEmpty(userid)) {
                return Result.error("userid is null");
            }
            userService.remove(userid);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @PostMapping("get")
    public Result get(String id) {
        try {
            User user = userService.get(id);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 分页查询数据
     *
     * @param index
     * @param size
     * @return
     */
    @PostMapping(value = "select")
    public Result select(int index, int size, String where, HttpServletRequest request) {
        try {
            int offset = index * size;

            Map<String, Object> map = userService.getUsersByWhere(offset, size, where);
            return Result.success(map);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 分页查询数据(新版)
     * //@param index
     * //@param size
     *
     * @return
     */
    @PostMapping(value = "getUsers")
    public Result getUsers(HttpServletRequest request, @RequestBody Map<String, Object> mapData) {
        try {
            System.out.print("执行用户查询操作！！");
            String user=(String)request.getSession().getAttribute(SessionVariable.USER_ID);
            System.out.print(user+"+++++++++++++++++++++++++");
            System.out.print(mapData.toString() + "--------------------");
            Map<String,Object> retult=new HashMap<String,Object>();
            int index=(int)mapData.get("index");
            int size=(int)mapData.get("size");
            int offset = index * size;
            mapData.put("offset",offset);
            List<User> users= userService.getUsers(mapData);
            int count=userService.getCountUser(mapData);
            retult.put("list",users);
            retult.put("count",count);
            return Result.success(retult);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e);
        }
    }

    /**
     * 给用户赋角色
     *
     * @param roles
     * @param userid
     * @return
     */
    @PostMapping(value = "setRoles")
    public Result setRoles(String roles, String userid) {

        try {
            System.out.print("执行用户赋角色操作！！");
            //判断userid不为空
            if (StringUtils.isEmpty(userid)) {
                return Result.error("userid is null");
            }
            userRoleService.setRoles(roles, userid);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 给用户赋功能
     *
     * @param features
     * @param userid
     * @return
     */
    @PostMapping(value = "setFeatures")
    public Result setFeature(String features, String userid) {

        try {
            System.out.print("执行用户赋功能操作！！");
            //判断userid不为空
            if (StringUtils.isEmpty(userid)) {
                return Result.error("userid is null");
            }
            userFeatureService.setFeatures(features, userid);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 获取用户下角色信息
     *
     * @return
     */
    @PostMapping(value = "getRolesByUserId")
    public Result getRolesByUserId(HttpServletRequest request, String userid) {

        try {
            System.out.print("执行获取用户下角色信息操作！！");
            //判断userid不为空
            if (StringUtils.isEmpty(userid)) {
                return Result.error("userid is null");
            }
            List<Role> listRole = roleService.getRolesByUser(userid);
            return Result.success(listRole);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    /**
     * 按照userid查询用户下的功能信息
     *
     * @param request
     * @param userid
     * @return
     */
    @PostMapping(value = "getFeatureByUserId")
    public Result getFeatureByUserId(HttpServletRequest request, String userid) {

        try {
            System.out.print("执行获取用户下功能信息操作！！");
            //String userid=(String)request.getSession().getAttribute(SessionVariable.USER_ID);
            //判断userid不为空
            if (StringUtils.isEmpty(userid)) {
                return Result.error("userid is null");
            }
            List<Feature> listFeature = featureService.getFeaturesByUser(userid);
            return Result.success(listFeature);
        } catch (Exception e) {
            return Result.error(e);
        }
    }


}
