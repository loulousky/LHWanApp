package base;


import error.AppException;

/**
 * 作者：liuhai
 * 时间：2019/1/10:14:01
 * 邮箱：185587041@qq.com
 * 说明：常规集成的Activity
 */
public class BaseFunActivity<T extends BasePresenter> extends BaseActivity implements ILoadingView {

    //P层接口
    private T presenter;


    @Override
    public void showLoading() {
        showLoading();
    }

    @Override
    public void showError(AppException errorThrowable) {
        errorThrowable.printStackTrace();
        showError(errorThrowable);
    }

    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {

    }

    /**
     *添加Fragment
     *
     */



    /**
     * 删除Fragmet
     */


}
