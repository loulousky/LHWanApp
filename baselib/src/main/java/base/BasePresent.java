package base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * 作者：liuhai
 * 时间：2019/1/10:11:09
 * 邮箱：185587041@qq.com
 * 说明：P层基本接口 实现Android的生命周期监听 更好的管理内存
 */
public interface BasePresent extends LifecycleObserver {

    //创建的时候做初始化
     @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
     public  void  onAttachView();

    //销魂的时候做释放
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
     public void onDettachView();


}
