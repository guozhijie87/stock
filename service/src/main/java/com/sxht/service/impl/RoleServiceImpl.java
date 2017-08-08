package com.sxht.service.impl;

import com.sxht.dao.RoleFeatureMapper;
import com.sxht.dao.RoleMapper;
import com.sxht.dao.UserRoleMapper;
import com.sxht.model.*;
import com.sxht.service.RoleService;
import com.sxht.common.web.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by lmm on 2017/1/16.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleFeatureMapper  roleFeatureMapper;

    @Override
    public void insert(Role o) {
        roleMapper.insert(o);
    }

    @Override
    public void update(Role o) {
        roleMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public void delete(String id) {
        userRoleMapper.deleteByRoleId(id);
        roleFeatureMapper.deleteByRoleId(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role get(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> getAll() {
        RoleExample e = new RoleExample();

        return roleMapper.selectByExample(e);
    }

    @Override
    public Set<TreeNode> tree() {
        RoleExample e = new RoleExample();

        e.setOrderByClause("id desc");

        List<Role> all = roleMapper.selectByExample(new RoleExample());

        return buildTree(all);
    }

    protected Set<TreeNode> buildTree(List<Role> sources) {

        Set<TreeNode> nodes = new HashSet<>();

        Stream<Role> roots = sources
                .stream()
                .filter(f -> f.getParentid() == null);

        roots.forEach(f -> {
                    TreeNode node =
                            new TreeNode(
                                    f.getId(),
                                    f.getName(),
                                    "",
                                    null,
                                    null);

                    Set<TreeNode> childs = setTreeChild(node.getId(), sources);
                    node.setChilds(childs);

                    nodes.add(node);
                }
        );

        return nodes;
    }


    protected Set<TreeNode> setTreeChild(String id, List<Role> sources) {
        Set<TreeNode> nodes = new HashSet<>();

        Stream<Role> list = sources.stream()
                .filter(f -> id.equals(f.getParentid()));

        list.forEach(
                f -> {
                    TreeNode node =
                            new TreeNode(
                                    f.getId(),
                                    f.getName(),
                                    "",
                                    null,
                                    null);

                    Set<TreeNode> childs = setTreeChild(node.getId(), sources);
                    node.setChilds(childs);

                    nodes.add(node);
                }
        );

        return nodes;
    }

    public Set<TreeNode> getTreeByUser(String userId) {
        List<Role> source = roleMapper.getRolesByUser(userId);

        return buildTree(source);
    }

    public List<Role> getRolesByUser(String userId) {
        return roleMapper.getRolesByUser(userId);
    }

    @Override
    public List<Role> getNotRolesByUserId(String userId) {
        List<Role> notRoles=new ArrayList<Role>();
        RoleExample e = new RoleExample();
        List<Role> allRoles= roleMapper.selectByExample(e);
        List<Role> hasRoles= roleMapper.getRolesByUser(userId);
        for(int i=0;i<allRoles.size();i++){
            int count=0;
            for(int j=0;j<hasRoles.size();j++){
                if(allRoles.get(i).getId().equals(hasRoles.get(j).getId())){
                    count++;
                }
            }
            if(count==0){
                notRoles.add(allRoles.get(i));
            }
        }
        return notRoles;
    }

    @Override
    public List<Role> getRoles(Map<String, Object> mapData) {
        RoleExamplePage e = new RoleExamplePage();
        e.setIndex((int)mapData.get("offset"));
        e.setSize((int)mapData.get("size"));
        e.setOrderByClause("id");
        RoleExamplePage.Criteria c = e.or();
        //c.andFirstnameLike("%"+(String)mapData.get("name")+"%");
        if(mapData.get("name")!=null&&!"".equals(mapData.get("name"))){
            c.andNameLike("%"+(String)mapData.get("name")+"%");
        }
        List<Role> roles = roleMapper.selectByExamplePage(e);
        return roles;
    }

    @Override
    public int getCountRole(Map<String, Object> mapData) {
        RoleExamplePage e = new RoleExamplePage();
        e.setIndex((int)mapData.get("offset"));
        e.setSize((int)mapData.get("size"));
        e.setOrderByClause("id");
        RoleExamplePage.Criteria c = e.or();
        //c.andFirstnameLike("%"+(String)mapData.get("name")+"%");
        if(mapData.get("name")!=null&&!"".equals(mapData.get("name"))){
            c.andNameLike("%"+(String)mapData.get("name")+"%");
        }
        int count = roleMapper.selectCountByExamplePage(e);
        return count;
    }
}
