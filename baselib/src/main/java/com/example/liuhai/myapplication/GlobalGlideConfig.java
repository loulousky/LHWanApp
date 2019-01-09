package com.example.liuhai.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 作者：liuhai
 * 时间：2019/1/9:17:10
 * 邮箱：185587041@qq.com
 * 说明：Glide的全局配置 包名必须和应用包名统一
 */
@GlideModule
public class GlobalGlideConfig extends AppGlideModule {


    @Override
    public boolean isManifestParsingEnabled() {
//    return super.isManifestParsingEnabled();

        return false;

    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);

    }
}
