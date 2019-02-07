package com.example.apple.digitalwatch.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.digitalwatch.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DigitWatch extends LinearLayout {

    private NumberView hourFirst, hourSecond, minuteFirst, minuteSecond, secondFirst, secondSecond;
    private TextView dots, dots2;
    private String hourF, hourS, minuteF, minuteS, secondF, secondS;

    public DigitWatch(Context context) {
        super(context);
        init();
    }

    public DigitWatch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DigitWatch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }



    private void init() {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.digit_watch_view, this, true);
        hourFirst = findViewById(R.id.hourFirst);
        hourSecond = findViewById(R.id.hourSecond);
        minuteFirst = findViewById(R.id.minuteFirst);
        minuteSecond = findViewById(R.id.minuteSecond);
        secondFirst = findViewById(R.id.secondFirst);
        secondSecond = findViewById(R.id.secondSecond);
        dots = findViewById(R.id.dots);
        dots2 = findViewById(R.id.dots2);
        dots.setText(":");
        dots2.setText(":");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String time = simpleDateFormat.format(calendar.getTime());
        invalidateValue(time);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void invalidateValue(String time) {
        hourF = String.valueOf(time.charAt(0));
        hourS = String.valueOf(time.charAt(1));
        minuteF = String.valueOf(time.charAt(3));
        minuteS = String.valueOf(time.charAt(4));
        secondF = String.valueOf(time.charAt(6));
        secondS = String.valueOf(time.charAt(7));
        int a = Integer.parseInt(secondS);
        if (a % 2 == 0) {
            dots.setVisibility(VISIBLE);
            dots2.setVisibility(VISIBLE);
        } else {
            dots.setVisibility(INVISIBLE);
            dots2.setVisibility(INVISIBLE);
        }
        hourFirst.setText(String.valueOf(hourF));
        hourSecond.setText(String.valueOf(hourS));
        minuteFirst.setText(String.valueOf(minuteF));
        minuteSecond.setText(String.valueOf(minuteS));
        secondFirst.setText(String.valueOf(secondF));
        secondSecond.setText(String.valueOf(secondS));
    }
}
