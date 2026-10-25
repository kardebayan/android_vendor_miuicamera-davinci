/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

/** Popup list used by the miuix drop down preference, built on a plain ListView. */
public class ImmersionListPopupWindow extends PopupWindow {
    private final Context mContext;
    private final ListView mListView;
    private ListAdapter mAdapter;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public ImmersionListPopupWindow(Context context) {
        super(context);
        mContext = context;
        setFocusable(true);
        setOutsideTouchable(true);

        TypedArray a = context.obtainStyledAttributes(
                new int[] {android.R.attr.colorBackgroundFloating});
        setBackgroundDrawable(new ColorDrawable(a.getColor(0, 0xffffffff)));
        a.recycle();
        setElevation(8.0f * context.getResources().getDisplayMetrics().density);

        mListView = new ListView(context);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(parent, view, position, id);
                }
            }
        });
        setContentView(mListView);
    }

    public void setAdapter(ListAdapter adapter) {
        mAdapter = adapter;
        mListView.setAdapter(adapter);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public boolean prepareShow(View anchor, ViewGroup parent) {
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return false;
        }
        int maxWidth = (int) (mContext.getResources().getDisplayMetrics().widthPixels * 0.8f);
        setWidth(Math.min(measureContentWidth(parent), maxWidth));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        return true;
    }

    public void show(View anchor, ViewGroup parent) {
        if (prepareShow(anchor, parent)) {
            showAsDropDown(anchor);
        }
    }

    private int measureContentWidth(ViewGroup parent) {
        if (parent == null) {
            parent = new FrameLayout(mContext);
        }
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int width = 0;
        View view = null;
        int viewType = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            int type = mAdapter.getItemViewType(i);
            if (type != viewType) {
                view = null;
                viewType = type;
            }
            view = mAdapter.getView(i, view, parent);
            view.measure(spec, spec);
            width = Math.max(width, view.getMeasuredWidth());
        }
        return width + mListView.getPaddingLeft() + mListView.getPaddingRight();
    }
}
