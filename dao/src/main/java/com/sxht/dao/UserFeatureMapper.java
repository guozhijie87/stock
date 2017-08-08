package com.sxht.dao;

import com.sxht.model.UserFeature;
import com.sxht.model.UserFeatureExample;
import com.sxht.model.UserFeatureKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFeatureMapper {
    int countByExample(UserFeatureExample example);

    int deleteByExample(UserFeatureExample example);

    int deleteByPrimaryKey(UserFeatureKey key);

    int insert(UserFeature record);

    int insertSelective(UserFeature record);

    List<UserFeature> selectByExample(UserFeatureExample example);

    UserFeature selectByPrimaryKey(UserFeatureKey key);

    int updateByExampleSelective(@Param("record") UserFeature record, @Param("example") UserFeatureExample example);

    int updateByExample(@Param("record") UserFeature record, @Param("example") UserFeatureExample example);

    int updateByPrimaryKeySelective(UserFeature record);

    int updateByPrimaryKey(UserFeature record);

    int deleteByUserId(String userid);

    int deleteByFeatureId(String featureId);
}