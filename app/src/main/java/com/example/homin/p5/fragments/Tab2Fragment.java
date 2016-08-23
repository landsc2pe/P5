package com.example.homin.p5.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homin.p5.R;
import com.example.homin.p5.base.Paths;

import java.io.File;

/**
 * Created by HOMIN on 2016-08-21.
 */
public class Tab2Fragment extends Fragment {

    private String treePath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        checkTreeFolder();

    }

    private void checkTreeFolder() {
        String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        treePath = rootPath + Paths.TREE_FOLDER_NAME;

        File checkFile = new File(treePath);
        if(checkFile.isFile()){
            startTreeExplorer();
        }else {
            setTreeManager();
        }
    }

    private void setTreeManager() {

    }


    private void startTreeExplorer() {

    }

}
