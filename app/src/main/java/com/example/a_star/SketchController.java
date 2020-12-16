package com.example.a_star;

import android.view.MotionEvent;
import android.view.View;

public class SketchController implements View.OnTouchListener {

    SketchModel model;

    private enum State {READY, PENDING, DRAGGING}
    private State currentState = State.READY;

    private float curX, curY;

    public SketchController() {

    }

    public void setModel(SketchModel aSketchModel) {
        model = aSketchModel;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
