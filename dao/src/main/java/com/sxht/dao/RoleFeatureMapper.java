package com.sxht.dao;

import com.sxht.model.RoleFeature;
import com.sxht.model.RoleFeatureExample;
import com.sxht.model.RoleFeatureKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleFeatureMapper {
    int countByExample(RoleFeatureExample example);

    int deleteByExample(RoleFeatureExample example);

    int deleteByPrimaryKey(RoleFeatureKey key);

    int insert(RoleFeature record);

    int insertSelective(RoleFeature record);

    List<RoleFeature> selectByExample(RoleFeatureExample example);

    RoleFeature selectByPrimaryKey(RoleFeatureKey key);

    int updateByExampleSelective(@Param("record") RoleFeature record, @Param("example") RoleFeatureExample example);

    int updateByExample(@Param("record") RoleFeature record, @Param("example") RoleFeatureExample example);

    int updateByPrimaryKeySelective(RoleFeature record);

    int updateByPrimaryKey(RoleFeature record);

    int deleteByRoleId(String roleid);

    int deleteByFeatureId(String featureId);
}