package com.example.homin.p5.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.homin.p5.R;
import com.example.homin.p5.adapters.ExplorerAdapter;
import com.example.homin.p5.base.BaseActivity;
import com.example.homin.p5.utils.ClickEvent;
import com.example.homin.p5.base.ClickEventID;
import com.example.homin.p5.base.StringPath;
import com.example.homin.p5.fragments.DirFragment;
import com.example.homin.p5.utils.LogTag;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by HOMIN on 2016-08-20.
 */
public class ManagerActivity extends BaseActivity {
    public static final String TAG = ManagerActivity.class.getSimpleName();

    private ArrayList<File> explorerList;
    private RecyclerView explorerRecyclerView;
    private ExplorerAdapter explorerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        EventBus.getDefault().register(this);
        init();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    private void init() {

        makeTreeDir();
        dataSort();
        setViews();
//        setClickListener();
    }
//
//    private void setClickListener() {
//        explorerAdapter.setOnItemClickListener(new ExplorerAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//                recentPath += "/" + explorerList.get(position).getName();
//                dataSort();
////                explorerAdapter.notifyDataSetChanged();
//                explorerRecyclerView.setAdapter(new ExplorerAdapter(explorerList));
//            }
//        });
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void makeTreeDir() {

        File checkFile = new File(treePath);
        if (!checkFile.isDirectory()) {
            Toast.makeText(this, "Now, You have your own Tree", Toast.LENGTH_LONG).show();
            checkFile.mkdir();
        }
    }

    private void setViews() {

        RecyclerView aboveRecyclerView = (RecyclerView) findViewById(R.id.recycler_above);
        explorerRecyclerView = (RecyclerView) findViewById(R.id.recycler_explorer);
        RecyclerView imageRecyclerView = (RecyclerView) findViewById(R.id.recycler_images);

        LinearLayoutManager aboveManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager explorerManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager imagesManager = new GridLayoutManager(this, 4);

        aboveRecyclerView.setLayoutManager(aboveManager);
        explorerRecyclerView.setLayoutManager(explorerManager);
        imageRecyclerView.setLayoutManager(imagesManager);

        explorerAdapter = new ExplorerAdapter(explorerList);

        aboveRecyclerView.setAdapter(null);
        explorerRecyclerView.setAdapter(explorerAdapter);
        imageRecyclerView.setAdapter(null);


        Button makeDirButton = (Button) findViewById(R.id.button_mkdir);
        Button backSpaceButton = (Button) findViewById(R.id.button_backspace);

        makeDirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                if (LogTag.DEBUG) Log.d(TAG, "Clicked");
                FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
                fragmentManager.add(R.id.manager_container, new DirFragment(), DirFragment.TAG);
                fragmentManager.addToBackStack(DirFragment.TAG);
                fragmentManager.commit();

            }
        });

        backSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Objects.equals(recentPath, treePath)) {
                    int num = recentPath.lastIndexOf("/");
                    recentPath = recentPath.substring(0, num);
                    if (LogTag.DEBUG) Log.d(TAG, recentPath);
                    dataSort();
                    explorerRecyclerView.setAdapter(new ExplorerAdapter(explorerList));
                }
//                explorerAdapter.notifyDataSetChanged();

            }
        });

    }

    public void dataSort() {

        File root = new File(recentPath);
        File[] rootFiles = root.listFiles();
        explorerList = new ArrayList<>();

        //possible to make errors
        for (File file : rootFiles) {
            File confirmFile = new File(file.getPath() + StringPath.CONFIRM_LEAF);
            if (!confirmFile.isFile()) {
                explorerList.add(file);
            }
        }

        for (File file : rootFiles) {
            File confirmFile = new File(file.getPath() + StringPath.CONFIRM_LEAF);
            if (confirmFile.isFile()) {
                explorerList.add(file);
            }
        }

    }

    @Subscribe
    public void onEvent(ClickEvent event) {
        if (event.getId() == ClickEventID.ITEM_RESET) {
            dataSort();
            explorerRecyclerView.setAdapter(new ExplorerAdapter(explorerList));
        } else if (event.getId() == ClickEventID.ITEM_ENTER) {
            recentPath += "/" + explorerList.get(Integer.parseInt(event.getParams()[0].toString())).getName();
                dataSort();
                explorerRecyclerView.setAdapter(new ExplorerAdapter(explorerList));
        } else if (event.getId() == ClickEventID.ITEM_DELETE) {
            Toast.makeText(getApplicationContext(),"Delete", Toast.LENGTH_SHORT).show();
        }

    }

}
