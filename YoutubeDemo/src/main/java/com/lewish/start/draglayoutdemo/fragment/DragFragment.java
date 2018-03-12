package com.lewish.start.draglayoutdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewish.start.draglayoutdemo.R;

/**
 * author: sundong
 * created at 2017/3/21 15:38
 */
public class DragFragment extends Fragment {
    private View mContentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.frag_drag,container,false);
        return mContentView;
    }
}
