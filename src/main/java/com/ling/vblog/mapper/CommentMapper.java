package com.ling.vblog.mapper;

import com.ling.vblog.entity.Comment;
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
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select * from comment where blog_id=#{blogId} and deleted=0 order by create_time desc")
    List<Comment> getByBlogId(@Param("blogId") Integer blogId);
}
