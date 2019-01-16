package base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;


/**
 * 作者：liuhai
 * 时间：2019/1/11:10:00
 * 邮箱：185587041@qq.com
 * 说明：基础模块初始化的申明,会反射调用
 */
public class BaseLibInitImpl implements BaseInitInterface {


    @Override
    public void init(Context context) {
        //基类的初始化工具
      //  if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
  //      }
        ARouter.init((Application) context); // 尽可能早，推荐在Application中初始化

    }

    @Override
    public void trim() {
        ARouter.getInstance().destroy();
    }
}
