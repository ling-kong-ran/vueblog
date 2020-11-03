package com.ling.vblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-28
 */
@ApiModel(value="Blog对象", description="blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "是否开启赞赏")
    private Boolean appreciation;

    @ApiModelProperty(value = "是否开启评论")
    private Boolean commentAbled;

    @NotBlank(message = "内容不能为空")
    @ApiModelProperty(value = "博客主要内容")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String firstPicture;

    @ApiModelProperty(value = "例如：转载、原创、翻译等等")
    private String flag;

    @ApiModelProperty(value = "是否发布状态")
    private Boolean published;

    @ApiModelProperty(value = "是否开启二级评论")
    private Boolean recommend;

    @ApiModelProperty(value = "是否开启分享")
    private Boolean shareStatement;

    @NotBlank(message = "标题不能为空")
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer views;

    private Long typeId;

    private Long userId;

    @NotBlank(message = "描述不能为空")
    private String description;

    @TableLogic
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getCommentAbled() {
        return commentAbled;
    }

    public void setCommentAbled(Boolean commentAbled) {
        this.commentAbled = commentAbled;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(Boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Blog{" +
        "id=" + id +
        ", appreciation=" + appreciation +
        ", commentAbled=" + commentAbled +
        ", content=" + content +
        ", createTime=" + createTime +
        ", firstPicture=" + firstPicture +
        ", flag=" + flag +
        ", published=" + published +
        ", recommend=" + recommend +
        ", shareStatement=" + shareStatement +
        ", title=" + title +
        ", updateTime=" + updateTime +
        ", views=" + views +
        ", typeId=" + typeId +
        ", userId=" + userId +
        ", description=" + description +
        ", deleted=" + deleted +
        "}";
    }
}
