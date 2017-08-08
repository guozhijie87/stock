package com.sxht.dao;

import com.sxht.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeatureMapper {
    int countByExample(FeatureExample example);

    int deleteByExample(FeatureExample example);

    int deleteByPrimaryKey(String id);

    int insert(Feature record);

    int insertSelective(Feature record);

    List<Feature> selectByExample(FeatureExample example);

    Feature selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Feature record, @Param("example") FeatureExample example);

    int updateByExample(@Param("record") Feature record, @Param("example") FeatureExample example);

    int updateByPrimaryKeySelective(Feature record);

    int updateByPrimaryKey(Feature record);

    List<Feature> getFeatures(int index, int size);

    List<Feature> getFeaturesByRole(String roleId);

    List<Feature> getFeaturesByUser(String userId);

    List<Feature> selectByExamplePage(FeatureExamplePage featureExamplePage);

    int  selectCountByExamplePage(FeatureExamplePage featureExamplePage);
}