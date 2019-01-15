package wiget;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.example.liuhai.baselib.R;


/**
 * 作者：liuhai
 * 时间：2019/1/15:14:53
 * 邮箱：185587041@qq.com
 * 说明：可以自定义圆形背景的imageview
 */
public class CircleImageVIew  extends android.support.v7.widget.AppCompatImageView {

    private Paint paint;


    public float translateY;

    private ObjectAnimator animator;

    public float getTranslateY() {
        return translateY;
    }

    public void setTranslateY(float translateY) {
        this.translateY = translateY;
        Log.d("是否移动",translateY+"");
        invalidate();
    }

    public CircleImageVIew(Context context) {
        super(context);
        init();
    }

    public CircleImageVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleImageVIew(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        animator=ObjectAnimator.ofFloat(this,"translateY",0,-80,0);
        animator.setDuration(2000);
        animator.setInterpolator(new BounceInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
    }

    /**
     * 设置圆形颜色
     * @param colorRes1
     */
    public void setColorRes(int colorRes1) {
        paint.setColor(getResources().getColor(colorRes1));
        invalidate();
    }

    public void start(){
        animator.start();
    }

    public void stop(){
        animator.cancel();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(0,translateY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2+10,paint);
        super.onDraw(canvas);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.cancel();
    }
}
