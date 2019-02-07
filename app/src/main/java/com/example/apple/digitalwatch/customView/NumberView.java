package com.example.apple.digitalwatch.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.view.View;


public class NumberView extends View {

    private Paint numberPaint, zeroPaint;
    private String number;
    private int x, y;

    public NumberView(Context context) {
        super(context);
        init();
    }

    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        zeroPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        zeroPaint.setColor(Color.LTGRAY);
        zeroPaint.setStrokeWidth(5f);
        zeroPaint.setStyle(Paint.Style.STROKE);

        numberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setStyle(Paint.Style.STROKE);
        numberPaint.setStrokeWidth(5f);
        numberPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        zeroDraw(canvas);
        numberDraw(canvas, number);
    }

    private void zeroDraw(Canvas canvas) {
        x = getWidth();
        y = getHeight();
        canvas.drawLine(10, y/2, 10, 5, zeroPaint);
        canvas.drawLine(10, 5, x - 10, 5, zeroPaint );
        canvas.drawLine(x - 10 , 5, x - 10 , y/2,zeroPaint);
        canvas.drawLine(x - 10, y/2, x - 10 , y - 5,zeroPaint);
        canvas.drawLine(x - 10, y - 5,10, y - 5,zeroPaint);
        canvas.drawLine(10, y - 5, 10, y/2,zeroPaint);
        canvas.drawLine(10, y/2, x - 10, y/2, zeroPaint);
    }

    public void setText(String number){
        this.number = number;
    }

    private void numberDraw(Canvas canvas, String number){
        if (number.equals("0") || number.equals("4") || number.equals("5") || number.equals("6")
                || number.equals("8") || number.equals("9")) {
            canvas.drawLine(10, y/2, 10, 5, numberPaint);
        }
        if (number.equals("0") || number.equals("2") || number.equals("3") || number.equals("5")
                || number.equals("6") || number.equals("7")  || number.equals("8")  || number.equals("9")) {
            canvas.drawLine(10, 5, x - 10, 5, numberPaint);
        }
        if (number.equals("0") || number.equals("1") || number.equals("2") || number.equals("3") ||
                number.equals("4") || number.equals("7")  || number.equals("8")
                || number.equals("9")) {
            canvas.drawLine(x - 10 , 5, x - 10 , y/2, numberPaint);
        }
        if (!number.equals("2")) {
            canvas.drawLine(x - 10, y/2, x - 10 , y - 5, numberPaint);
        }
        if (number.equals("0") || number.equals("2") || number.equals("3") || number.equals("5") ||
                number.equals("6")  || number.equals("8") || number.equals("9")) {
            canvas.drawLine(x - 10, y - 5,10, y - 5, numberPaint);
        }
        if (number.equals("0") || number.equals("2") || number.equals("6") || number.equals("8")) {
            canvas.drawLine(10, y - 5, 10, y/2, numberPaint);
        }
        if (!(number.equals("1") || number.equals("7") || number.equals("0"))) {
            canvas.drawLine(10, y/2, x - 10, y/2, numberPaint);
        }
    }
}
