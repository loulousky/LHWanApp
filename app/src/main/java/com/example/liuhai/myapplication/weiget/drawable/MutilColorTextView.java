package com.example.liuhai.myapplication.weiget.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 作者：liuhai
 * 时间：2019/1/16:16:00
 * 邮箱：185587041@qq.com
 * 说明：渐变色文字
 */
public class MutilColorTextView extends android.support.v7.widget.AppCompatTextView {

    private Paint paint;
    private String text;
    private Rect mTextBound = new Rect();

    {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);


    }

    public MutilColorTextView(Context context) {
        super(context);


    }

    public MutilColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MutilColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        text = (String) getText();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
       Paint.FontMetrics metrics= paint.getFontMetrics();
        paint.getTextBounds(text, 0, text.length(), mTextBound);
        paint.setTextSize(getTextSize());
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setShader(new LinearGradient(0, 0, getWidth(), getHeight(), new int[]{0x770055aa, 0xFF9400D3}, null, Shader.TileMode.REPEAT));
        canvas.drawText(text, getWidth() / 2-mTextBound.left, getHeight() / 2-(metrics.ascent+metrics.descent)/2 , paint);


    }
}
