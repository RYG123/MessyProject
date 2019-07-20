package linkjob.com.recyclerviewdatabind;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import linkjob.com.recyclerviewdatabind.databinding.ItemuserBinding;

public class UserAdapter extends BaseBindingAdapter<User,ItemuserBinding>{
    private Context context;
    private SendData sendData;
    public UserAdapter(Context context,SendData sendData){
        super(context);
        this.context = context;
        list.add(new User("张三", 18));
        list.add(new User("李四", 28));
        list.add(new User("王五", 38));
        this.sendData = sendData;
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.itemuser;
    }

    @Override
    protected void onBindItem(ItemuserBinding binding, final User item) {

        binding.setModel(item);
        binding.executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData.Send(item);
            }
        });
    }

    public void addItem(User user){
        list.add(user);
    }
}
