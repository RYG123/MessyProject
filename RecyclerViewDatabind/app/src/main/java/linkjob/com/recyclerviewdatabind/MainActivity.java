package linkjob.com.recyclerviewdatabind;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import linkjob.com.recyclerviewdatabind.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SendData{
    ActivityMainBinding binding;
    UserAdapter adapter;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new UserAdapter(this,this);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(adapter);

        binding.addTextItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(new User("Aa",20));
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void Send(final User user) {
        this.user = user;
        System.out.println("姓名："+user.getName()+" 年龄："+user.getAge());
    }
}