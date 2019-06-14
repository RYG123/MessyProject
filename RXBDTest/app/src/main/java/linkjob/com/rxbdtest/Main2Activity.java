package linkjob.com.rxbdtest;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import linkjob.com.rxbdtest.databinding.ActivityMain2Binding;

public class Main2Activity extends AppCompatActivity {
    private ActivityMain2Binding binding;
    private FragmenTest2 fragmentTest2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);
        fragmentTest2 = new FragmenTest2();
        replaceFragmen(fragmentTest2);
    }
    private void replaceFragmen(Fragment fragment) {
        FragmentManager fragmentManager;
        FragmentTransaction transaction;
        // 1.获取FragmentManager，在活动中可以直接通过调用getFragmentManager()方法得到
        fragmentManager =getSupportFragmentManager();
// 2.开启一个事务，通过调用beginTransaction()方法开启
        transaction = fragmentManager.beginTransaction();
// 3.向容器内添加或替换碎片，一般使用replace()方法实现，需要传入容器的id和待添加的碎片实例
        transaction.replace(R.id.fragmentmain, fragment);  //fr_container不能为fragment布局，可使用线性布局相对布局等。
// 4.使用addToBackStack()方法，将事务添加到返回栈中，填入的是用于描述返回栈的一个名字
        transaction.addToBackStack(null);
// 5.提交事物,调用commit()方法来完成
        transaction.commit();
    }
}
