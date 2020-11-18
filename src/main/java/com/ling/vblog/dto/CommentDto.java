package com.ling.vblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ling.vblog.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommentDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String img;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private String nickname;
    private Long blogId;
    private Long parentCommentId;
    private Boolean deleted;
    private List<Comment> comment_son;

}
