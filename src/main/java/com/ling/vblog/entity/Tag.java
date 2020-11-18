package com.ling.vblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;


/**
 * <p>
 *
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@ApiModel(value="Tag对象", description="")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "不能为空")
    private String name;

    @TableLogic
    private Boolean deleted;

    @TableField(exist = false)
    private Long blogsId;

    public Long getBlogsId() {
        return blogsId;
    }

    public Tag() {
    }

    public Tag(Long id, @NotBlank(message = "不能为空") String name, Boolean deleted, Long blogsId) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
        this.blogsId = blogsId;
    }

    public void setBlogsId(Long blogsId) {
        this.blogsId = blogsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Tag{" +
        "id=" + id +
        ", name=" + name +
        ", deleted=" + deleted +
        "}";
    }
}
