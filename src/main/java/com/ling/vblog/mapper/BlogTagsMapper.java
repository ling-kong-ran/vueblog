package com.ling.vblog.mapper;

import com.ling.vblog.entity.BlogTags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ling.vblog.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
public interface BlogTagsMapper extends BaseMapper<BlogTags> {
  void addBlogTags(List<Tag> blogTags);

  void deleteBlogTags(@Param("blogId") Long blogId);
}
