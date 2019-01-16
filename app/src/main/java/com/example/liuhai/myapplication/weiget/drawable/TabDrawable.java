package com.example.liuhai.myapplication.weiget.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * 作者：liuhai
 * 时间：2019/1/16:14:36
 * 邮箱：185587041@qq.com
 * 说明：主TAB基础底色
 */
public class TabDrawable extends BitmapDrawable {
    Paint paint = new Paint();

    private int alphe = 0;

    @Override
    public void applyTheme(Resources.Theme t) {


    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.parseColor("#082E54"));
        paint.setAlpha(alphe);
        canvas.drawPaint(paint);

        canvas.drawColor(Color.parseColor("#cc082E54"));//


    }


    @Override
    public void setAlpha(int alpha) {
        alpha = alpha;
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }


}
