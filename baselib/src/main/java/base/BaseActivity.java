package base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.liuhai.baselib.R;
import com.gyf.barlibrary.ImmersionBar;
import com.liuhai.permission.BuildConfig;
import com.liuhai.permission.PermissionUtils;

import java.security.Permission;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.Subject;
import util.SpUtils;
import wiget.ProgressDialog;


/**
 * 作者：liuhai
 * 时间：2019/1/10:9:27
 * 邮箱：185587041@qq.com
 * 说明：集成AutoLayout
 */
public abstract class BaseActivity extends AppCompatActivity {
    //推荐用progressbar替代 效果可能不如Dialog
    private ProgressDialog progressDialog;
    //Activity的View
    private View contentView;
    //全局SP存储
    public SharedPreferences sharedPreferences;
    //权限视频工具类
    public PermissionUtils permissionUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = SpUtils.getInstance(getApplicationContext());
        permissionUtils = PermissionUtils.getInstance();
        //设置默认全部竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        steepStatusBar();
        contentView = LayoutInflater.from(this).inflate(getLayoutId(), null);

        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            initParam(getIntent().getExtras());
        }
        setContentView(contentView);



    }

    /**
     * 做一些获取BUNDLE的数据
     *
     * @param savedInstanceState
     */
    public void initParam(Bundle savedInstanceState) {

    }


    /**
     * 设置全屏显示
     * @param fullscreen
     */
    public void setFullScreen(boolean fullscreen) {
        if (fullscreen) {
            this.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

    }

    /**
     * 设置状态栏透明
     * 默认设置
     */
    public void steepStatusBar() {
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary).init();
    }

    /**
     * 重写Contentview
     *
     * @param view
     */
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);//Butternife统一入库
        //路由注入框架
        ARouter.getInstance().inject(this);
        init();
        setListener();
    }


    /**
     * 基本Toast
     * @param toast
     */
    public void quickToast(String toast) {
        Toast.makeText(getApplicationContext(), toast, 0).show();
    }


    public void showLoadingDialog(String text) {
        showLoadingDialog(text, false);
    }

    public void showLoadingDialog(String loadText, boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, loadText, cancelable);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        progressDialog.setLoadText(loadText);
    }

    public void closeLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    /**
     * 返回ContentViewID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 做初始化操作
     */
    protected abstract void init();

    /**
     * 设置监听
     */
    protected abstract void setListener();


    @Override
    protected void onDestroy() {
        closeLoadingDialog();
        progressDialog = null;
        super.onDestroy();
    }

    /**
     * 阿里路由的跳转 直接跳转无参数携带
     * @param Path 路径
     */
    public void startActivity(String  Path) {

        ARouter.getInstance().build(Path).navigation();

    }


    /**
     * 拿到具体的AROUTER做 特殊跳转例如传至等
     * @param path
     * @说明 with传参数，addflag添加FLAG  withTransition动画
     * @return
     */
    public ARouter getAouter(String path){
        return ARouter.getInstance();
    }






    /**
     * 阿里路由的跳转 直接跳转无参数携带
     * @param Path 路径
     */
    public void startActivityWithUri(Uri  Path) {
        ARouter.getInstance().build(Path).navigation();

    }





}
