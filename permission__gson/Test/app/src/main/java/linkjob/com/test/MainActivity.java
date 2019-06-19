package linkjob.com.test;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity {
    private int SUCCESSCODE = 1;
    private Button btn ;
    private Permission permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission = Permission.getInstance();
        permission.getTakePicPermission(MainActivity.this);
        System.out.println("打印一下permission："+permission);
        init();
    }

    private void init() {
        //: PrimitiveOverloading.java // Promotion of primitives and overloading
        // boolean can't be automatically converted   static void prt(String s) {      System.out.println(s);    }
//        Flower x = new Flower();
//        x.print();

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permission.getTakePicPermission(MainActivity.this);
                if(permission.isPermssionResult()){
                    startActivity(new Intent(MainActivity.this,Main2Activity.class));
                }
            }
        });
        User person=new User();
        Gson g = new Gson();
        String str = "[{username:'zhangsan',userid:12},{username:'lisi',userid:12}]";
        Type type = new TypeToken<List<User>>(){}.getType();
        List<User> data = g.fromJson(str,  type);
for(User p : data){
        System.out.println("username:"+p.getUsername()+" "+ "userid:"+p.getUserId());
}


    }

//    public class Flower {
//        private int petalCount = 0;
//        private String s = new String("null");
//        Flower(int petals) {
//            petalCount = petals;
//            System.out.println("Constructor w/ int arg only, petalCount= "       + petalCount);
//        }
//        Flower(String ss) {
//            System.out.println("Constructor w/ String arg only, s=" + ss);
//            s = ss;
//        }
//        Flower(String s, int petals) {
//            this(s);
////            this(petals);
//            //!    this(s); // Can't call two!     this.s = s; // Another use of "this"
//            System.out.println("String & int args");
//        }
//        Flower() {
//            this("hi", 47);
//            System.out.println("default constructor (no args)");
//        }
//        void print() { //!    this(11); // Not inside non-constructor!
//            System.out.println("petalCount = " + petalCount + " s = "+ s);
//        }
//
//        }


}

