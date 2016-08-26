package com.example.homin.p5.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homin.p5.R;
import com.example.homin.p5.base.ClickEventID;
import com.example.homin.p5.utils.ClickEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by HOMIN on 2016-08-22.
 */
public class ExplorerAdapter extends RecyclerView.Adapter<ExplorerAdapter.ExplorerViewHolder> {
    public static final String TAG = ExplorerAdapter.class.getSimpleName();

    private ArrayList<File> data;
    private OnItemClickListener onItemClickListener;

    public ExplorerAdapter(ArrayList<File> data) {
        this.data = data;
    }

    @Override
    public ExplorerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_explorer, parent, false);
        view.setBackgroundColor(Color.TRANSPARENT);
        return new ExplorerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExplorerViewHolder holder, final int position) {
        holder.itemText.setText(data.get(position).getName());
        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ClickEvent(ClickEventID.ITEM_ENTER, position));
////                onItemClickListener.onClick(position);
//                ManagerActivity.recentPath += "/" + data.get(position).getName();
//                ManagerActivity.dataSort();
//                ManagerActivity.explorerRecyclerView.setAdapter(new ExplorerAdapter(ManagerActivity.explorerList));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ExplorerViewHolder extends RecyclerView.ViewHolder {

        public View itemContainer;
        public ImageView itemIcon;
        public TextView itemText;

        public ExplorerViewHolder(View itemView) {
            super(itemView);

            itemContainer = itemView.findViewById(R.id.item_explorer_container);
            itemIcon = (ImageView) itemView.findViewById(R.id.item_explorer_icon);
            itemText = (TextView) itemView.findViewById(R.id.item_explorer_text);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

}
