package error;

import android.util.Log;

import com.example.liuhai.baselib.BuildConfig;

/**
 * 作者：liuhai
 * 时间：2019/1/10:11:41
 * 邮箱：185587041@qq.com
 * 说明：通用的exception
 */
public class AppException  extends Exception{

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        Log.e("!!!!~~~ERROR~~~!!!!",getMessage());
        super.printStackTrace();


    }
}
