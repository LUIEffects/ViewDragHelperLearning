package com.lewish.start.draglayoutdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lewish.start.draglayoutdemo.common.Constants;
import com.lewish.start.draglayoutdemo.R;
/**
 * author: sundong
 * created at 2017/3/21 15:38
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnDrag;
    private Button mBtnYoutube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
        initListener();
    }

    private void initListener() {
        mBtnDrag.setOnClickListener(this);
        mBtnYoutube.setOnClickListener(this);
    }

    private void initView(Bundle savedInstanceState) {
        mBtnDrag = (Button)findViewById(R.id.mBtnDrag);
        mBtnYoutube = (Button)findViewById(R.id.mBtnYoutube);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.mBtnDrag :
                bundle.putInt(Constants.FRAGMENT_TYPE, Constants.FRAGMENT_TYPE_DRAG);
                break;
            case R.id.mBtnYoutube :
                bundle.putInt(Constants.FRAGMENT_TYPE, Constants.FRAGMENT_TYPE_YOUTUBE);
                break;
        }
        openActivity(DemoActivity.class,bundle);
    }
    /**
     * 跳转到指定Activity（传参）
     *
     * @param cls
     * @param bundle
     */
    public void openActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转到制定Activity（不传参）
     *
     * @param cls
     */
    public void openActivity(Class<?> cls) {
        openActivity(cls, null);
    }
}
