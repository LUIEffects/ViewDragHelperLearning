package com.lewish.start.draglayoutdemo.view;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


/**
 * author: sundong
 * created at 2017/3/21 14:28
 */
public class MyDragLayout extends LinearLayout {
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;
    private ViewDragHelper mViewDragHelper;
    private Point mAutoBackViewOriginPoint;
    public MyDragLayout(Context context) {
        this(context,null);
    }

    public MyDragLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mAutoBackViewOriginPoint = new Point();
        initViewDragHelper(context);
    }

    private void initViewDragHelper(Context context) {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mDragView || child == mAutoBackView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int leftBorder = getPaddingLeft();
                int rightBorder = getWidth() - leftBorder - child.getWidth();
                return Math.min(Math.max(leftBorder, left), rightBorder);
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int topBorder = getPaddingTop();
                int bottomBorder = getHeight() - topBorder - child.getHeight();
                return Math.min(Math.max(topBorder, top), bottomBorder);
            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
                mViewDragHelper.captureChildView(mEdgeTrackerView,pointerId);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if(releasedChild == mAutoBackView) {
                    mViewDragHelper.settleCapturedViewAt(mAutoBackViewOriginPoint.x,mAutoBackViewOriginPoint.y);
                    invalidate();
                }
                super.onViewReleased(releasedChild, xvel, yvel);
            }
        });

        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackViewOriginPoint.x = mAutoBackView.getLeft();
        mAutoBackViewOriginPoint.y = mAutoBackView.getTop();
    }

    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
