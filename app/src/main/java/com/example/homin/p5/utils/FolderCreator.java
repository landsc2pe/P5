package com.example.homin.p5.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by HOMIN on 2016-08-22.
 */
public class FolderCreator {
    public static final String TAG = FolderCreator.class.getSimpleName();

    String parentPath;
    String folderName;
    String name;
    String LEAF_CONFIRM = "/leaf_confirm.text";

    public FolderCreator(int type, String parentPath, String folderName) {
        this.parentPath = parentPath;
        this.folderName = folderName;
        name = parentPath + "/" + folderName;

        if (type == 0) {
            createBranch();
        } else {
            createLeaf();
        }
    }

    private void createBranch() {
        File branchFolder = new File(name);
        if(LogTag.DEBUG) Log.d(TAG, name);
        if (!branchFolder.exists()) branchFolder.mkdir();

    }


    private void createLeaf() {
        File leafFolder = new File(name);
        File leafFile = new File(name + LEAF_CONFIRM);
        if (!leafFolder.exists()) {
            leafFolder.mkdir();
            try {
                leafFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}