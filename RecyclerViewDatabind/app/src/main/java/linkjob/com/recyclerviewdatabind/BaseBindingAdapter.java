package linkjob.com.recyclerviewdatabind;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBindingAdapter<M,B extends ViewDataBinding> extends RecyclerView.Adapter {
    protected Context context ;
    public List<M> list;
    protected B binding;
    public BaseBindingViewHolder holder;
    public BaseBindingAdapter(Context context){
        this.context = context;
        this.list = new ArrayList<>();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this.context),this.getLayoutResId(viewType),viewGroup,false);
        holder = new BaseBindingViewHolder(binding.getRoot());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        binding = DataBindingUtil.getBinding(viewHolder.itemView);
        this.onBindItem(binding, this.list.get(i));
    }

    protected abstract @LayoutRes int getLayoutResId(int viewType);

    protected abstract void onBindItem(B binding, M item);

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
