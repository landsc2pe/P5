package com.example.homin.p5.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homin.p5.R;

import java.io.File;
import java.util.List;

/**
 * Created by HOMIN on 2016-08-22.
 */
public class ExplorerAdapter extends RecyclerView.Adapter<ExplorerAdapter.ExplorerViewHolder> {
    public static final String TAG = ExplorerAdapter.class.getSimpleName();

    private List<File> data;
    private OnItemClickListener onItemClickListener;

    public ExplorerAdapter(List<File> data) {
        this.data = data;
    }

    @Override
    public ExplorerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_dir, parent, false);
        return new ExplorerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExplorerViewHolder holder, final int position) {
        holder.mTextView.setText(data.get(position).getName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ExplorerViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIcon;
        private final TextView mTextView;

        public ExplorerViewHolder(View itemView) {
            super(itemView);

            mIcon = (ImageView) itemView.findViewById(R.id.icon_view);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

}
