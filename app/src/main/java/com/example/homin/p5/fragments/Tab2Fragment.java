package com.example.homin.p5.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homin.p5.R;
import com.example.homin.p5.adapters.Tab2Adapter;
import com.example.homin.p5.utils.ItemCreator;
import com.example.homin.p5.utils.LogTag;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HOMIN on 2016-08-21.
 */
public class Tab2Fragment extends Fragment {
    public static final String TAG = Tab2Adapter.class.getSimpleName();

    private String treePath;
    private View view;
    private RecyclerView recyclerView;
    private ItemCreator itemCreator;
    private Tab2Adapter tab2Adapter;
    private List<File> treeData;
    private List<File> dataCache;
    private ArrayList<File> checkFold;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        setData();
        setViews();
        setOnItemListener();
    }

    private void setOnItemListener() {
        tab2Adapter.setOnItemClickListener(new Tab2Adapter.OnItemClickListenerTab2() {

            public File clickedFile;
            public String clickedPath;
            public String clickedName;

            @Override
            public void onClick(int position) {
                if(LogTag.DEBUG) Log.d(TAG, "position : " + position);

                clickedFile = treeData.get(position).getAbsoluteFile();
                clickedPath = treeData.get(position).getAbsolutePath();
                clickedName = treeData.get(position).getName();

                if(LogTag.DEBUG) Log.d("TTT", clickedPath);
                if(LogTag.DEBUG) Log.d("TTT",clickedName);

                if (checkFold.contains(clickedFile)) {
                    checkFold.remove(clickedFile);
                    dataRemove();
                    checkFold.remove(clickedFile);

                } else {
                    checkFold.add(clickedFile);

                    dataCache = new ArrayList<>();
                    Collections.addAll(dataCache, clickedFile.listFiles());
                    treeData.addAll(position + 1, dataCache);
                    tab2Adapter.notifyDataSetChanged();
                }
            }

            private void dataRemove() {
                Iterator<File> iterator = snapshotIterator(treeData);
                while (iterator.hasNext()) {
                    File file = iterator.next();
                    if (file.getAbsolutePath().contains(clickedName + "/")) {
                        treeData.remove(file);
                        //todo iterator.remove();
                    }

                }
                if(LogTag.DEBUG) Toast.makeText(getContext(),"Collapsed",Toast.LENGTH_SHORT).show();

                tab2Adapter.notifyDataSetChanged();
//                recyclerView.setAdapter(new Tab2Adapter(treeData));
            }



        });
    }

    private void setViews() {
        setRecyclerView();

    }

    private void setRecyclerView() {
        tab2Adapter = new Tab2Adapter(treeData);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_tab2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(tab2Adapter);
    }


    private void setData() {
        itemCreator = new ItemCreator();
        itemCreator.setRootData();
        treeData = itemCreator.getTreeData();

        checkFold = new ArrayList<>();
    }

    public Iterator snapshotIterator(List treeData) {

        return new ArrayList(treeData).iterator();

    }

}
