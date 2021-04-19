package com.example.a_star;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;

import java.util.List;

class SketchView extends View implements SketchListener {

    Paint gridPaint;
    Paint spotPaint;
    SketchModel model;
    SketchController controller;
    Display display;
    int maxX, maxY;
    Point size;
    int rows, cols;
    int rowGap, colGap;



    public SketchView(Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);
        gridPaint = new Paint();
        spotPaint = new Paint();
        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getRealSize(size);
        maxX = size.x;
        maxY = size.y;
        rows = 48;
        cols = 24;
        rowGap = maxX / rows * 2 + 1;
        colGap = maxY / cols / 2;
    }

    public void setModel(SketchModel sm) {
        model = sm;
    }


    public void setController(SketchController newC) {
        controller = newC;
        this.setOnTouchListener(controller);
    }

    public void onDraw(Canvas canvas) {
        gridPaint.setStrokeWidth(3);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setColor(Color.GRAY);

        int curX = 0;
        for (int col=0; col<cols; col++) {
            canvas.drawLine(curX, 0, curX, maxY, gridPaint);
            curX = curX + colGap;
        }

        int curY = 0;
        for (int row=0; row<rows; row++) {
            canvas.drawLine(0, curY, maxX, curY, gridPaint);
            curY = curY + rowGap;
        }

        spotPaint.setStyle(Paint.Style.FILL);
        for (List<Spot> row : model.spotGrid) {
            for (Spot spot : row) {
                spotPaint.setColor(spot.color);
                canvas.drawRect(spot.x+1, spot.y+1, spot.x+spot.width-1, spot.y+spot.height-1, spotPaint);
            }
        }
    }

    @Override
    public void modelChanged() {
        this.invalidate();
    }
}
