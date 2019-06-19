package linkjob.com.test;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionGen;

public class Permission{
    private static Permission permission;
    private boolean permssionResult = false;
    public static Permission getInstance(){
        if(permission == null) {
            permission = new Permission();
        }
        return permission;
    }
    public void getPermission(Activity activity, String... permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (findDeniedPermissions(activity, permissions).size() > 0) {
                PermissionGen.with(activity)
                        .addRequestCode(100)
                        .permissions(
                                permissions
                        )
                        .request();
            } else {
                doSomething();
            }
        } else {
            doSomething();
        }

    }
    @TargetApi(value = Build.VERSION_CODES.M)
    public  List<String> findDeniedPermissions(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }
    //申请拍照和文件读写权限
    public  void getTakePicPermission(Activity activity) {
        getPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                doFailSomething();
                return;
            }
        }

        doSomething();

    }

    public void doSomething(){
        permssionResult = true;
        System.out.println("成功打印一下");
    }
    public  void doFailSomething(){
        permssionResult = false;
        System.out.println("失败打印一下");
    }

    public boolean isPermssionResult() {

        return permssionResult;
    }
}