package base;

import error.AppException;

/**
 * <View层拿到的回调>
 *
 * @version: V1.0
 */
public interface OnSubscribeSuccess<T> {
    void onSuccess(T t);

    void onError(AppException error);
}
