package base;
import error.AppException;
/**
 *
 *通用VIEW提示接口
 * @version: V1.0
 */
public interface ILoadingView {
    void showLoading();

    void showError(AppException errorThrowable);

    void hideLoading();
}
