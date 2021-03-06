package com.android.mobile.presentation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mobile.R;

public class HorizontalDivider extends RecyclerView.ItemDecoration {
    private static final String TAG = HorizontalDivider.class.getSimpleName();

    private int thick;
    private int gap;
    private int color;
    private Paint paint;

    public HorizontalDivider(Context context) {
        super();

        // Get attributes
        Resources res = context.getResources();
        thick = res.getDimensionPixelSize(R.dimen.horizontal_divider_thickness);
        gap = res.getDimensionPixelOffset(R.dimen.horizontal_divider_gap);
        color = res.getColor(R.color.gray_100);

        // Divider paint
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 0;
        outRect.right = 0;
        outRect.top = 0;
        outRect.bottom = 0;
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int y = parent.getTop() + parent.getPaddingTop();
        int n = parent.getChildCount();
        for (int i = 0; i < n; i++) {
            View child = parent.getChildAt(i);
            int top = child.getTop();
            if (top > y) {
                int bottom = top + thick;
                int left = child.getLeft() + gap;
                int right = child.getRight() - gap;
                canvas.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    public void setColor(@ColorInt int color) {
        this.color = color;
        paint.setColor(color);
    }

    /**
     * Gap from either side of the recyclerview in pixels.
     */
    public void setGap(@DimenRes int gap) {
        this.gap = gap;
    }

    /**
     * Thickness in pixels.
     */
    public void setThick(@DimenRes int thick) {
        this.thick = thick;
    }
}
