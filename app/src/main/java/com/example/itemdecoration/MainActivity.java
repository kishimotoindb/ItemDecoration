package com.example.itemdecoration;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.addItemDecoration(new MyDecoration());
        mRv.setAdapter(new Adapter());
    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Rect outRect = new Rect();
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_star_selected);

        {
            mPaint.setColor(0xffff0000);
            mPaint.setPathEffect(new CornerPathEffect(100));
            mPaint.setTextSize(20);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(50, 50, 50, 50);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            for (int i = 0; i < parent.getChildCount(); i++) {
                View childAt = parent.getChildAt(i);
                int l = childAt.getLeft();
                int t = childAt.getTop();
                int r = childAt.getRight();
                int b = childAt.getBottom();

                getItemOffsets(outRect, childAt, parent, state);
                int lOffset = outRect.left;
                int tOffset = outRect.top;
                int rOffset = outRect.right;
                int bOffset = outRect.bottom;

                c.drawBitmap(mBitmap, l, t, null);
                c.drawBitmap(mBitmap, l, b, null);
                c.drawBitmap(mBitmap, r, t, null);
                c.drawBitmap(mBitmap, r, b, null);
            }

        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

        }
    }

}
