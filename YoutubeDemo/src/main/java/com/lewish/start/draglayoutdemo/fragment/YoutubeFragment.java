package com.lewish.start.draglayoutdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lewish.start.draglayoutdemo.R;
import com.lewish.start.draglayoutdemo.view.YoutubeLayout;

/**
 * author: sundong
 * created at 2017/3/21 15:38
 */
public class YoutubeFragment extends Fragment {
    private View mContentView;
    private TextView viewHeader;
    private YoutubeLayout youtubeLayout;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.frag_youtube,container,false);
        initView(mContentView);
        return mContentView;
    }

    private void initView(View mContentView) {
        viewHeader = (TextView) mContentView.findViewById(R.id.header);
        youtubeLayout = (YoutubeLayout) mContentView.findViewById(R.id.dragLayout);
        listView = (ListView) mContentView.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewHeader.setText(listView.getAdapter().getItem(i).toString());
                youtubeLayout.setVisibility(View.VISIBLE);
                youtubeLayout.maximize();
            }
        });

        listView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public String getItem(int i) {
            return "object" + i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View rView, ViewGroup viewGroup) {
            View view = rView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            }
            ((TextView) view.findViewById(android.R.id.text1)).setText(getItem(i));
            return view;
        }
    }
}
