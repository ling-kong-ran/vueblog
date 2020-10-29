package com.ling.vblog.mapper;

import com.ling.vblog.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
public interface TagMapper extends BaseMapper<Tag> {
    @Select("select * from tag order by id desc limit #{current},#{size}")
    List<Tag> selectPage(@Param("current") int current, @Param("size") int size);
}
