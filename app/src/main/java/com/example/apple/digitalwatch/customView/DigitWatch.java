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

    private TextView hourFirst, hourSecond, minuteFirst, minuteSecond, dots, dots2, secondFirst, secondSecond;
    private int xCenter, yCenter;
    private Paint paint, numberPaint;
    private String hourF, hourS, minuteF, minuteS, secondF, secondS;
    private Path numberPath;

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
        zeroDraw(canvas);
        setNumbers(canvas);
        dotsDraw(canvas);
    }

    private void dotsDraw(Canvas canvas) {
        int second = Integer.parseInt(secondS);
        if(second % 2 == 0) {
            int x = (xCenter + 290) / 2;
            int y = yCenter / 2;
            canvas.drawLine(x, y - 35, x, y - 15, paint);
            canvas.drawLine(x, y + 35, x, y + 15, paint);
            canvas.drawLine(x + 170, y - 35, x + 170, y - 15, paint);
            canvas.drawLine(x + 170, y + 35, x + 170, y + 15, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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

        numberPath = new Path();
        numberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setStrokeWidth(10);
        numberPaint.setColor(Color.GRAY);
        numberPaint.setStyle(Paint.Style.STROKE);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String time = simpleDateFormat.format(calendar.getTime());
        invalidateValue(time);
        setWillNotDraw(false);
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

    private void zeroDraw(Canvas canvas) {
        canvas.save();
        int x = canvas.getWidth();
        int y = canvas.getHeight();
        xCenter = x / 2;
        yCenter = y / 2;
        canvas.drawPath(zeroBackground(xCenter, yCenter), numberPaint);
        canvas.drawPath(zeroBackground(xCenter + 140, yCenter), numberPaint);
        canvas.drawPath(zeroBackground(xCenter + 340, yCenter), numberPaint);
        canvas.drawPath(zeroBackground(xCenter + 480, yCenter), numberPaint);
        canvas.drawPath(zeroBackground(xCenter + 680, yCenter), numberPaint);
        canvas.drawPath(zeroBackground(xCenter + 820, yCenter), numberPaint);
    }

    private void setNumbers(Canvas canvas) {
        numberDraw(xCenter, yCenter, hourF, canvas);
        numberDraw(xCenter + 140, yCenter, hourS, canvas);
        numberDraw(xCenter + 340, yCenter, minuteF, canvas);
        numberDraw(xCenter + 480, yCenter, minuteS, canvas);
        numberDraw(xCenter + 680, yCenter, secondF, canvas);
        numberDraw(xCenter + 820, yCenter, secondS, canvas);
    }

    private Path zeroBackground(int x, int y) {
        x /= 2;
        y /= 2;
        numberPath.moveTo(x, y);
        numberPath.lineTo(x, y - 50);
        numberPath.lineTo(x + 50, y - 50);
        numberPath.lineTo(x + 50, y);
        numberPath.lineTo(x + 50, y + 50);
        numberPath.lineTo(x, y + 50);
        numberPath.lineTo(x, y);
        return numberPath;
    }

    private void numberDraw(int x, int y, String number, Canvas canvas) {
        x /= 2;
        y /= 2;
        if (number.equals("0") || number.equals("4") || number.equals("5") || number.equals("6")
                || number.equals("8") || number.equals("9")) {
            canvas.drawLine(x, y, x, y - 50, paint);
        }
        if (number.equals("0") || number.equals("2") || number.equals("3") || number.equals("5")
                || number.equals("6") || number.equals("7")  || number.equals("8")  || number.equals("9")) {
            canvas.drawLine(x, y - 50, x + 50, y - 50, paint);
        }
        if (number.equals("0") || number.equals("1") || number.equals("2") || number.equals("3") ||
                number.equals("4") || number.equals("7")  || number.equals("8")
                || number.equals("9")) {
            canvas.drawLine(x + 50, y - 50, x + 50, y, paint);
        }
        if (!number.equals("2")) {
            canvas.drawLine(x + 50, y, x + 50, y + 50, paint);
        }
        if (number.equals("0") || number.equals("2") || number.equals("3") || number.equals("5") ||
                number.equals("6")  || number.equals("8") || number.equals("9")) {
            canvas.drawLine(x + 50, y + 50, x, y + 50, paint);
        }
        if (number.equals("0") || number.equals("2") || number.equals("6") || number.equals("8")) {
            canvas.drawLine(x, y + 50, x, y, paint);
        }
        if (!(number.equals("1") || number.equals("7") || number.equals("0"))) {
            canvas.drawLine(x, y, x + 50, y, paint);
        }
    }
}
