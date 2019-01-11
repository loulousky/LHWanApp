package base;

import android.app.Application;
import android.content.Context;

/**
 * 作者：liuhai
 * 时间：2019/1/11:9:59
 * 邮箱：185587041@qq.com
 * 说明：每个子模块引用第三方库的初始化接口
 */
public interface BaseInitInterface {

    public void init(Context context);

    /**
     * application TRIM回调释放
     */
    public void trim();


}
