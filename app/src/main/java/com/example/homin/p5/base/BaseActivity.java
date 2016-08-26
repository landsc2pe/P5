package com.example.homin.p5.base;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HOMIN on 2016-08-22.
 */
public class BaseActivity extends AppCompatActivity {

    public static String treePath;
    public static String recentPath;
    public static boolean treeFolderState;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        treePath = rootPath + StringPath.TREE_FOLDER_NAME;
        recentPath = treePath;
    }
}
