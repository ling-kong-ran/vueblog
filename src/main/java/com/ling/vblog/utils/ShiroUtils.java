package com.ling.vblog.utils;

import com.ling.vblog.shiro.Profile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {
    public static Profile getProfile(){
        return (Profile) SecurityUtils.getSubject().getPrincipal();
    }
}
