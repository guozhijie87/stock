package com.sxht.dao;

import com.sxht.model.ImageUpload;
import com.sxht.model.ImageUploadExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by LJ on 2017/3/24.
 */
public interface ImageUploadMapper {
    int countByExample(ImageUploadExample example);

    int deleteByExample(ImageUploadExample example);

    int deleteByPrimaryKey(Long imageid);

    int insert(ImageUpload record);

    int insertSelective(ImageUpload record);

    List<ImageUpload> selectByExample(ImageUploadExample example);

    ImageUpload selectByPrimaryKey(Long imageid);

    int updateByExampleSelective(@Param("record") ImageUpload record, @Param("example") ImageUploadExample example);

    int updateByExample(@Param("record") ImageUpload record, @Param("example") ImageUploadExample example);

    int updateByPrimaryKeySelective(ImageUpload record);

    int updateByPrimaryKey(ImageUpload record);
}
