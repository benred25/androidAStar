package com.example.a_star;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class SketchController implements View.OnTouchListener {

    SketchModel model;

    private enum State {READY, PENDING, DRAGGING, RUNNING, DONE}
    private State currentState = State.READY;

    public SketchController() {

    }

    public void run() {
        currentState = State.RUNNING;

        for (List<Spot> l : model.spotGrid) {
            for (Spot s : l) {
                s.update_neighbors(model.spotGrid);
            }
        }

        model.findPath();
        currentState = State.DONE;
    }

    public void makeReady() {
        currentState = State.READY;
    }

    public void setModel(SketchModel aSketchModel) {
        model = aSketchModel;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch(currentState) {
            case READY:
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        model.setCurX(event.getX());
                        model.setCurY(event.getY());
                        currentState = State.PENDING;
                        break;
                }
                break;

            case PENDING:
                switch(event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // set either the start or end
                        if (model.getStart() == null) {
                            model.setSpot(model.getCurX(), model.getCurY(), Color.YELLOW);
                            model.setStart(model.getCurrentSpot());
                        } else if (model.getEnd() == null) {
                            model.setSpot(model.getCurX(), model.getCurY(), Color.BLUE);
                            model.setEnd(model.getCurrentSpot());
                        }
                        currentState = State.READY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (model.getStart() == null || model.getEnd() == null) {
                            // set either the start or end
                            if (model.getStart() == null) {
                                model.setSpot(model.getCurX(), model.getCurY(), Color.YELLOW);
                                model.setStart(model.getCurrentSpot());
                            } else if (model.getEnd() == null) {
                                model.setSpot(model.getCurX(), model.getCurY(), Color.BLUE);
                                model.setEnd(model.getCurrentSpot());
                            }
                            currentState = State.READY;
                            break;
                        }
                        currentState = State.DRAGGING;
                        break;
                }
                break;

            case DRAGGING:
                switch(event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        model.setCurX(event.getX());
                        model.setCurY(event.getY());
                        if (model.isDrawing()) {
                            model.setSpot(model.getCurX(), model.getCurY(), Color.BLACK);
                        } else {
                            model.setSpot(model.getCurX(), model.getCurY(), Color.WHITE);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        currentState = State.READY;
                        break;
                }
                break;

        }
        return true;
    }
}
