package com.example.homin.p5.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homin.p5.R;
import com.example.homin.p5.base.BaseActivity;
import com.example.homin.p5.utils.FolderCreator;

import java.util.Objects;

/**
 * Created by HOMIN on 2016-08-22.
 */
public class DirFragment extends Fragment {
    public static final String TAG = DirFragment.class.getSimpleName();
    private View view;
    private EditText textFolderName;
    private Button mBranchButton;
    private Button mLeafButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dir, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setContents();
        setClickListener();

    }


    private void setClickListener() {
        mBranchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mFolderName = textFolderName.getText().toString();
                if (Objects.equals(mFolderName, ""))
                    Toast.makeText(getContext(), "Enter Branch folder name", Toast.LENGTH_SHORT).show();
                else
                    new FolderCreator(0, BaseActivity.recentPath, mFolderName);
            }
        });

        mLeafButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mFolderName = textFolderName.getText().toString();
                if (Objects.equals(mFolderName, ""))
                    Toast.makeText(getContext(), "Enter Leaf folder name", Toast.LENGTH_SHORT).show();
                else {
                    new FolderCreator(1, BaseActivity.recentPath, mFolderName);
                }
            }
        });
    }

    private void setContents() {

        textFolderName = (EditText) view.findViewById(R.id.folder_name);
        mBranchButton = (Button) view.findViewById(R.id.button_branch);
        mLeafButton = (Button) view.findViewById(R.id.button_leaf);
    }
}
