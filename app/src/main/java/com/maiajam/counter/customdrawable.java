package com.maiajam.counter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

/**
 * Created by maiAjam on 10/29/2017.
 */

public class customdrawable extends Drawable {

    private Paint mPaint;
    private float mFraction;
    private Context con ;
    private Color color;
    private int stepComp;

    public customdrawable(float fraction, Context cont , int stepNo) {
        mPaint = new Paint();
        setFraction(fraction);
        con = cont ;
        stepComp = stepNo ;
    }


    @Override
    public void draw(@NonNull Canvas canvas) {

        Rect b = getBounds();
        if(stepComp == 1)
        {
            //the completeness is 0%
            mPaint.setColor(ContextCompat.getColor(con, R.color.backgroundColor));
            float x = b.width() * mFraction;
            canvas.drawRect(0, 0, x, b.height(), mPaint);
            mPaint.setColor(ContextCompat.getColor(con, R.color.complete));
            canvas.drawRect(x, 0, b.width(), b.height(), mPaint);
        }else if (stepComp == 2)
        {
            // the completness is 1 - 16%
            mPaint.setColor(ContextCompat.getColor(con, R.color.backgroundColor));
            float x = b.width() * mFraction;
            canvas.drawRect(0, 0, x, b.height(), mPaint);
            mPaint.setColor(ContextCompat.getColor(con, R.color.complete));
            canvas.drawRect(x, 0, b.width(), b.height(), mPaint);

        }else if(stepComp == 3)
        {
            // the completness is 17 - 32%
            mPaint.setColor(ContextCompat.getColor(con, R.color.backgroundColor));

            float x = b.width() * mFraction;
            canvas.drawRect(0, 0, x, b.height(), mPaint);
            mPaint.setColor(ContextCompat.getColor(con, R.color.complete));
            canvas.drawRect(x, 0, b.width(), b.height(), mPaint);

        }else if(stepComp == 4)
        {

            // the completness is 33 - 48%
            mPaint.setColor(ContextCompat.getColor(con, R.color.backgroundColor));
            float x = b.width() * mFraction;
            canvas.drawRect(0, 0, x, b.height(), mPaint);
            mPaint.setColor(ContextCompat.getColor(con, R.color.complete));
            canvas.drawRect(x, 0, b.width(), b.height(), mPaint);

        }else if(stepComp == 5)
        {
            // the completness is 49 - 64%
            mPaint.setColor(ContextCompat.getColor(con, R.color.backgroundColor));
            float x = b.width() * mFraction;
            canvas.drawRect(0, 0, x, b.height(), mPaint);
            mPaint.setColor(ContextCompat.getColor(con, R.color.complete));
            canvas.drawRect(x, 0, b.width(), b.height(), mPaint);

        }else if(stepComp == 6)
        {
            // the completness is 65 - 80%

            mPaint.setColor(ContextCompat.getColor(con, R.color.backgroundColor));
            float x = b.width() * mFraction;
            canvas.drawRect(0, 0, x, b.height(), mPaint);
            mPaint.setColor(ContextCompat.getColor(con, R.color.complete));
            canvas.drawRect(x, 0, b.width(), b.height(), mPaint);

        }else if(stepComp == 7)
        {

            // the completness is 81 - 99%
            mPaint.setColor(ContextCompat.getColor(con, R.color.backgroundColor));
            float x = b.width() * mFraction;
            canvas.drawRect(0, 0, x, b.height(), mPaint);
            mPaint.setColor(ContextCompat.getColor(con, R.color.complete));

            canvas.drawRect(x, 0, b.width(), b.height(), mPaint);
        }else if(stepComp == 8)
        {

            // the completness is 100%
            mPaint.setColor(ContextCompat.getColor(con, R.color.backgroundColor));
            float x = b.width() * mFraction;
            canvas.drawRect(0, 0, x, b.height(), mPaint);
            mPaint.setColor(ContextCompat.getColor(con, R.color.complete));
            canvas.drawRect(x, 0, b.width(), b.height(), mPaint);
        }


    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setFraction(float fraction) {
        this.mFraction = fraction;
        invalidateSelf();
    }
}
