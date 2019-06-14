package linkjob.com.rxbdtest;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import linkjob.com.rxbdtest.databinding.ActivityMainBinding;
import linkjob.com.rxbdtest.databinding.ItemBinding;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    ItemBinding itemBinding;

    public RecyclerViewHolder(View v) {
        super(v);
        itemBinding = DataBindingUtil.bind(v);
    }
    public ItemBinding getItemBinding(){
        return itemBinding;
    }


}
