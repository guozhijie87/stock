package com.sxht.service.impl;

import com.sxht.dao.UserRoleMapper;
import com.sxht.model.UserRole;
import com.sxht.model.UserRoleExample;
import com.sxht.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanjinxing on 2017-01-19.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void insert(UserRole userRole) {
        userRoleMapper.insert(userRole);
    }

    @Override
    public void update(UserRole userRole) {
//        userRoleMapper.updateByExampleSelective(map);
    }

    @Override
    public void remove(String userid) {
        userRoleMapper.deleteByUserId(userid);
    }

    @Override
    public void removeByRoleId(String roleId) {
        userRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public UserRole get(String id) {
        return null;
    }

    @Override
    public List<UserRole> getUsers(int index, int size) {
        UserRoleExample userRoleExample=new UserRoleExample();
        return userRoleMapper.selectByExample(userRoleExample);
    }

    @Override
    public void setRoles(String roles,String userid) {
        remove(userid);
        if(roles!=null&&!"".equals(roles)){
            String roleId[]=roles.split(",");
            UserRole userRole=null;
            for(int i=0;i<roleId.length;i++){
                userRole=new UserRole();
                userRole.setRoleid(roleId[i]);
                userRole.setUserid(userid);
                insert(userRole);
            }
        }
    }

    @Override
    public void setUsers(String roleId, String userids) {
        removeByRoleId(roleId);
        if(userids!=null&&!"".equals(userids)){
            String userId[]=userids.split(",");
            UserRole userRole=null;
            for(int i=0;i<userId.length;i++){
                userRole=new UserRole();
                userRole.setRoleid(roleId);
                userRole.setUserid(userId[i]);
                insert(userRole);
            }
        }
    }
}
