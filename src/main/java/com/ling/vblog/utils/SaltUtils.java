package com.ling.vblog.utils;

import cn.hutool.core.text.StrBuilder;

import java.util.Random;

public class SaltUtils {

    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*".toCharArray();
        StrBuilder sb=new StrBuilder();
        for (int i = 0; i <n ; i++) {
            char aChar = chars[new Random().nextInt(chars.length + 1)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
