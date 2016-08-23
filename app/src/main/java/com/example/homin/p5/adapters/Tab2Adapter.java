package com.example.homin.p5.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by HOMIN on 2016-08-22.
 */
public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.Tab2ViewHolder>{

    List fileList;

    public Tab2Adapter(List fileList) {
        this.fileList = fileList;
    }

    @Override
    public Tab2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Tab2ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Tab2ViewHolder extends RecyclerView.ViewHolder{

        public Tab2ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
