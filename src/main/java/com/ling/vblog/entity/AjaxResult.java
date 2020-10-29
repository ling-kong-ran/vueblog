package com.ling.vblog.entity;

import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;
    public AjaxResult(){

    }
    public AjaxResult(boolean status, String msg){
        super.put("status",status);
        super.put("msg",msg);
    }

    public AjaxResult(boolean status, String msg, Object data)
    {
        super.put("status",status);
        super.put("msg",msg);
        if (ObjectUtils.isNotEmpty(data))
        {
            super.put("data", data);
        }
    }
    public static AjaxResult success(){
        return AjaxResult.success("操作成功");
    }
    public static AjaxResult success(Object data){
        return AjaxResult.success("操作成功",data);
    }

    public static  AjaxResult success(String msg){
        return new AjaxResult(true,msg);
    }
    public static AjaxResult success(String msg, Object data){
        return new AjaxResult(true,msg,data);
    }

    public static AjaxResult error(){
        return AjaxResult.error("操作失败");
    }
    public static AjaxResult error(Object data){
        return AjaxResult.error("操作失败",data);
    }

    public static AjaxResult error(String msg){
        return new AjaxResult(false,msg);
    }
    public static AjaxResult error(String msg, Object data){
        return new AjaxResult(false,msg,data);
    }
}
