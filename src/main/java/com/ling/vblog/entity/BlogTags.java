package com.ling.vblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 *
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@ApiModel(value="BlogTags对象", description="")
public class BlogTags implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "blogs_id", type = IdType.AUTO)
    private Long blogsId;

    private Long tagsId;

    @TableLogic
    private Boolean deleted;


    public Long getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(Long blogsId) {
        this.blogsId = blogsId;
    }

    public Long getTagsId() {
        return tagsId;
    }

    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "BlogTags{" +
        "blogsId=" + blogsId +
        ", tagsId=" + tagsId +
        ", deleted=" + deleted +
        "}";
    }
}
