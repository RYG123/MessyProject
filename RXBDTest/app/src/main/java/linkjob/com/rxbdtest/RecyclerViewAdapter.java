package linkjob.com.rxbdtest;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import linkjob.com.rxbdtest.databinding.ItemBinding;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements View.OnClickListener{
    private List<UserObservableBean>list;
    private Random random = new Random(System.currentTimeMillis());
    private OnItemOnClickListener onItemOnClickListener;
    private LayoutInflater inflater;
    private Context context;


    public interface OnItemOnClickListener{
        void OnItemClick(View view,int position);
    }

    public  RecyclerViewAdapter(Context context, List<UserObservableBean> list){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list= list;
        Log.i("tag",list.get(0).getUserName());
    }
    public void  setOnItemOnClickListener(OnItemOnClickListener listener){
        onItemOnClickListener = listener;
    }
    public OnItemOnClickListener getOnItemOnClickListener(){
        return onItemOnClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
//        ItemBinding itemBinding = DataBindingUtil.inflate(inflater,R.layout.item,parent,false);
//        itemBinding.getRoot().setOnClickListener(this);
        return new RecyclerViewHolder(v);

    }

    @Override
    public void onBindViewHolder( RecyclerViewHolder recyclerViewHolder, int position) {
        ItemBinding itemBinding = recyclerViewHolder.getItemBinding();
        UserObservableBean userObservableBean = list.get(position);
        itemBinding.setVariable(BR.userObserBean,userObservableBean);
        itemBinding.getRoot().setTag(position);
        itemBinding.btnUpdate.setOnClickListener(new OnBtnClickListener(position,1, userObservableBean));
        itemBinding.btnDelete.setOnClickListener(new OnBtnClickListener(2, position));
        itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if(onItemOnClickListener!=null)
        onItemOnClickListener.OnItemClick(v, (int) v.getTag());
    }
    public void addData(UserObservableBean userObservableBean){
        int position = random.nextInt(list.size()+1);
        list.add(position,userObservableBean);
        notifyItemInserted(position);
    }
    public void DeleteData(int positionn){
       if(list.size()==0){
           return;
       }
       int position = random.nextInt(list.size());
       list.remove(position);
       notifyItemRemoved(position);
    }

    private class OnBtnClickListener implements View.OnClickListener{
        private int stats;
        private UserObservableBean userBean;
        private int position;

        OnBtnClickListener(int stats,int position){
            this.stats = stats;
            this.position = position;
        }
        OnBtnClickListener(int position,int stats,UserObservableBean userBean){
            this.position = position;
            this.stats = stats;
            this.userBean = userBean;
        }
        @Override
        public void onClick(View v) {
            switch(stats){
                case 1:
                    userBean.setUserName("修改后的+hjx");
                    String s = String.valueOf(userBean);
                    RxTest.getmInstance().post(s+"\nid:"+position);
                    notifyDataSetChanged();
                    break;
                case 2:
                    list.remove(position);
                    notifyDataSetChanged();
                    System.out.println("删除的item项是："+position);
                    break;
            }
        }
    }
}
