package com.avmhl.leitnary.start;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.avmhl.leitnary.R;

public class Loading extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;

    public Loading(Context context,ProgressBar progressBar,TextView textView,float from,float to)
    {
        this.context=context;
        this.progressBar=progressBar;
        this.textView=textView;
        this.from=from;
        this.to=to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;

    }
}