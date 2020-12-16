package com.example.a_star;


import java.util.ArrayList;
import java.util.List;


public class SketchModel {

    ArrayList<SketchListener> subscribers;
    boolean drawing;
    List<List<Spot>> spotGrid;
    int spotWidth;
    int spotHeight;
    int rows, cols;

    public SketchModel() {
        this.subscribers = new ArrayList<>();
        this.drawing = true;
        this.spotGrid = new ArrayList<List<Spot>>();
        this.rows = 48;
        this.cols = 24;


    }

    public void createGrid() {
        float y = 0;
        for (int i=0; i<this.rows; i++) {
            List<Spot> curRow = new ArrayList<>();
            float x = 0;
            for (int j=0; j<this.cols; j++) {
                curRow.add(new Spot(i, j, this.spotWidth, this.spotHeight, this.rows, this.cols, x, y));
                x = x + this.spotWidth;
            }
            this.spotGrid.add(curRow);
            y = y + this.spotHeight;
        }
    }

    public void addSubscriber(SketchListener subscriber) {
        subscribers.add(subscriber);

    }

    private void notifySubscribers() {
        for (SketchListener sl : subscribers) {
            sl.modelChanged();
        }
    }

}
