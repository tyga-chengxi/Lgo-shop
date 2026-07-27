package com.lgoshop.mapper;

import com.lgoshop.model.PmsAlbum;
import com.lgoshop.model.PmsAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsAlbumMapper extends BaseMapper<PmsAlbum> {
    long countByExample(PmsAlbumExample example);
    int deleteByExample(PmsAlbumExample example);
    int insert(PmsAlbum record);
    int insertSelective(PmsAlbum record);
    List<PmsAlbum> selectByExample(PmsAlbumExample example);
    int updateByExampleSelective(@Param("record") PmsAlbum record, @Param("example") PmsAlbumExample example);
    int updateByExample(@Param("record") PmsAlbum record, @Param("example") PmsAlbumExample example);
}
