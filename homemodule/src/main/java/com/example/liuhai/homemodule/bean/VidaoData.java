package com.example.liuhai.homemodule.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 作者：liuhai
 * 时间：2019/1/22:10:04
 * 邮箱：185587041@qq.com
 * 说明：视频的详细BEAN
 */
public class VidaoData implements Serializable {
    @SerializedName("desc")
    public String desc;
    @SerializedName("ganhuo_id")
    public String ganhuo_id;
    @SerializedName("publishedAt")
    public String publishedAt;
    @SerializedName("readability")
    public String readability;
    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String url;
    @SerializedName("who")
    public String who;
}
