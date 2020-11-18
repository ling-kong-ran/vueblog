package com.ling.vblog.mapper;

import com.ling.vblog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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
    @Select("select * from blog order by update_time desc limit #{current},#{size}")
    List<Blog> selectPage(@Param("current") int currentPage, @Param("size")  int size);
    @Update("update blog set views = views+1 where id=#{id}")
    void updateView(@Param("id") Integer id);

    Blog getOneBlog(@Param("id") Integer id);

    @Select("select * from blog where title =#{title}")
    Blog getByTitle(String title);

    @Select("select count(1) from blog")
    Integer count();
}
