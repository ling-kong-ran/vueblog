package com.ling.vblog.shiro;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String nickname;
}
