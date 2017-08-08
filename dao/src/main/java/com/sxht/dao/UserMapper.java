package com.sxht.dao;

import com.sxht.model.User;
import com.sxht.model.UserExample;
import com.sxht.model.UserExamplePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 条件查询数据
     * @param data
     * @return
     */
    List<User>  queryList(Map<String, Object> data);

    /**
     * 查询结果总条数
     * @param data
     * @return
     */
    int countList(Map<String, Object> data);

    /**
     * 按照userid查询数据
     * @param userid
     * @return
     */
    User queryByUserId(String userid);

    /**
     *
     * @param roleId
     * @return
     */
    List<User> getUserByRoleId(String roleId);

    /**
     * 条件查询数据(新版)
     * @param data
     * @return
     */
    List<User>  queryListByPage(Map<String, Object> data);

    /**
     * 查询结果总条数（新版）
     * @param data
     * @return
     */
    int countListByPage(Map<String, Object> data);

    List<User> selectByExamplePage(UserExamplePage userExamplePage);

    int  selectCountByExamplePage(UserExamplePage userExamplePage);

}