package com.example.liuhai.homemodule.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：liuhai
 * 时间：2019/1/22:10:03
 * 邮箱：185587041@qq.com
 * 说明：视频数据基础返回
 */
public class BaseData implements Serializable {

  @SerializedName("count")
  public  int count;
  @SerializedName("error")
  public  boolean error;
  @SerializedName("results")
  public List<VidaoData> results;

}
