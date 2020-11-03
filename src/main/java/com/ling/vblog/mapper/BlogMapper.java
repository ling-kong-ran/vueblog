package com.ling.vblog.mapper;

import com.ling.vblog.entity.Blog;
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
public interface BlogMapper extends BaseMapper<Blog> {
    @Select("select * from blog order by create_time desc limit #{current},#{size}")
    List<Blog> selectPage(@Param("current") int currentPage, @Param("size")  int size);
}
