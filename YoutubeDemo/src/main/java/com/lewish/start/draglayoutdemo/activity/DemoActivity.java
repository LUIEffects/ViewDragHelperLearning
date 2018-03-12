package com.lewish.start.draglayoutdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.lewish.start.draglayoutdemo.common.Constants;
import com.lewish.start.draglayoutdemo.fragment.DragFragment;
import com.lewish.start.draglayoutdemo.R;
import com.lewish.start.draglayoutdemo.fragment.YoutubeFragment;

/**
 * author: sundong
 * created at 2017/3/21 15:37
 */
public class DemoActivity extends AppCompatActivity {
    private int mFragmentType;
    private FragmentTransaction mTransaction;
    private FrameLayout mFlContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_demo);
        initView(savedInstanceState);
        initVariables();
        loadFragment();
    }

    private void loadFragment() {
        switch (mFragmentType) {
            case Constants.FRAGMENT_TYPE_DRAG:
                addFragment(R.id.fl_content, new DragFragment());
                break;
            case Constants.FRAGMENT_TYPE_YOUTUBE:
                addFragment(R.id.fl_content, new YoutubeFragment());
                break;
        }
    }

    private void initView(Bundle savedInstanceState) {
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
    }

    private void initVariables() {
        Bundle bundle = getIntent().getExtras();
        mFragmentType = bundle.getInt(Constants.FRAGMENT_TYPE, 1);
    }

    private void addFragment(int layoutId, Fragment fragment) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(layoutId, fragment);
        mTransaction.commitAllowingStateLoss();
    }
}
