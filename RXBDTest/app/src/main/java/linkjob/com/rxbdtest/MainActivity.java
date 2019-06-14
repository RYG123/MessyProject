package linkjob.com.rxbdtest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import linkjob.com.rxbdtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private FragmentTest fragmentTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
    }

    private void initView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //recyclerView = binding.recycler;
        binding.recycler.setLayoutManager(layoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,initObservableData());//
        binding.recycler.setAdapter(recyclerViewAdapter);

        //item的点击事件
        recyclerViewAdapter.setOnItemOnClickListener(new RecyclerViewAdapter.OnItemOnClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "行数：" + position, Toast.LENGTH_LONG).show();
            }
        });
        binding.btnDemo8Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserObservableBean userBean = new UserObservableBean();
                userBean.setNumber("databinding\n           小实验"+String.valueOf(8));
                userBean.setUserName("HJX"+8);
                userBean.setDate("2019.3.27");
                userBean.setSex(8 % 2 == 0 ? "男" : "男");
                System.out.println("增加的信息：\n"+userBean.getUserName());
                recyclerViewAdapter.addData(userBean);
            }
        });
        binding.btnDemo8Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
        binding.btnDemo8Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewAdapter.DeleteData(1);
            }
        });

        binding.opFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                if(fragmentTest==null) {
//                    fragmentTest = new FragmentTest();
//                    binding.fragmentmain.setVisibility(View.VISIBLE);
//                    UserObservableBean userBean = new UserObservableBean();
//                    userBean.setNumber("databinding\n           小实验"+String.valueOf(8));
//                    userBean.setUserName("HJX"+8);
//                    userBean.setDate("2019.3.27");
//                    userBean.setSex(8 % 2 == 0 ? "男" : "男");
//                    RxTest.getmInstance().post(userBean.getDate()+userBean.getUserName()+userBean.getNumber());
//                    replaceFragmen(fragmentTest);
//                }
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                UserObservableBean userBean = new UserObservableBean();
                    userBean.setNumber("databinding\n           小实验"+String.valueOf(8));
                    userBean.setUserName("HJX"+8);
                    userBean.setDate("2019.3.27");
                    userBean.setSex(8 % 2 == 0 ? "男" : "男");
                //RxTest.getmInstance().post(userBean.getDate()+userBean.getUserName()+userBean.getNumber());
                startActivity(i);
            }
        });
        binding.fragmentmain.setVisibility(View.GONE);
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

    private List<UserObservableBean> initObservableData() {
        List<UserObservableBean> list = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            UserObservableBean userBean = new UserObservableBean();
            userBean.setNumber("databinding\n         小实验"+String.valueOf(i));
            userBean.setUserName("hjx"+i);
            userBean.setDate("2019.3.2"+i);
            userBean.setSex(8 % 2 == 0 ? "男" : "男");
            list.add(userBean);
        }
        return list;
    }



}
