package com.example.homin.p5.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homin.p5.R;
import com.example.homin.p5.utils.LogTag;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HOMIN on 2016-08-22.
 */
public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.Tab2ViewHolder> {
    public static final String TAG = Tab2Adapter.class.getSimpleName();

    List<File> treeData;
    private OnItemClickListenerTab2 onItemClickListener;
    private ViewGroup viewGroup;

    public Tab2Adapter(List<File> treeData) {
        this.treeData = treeData;
    }

    @Override
    public Tab2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewGroup = parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_treeview, parent, false);
        return new Tab2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Tab2ViewHolder holder, final int position) {

        setColor(holder, position);

        holder.itemtext.setText(treeData.get(position).getName());
        holder.itemtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LogTag.DEBUG) Log.d(TAG, "view : " + view + ", onItemClickListener : " +
                    onItemClickListener);

                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return treeData.size();
    }

    public class Tab2ViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemtext;

        public Tab2ViewHolder(View itemView) {
            super(itemView);

            itemtext = (TextView) itemView.findViewById(R.id.item_tree_text);
        }
    }

    public void setOnItemClickListener(OnItemClickListenerTab2 onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListenerTab2 {
        void onClick(int position);
    }

    private void setColor(Tab2ViewHolder holder, int position) {
        String path = treeData.get(position).getAbsolutePath();
        if (LogTag.DEBUG) Log.d(TAG, path);
        int treeValue = path.indexOf("/Tree");
        String getValue = path.substring(treeValue);
        if (LogTag.DEBUG) Log.d(TAG, getValue);
        Pattern pattern = Pattern.compile("/");
        Matcher matcher = pattern.matcher(getValue);
        int count = 0;
        for (int i = 0; matcher.find(i); i = matcher.end()) {
            count++;
        }
        if(LogTag.DEBUG)Log.d(TAG, ""+count);

        if (count == 2)
            holder.itemtext.setBackgroundColor(viewGroup.getResources().getColor(R.color.colorOrange));
        else if (count == 3) {
            holder.itemtext.setBackgroundColor(viewGroup.getResources().getColor(R.color.colorDeepOrange));
        }else {
            holder.itemtext.setBackgroundColor(viewGroup.getResources().getColor(R.color.colorDeepOrange2));

        }

    }
}
