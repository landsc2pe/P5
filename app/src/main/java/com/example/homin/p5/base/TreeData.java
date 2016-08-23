package com.example.homin.p5.base;

import android.util.Log;

import com.example.homin.p5.utils.LogTag;

import java.io.File;

/**
 * Created by HOMIN on 2016-08-22.
 */
public class TreeData {
    public static final String TAG = TreeData.class.getSimpleName();

    private int type;
    private int hierarchy;
    private File data;
    private File[] childData;
    private String dataPath;
    private String dataName;

    public TreeData(File data) {
        this.data = data;
        childData = data.listFiles();
        dataPath = data.getPath();
        dataName = data.getName();

        if (LogTag.DEBUG) Log.d(TAG, "" + childData.length);

        File file = new File(dataPath + "/leaf_confirm.text");
        if (file.isFile()) {
            type = 0;
        } else {
            type = 1;
        }

//        setLinkedList();
    }
//
//    public void setLinkedList() {
//        childList = new LinkedList();
//
//        for(File file : childData){
//            childList.add(file);
//        }
//    }
//
//    public LinkedList<File> getChildList() {
//        return childList;
//    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public int getType() {

        return type;
    }

    public int getHierarchy() {
        return hierarchy;
    }


    public File getData() {
        return data;
    }

    public File[] getChildData() {
        return childData;
    }

    public String getDataPath() {
        return dataPath;
    }

    public String getDataName() {
        return dataName;
    }


}