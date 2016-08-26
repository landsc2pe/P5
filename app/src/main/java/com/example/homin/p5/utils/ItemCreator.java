package com.example.homin.p5.utils;

import com.example.homin.p5.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOMIN on 2016-08-27.
 */
public class ItemCreator extends BaseActivity {

    List<File> treeData;

    public void setRootData() {
        treeData = new ArrayList<>();

        File file = new File(treePath);
        for (File data : file.listFiles()) {
            treeData.add(data);
        }
    }


    public List<File> getTreeData() {
        return treeData;
    }


}
