package com.liuhai.permission;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者：liuhai
 * 时间：2019/1/10:14:37
 * 邮箱：185587041@qq.com
 * 说明：全局的权限管理（申请） 7.0UIR转换 8.0（应用的安装）
 */
public class PermissionUtils {
    private static volatile PermissionUtils permissionUtils;


    public static PermissionUtils getInstance() {
        if (permissionUtils == null) {
            synchronized (PermissionUtils.class) {
                if (permissionUtils == null) {
                    permissionUtils = new PermissionUtils();
                    return permissionUtils;
                }
            }
        }

        return permissionUtils;
    }


    /**
     * 请求权限组
     *
     * @param context
     * @param Permission Permission.Group.xxx
     */
    public void requestPermission(Context context, String[] Permission, Action<List<String>> grant, Action<List<String>> denied) {
        AndPermission.with(context)
                .runtime()
                .permission(Permission)
                .rationale(new RuntimeRationale())
                .onGranted(grant).onDenied(denied)
                .start();
    }


    /**
     * 单个权限或多个权限请求
     *
     * @param context
     * @param grant      成功
     * @param denied     失败
     * @param permission 权限
     */
    public void requestPermission(Context context, Action<List<String>> grant, Action<List<String>> denied, String... permission) {
        AndPermission.with(context).runtime().
                permission(permission)
                .rationale(new RuntimeRationale())
                .onGranted(grant)
                .onDenied(denied)
                .start();

    }

    /**
     * 安装APK
     * 8.0版本需要额外权限
     *
     * @param context
     * @param apkFile
     */
    public void installApk(final Context context, final File apkFile) {
        AndPermission.with(context)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {

                        installPackage(context, apkFile);

                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Toast.makeText(context, "获取权限失败，无法安装应用", 0).show();
                    }
                })
                .start();
    }

    /**
     * Install package.
     */
    private void installPackage(Context context, File file) {
        AndPermission.with(context)
                .install()
                .file(file)
                .rationale(new InstallRationale())
                .onGranted(new Action<File>() {
                    @Override
                    public void onAction(File data) {
                        // Installing.
                    }
                })
                .onDenied(new Action<File>() {
                    @Override
                    public void onAction(File data) {
                        // The user refused to install.
                    }
                })
                .start();
    }


    public Uri getFileUir(Context context, File uirFile) {
        return AndPermission.getFileUri(context, uirFile);
    }

    public Uri getFileUir(Fragment context, File uirFile) {
        return AndPermission.getFileUri(context, uirFile);
    }

}
