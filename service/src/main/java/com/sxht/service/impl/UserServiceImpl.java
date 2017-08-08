package com.sxht.service.impl;

import com.sxht.dao.UserFeatureMapper;
import com.sxht.dao.UserMapper;
import com.sxht.dao.UserRoleMapper;
import com.sxht.model.*;
import com.sxht.service.UserService;
import com.sxht.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lmm on 2017/1/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserFeatureMapper userFeatureMapper;

    @Override
    public void insert(User user) {
        user.setCreatetime(new Date());
        user.setPassword(EncryptionUtil.MD5(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        user.setLastupgradetime(new Date());
        if (user.getPassword() != null) {
            user.setPassword(EncryptionUtil.MD5(user.getPassword()));
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void remove(String id) {
        userRoleMapper.deleteByUserId(id);
        userFeatureMapper.deleteByUserId(id);
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User get(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUsers(int index, int size) {
        UserExample example = new UserExample();
        return userMapper.selectByExample(null);
    }

    @Override
    public Map<String, Object> getUsers(int index, int size, String roleId) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("roleid", roleId);
        data.put("index", index);
        data.put("size", size);
        int count = userMapper.countList(data);
        result.put("count", count);
        List<User> userList = userMapper.queryList(data);
        result.put("list", userList);
        return result;
    }

    @Override
    public Map<String, Object> getUsersByWhere(int index, int size, String where) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("whereData", where);
        data.put("index", index);
        data.put("size", size);
        int count = userMapper.countList(data);
        result.put("count", count);
        List<User> userList = userMapper.queryList(data);
        result.put("list", userList);
        return result;
    }

    @Override
    public List<User> getUserByRoleId(String roleId) {
        return userMapper.getUserByRoleId(roleId);
    }

    @Override
    public List<User> getUsers(Map<String, Object> mapData) {
        UserExamplePage e = new UserExamplePage();
        e.setIndex((int)mapData.get("offset"));
        e.setSize((int)mapData.get("size"));
        e.setOrderByClause("createtime desc");
        UserExamplePage.Criteria c = e.or();
        //c.andFirstnameLike("%"+(String)mapData.get("name")+"%");
        if(mapData.get("userid")!=null&&!"".equals(mapData.get("userid"))){
            c.andUseridLike("%"+(String)mapData.get("userid")+"%");
        }
        if(mapData.get("name")!=null&&!"".equals(mapData.get("name"))){
            c.andFirstnameLike("%"+(String)mapData.get("name")+"%");
        }
        List<User> users = userMapper.selectByExamplePage(e);
        return users;
    }

    @Override
    public int getCountUser(Map<String, Object> mapData) {
        UserExamplePage e = new UserExamplePage();
        UserExamplePage.Criteria c = e.createCriteria();
        if(mapData.get("userid")!=null&&!"".equals(mapData.get("userid"))){
            c.andUseridLike("%"+(String)mapData.get("userid")+"%");
        }
        if(mapData.get("name")!=null&&!"".equals(mapData.get("name"))){
            c.andFirstnameLike("%"+(String)mapData.get("name")+"%");
        }
        int count=userMapper.selectCountByExamplePage(e);
        return count;
    }

    @Override
    public List<User> getNotUserByRoleId(String roleId) {
        List<User> notUsers=new ArrayList<User>();
        UserExample e = new UserExample();
        List<User> allUsers= userMapper.selectByExample(e);
        List<User> hasUsers= userMapper.getUserByRoleId(roleId);
        for(int i=0;i<allUsers.size();i++){
            int count=0;
            for(int j=0;j<hasUsers.size();j++){
                if(allUsers.get(i).getUserid().equals(hasUsers.get(j).getUserid())){
                    count++;
                }
            }
            if(count==0){
                notUsers.add(allUsers.get(i));
            }
        }
        return notUsers;
    }

}
