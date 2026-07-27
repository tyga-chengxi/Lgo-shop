package com.lgoshop.mapper;

import com.lgoshop.model.PmsAlbumPic;
import com.lgoshop.model.PmsAlbumPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsAlbumPicMapper extends BaseMapper<PmsAlbumPic> {
    long countByExample(PmsAlbumPicExample example);
    int deleteByExample(PmsAlbumPicExample example);
    int insert(PmsAlbumPic record);
    int insertSelective(PmsAlbumPic record);
    List<PmsAlbumPic> selectByExample(PmsAlbumPicExample example);
    int updateByExampleSelective(@Param("record") PmsAlbumPic record, @Param("example") PmsAlbumPicExample example);
    int updateByExample(@Param("record") PmsAlbumPic record, @Param("example") PmsAlbumPicExample example);
}
