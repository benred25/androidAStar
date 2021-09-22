package com.example.a_star;


import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class SketchModel {

    ArrayList<SketchListener> subscribers;
    boolean drawing;
    List<List<Spot>> spotGrid;
    int spotWidth;
    int spotHeight;
    int rows, cols;
    float curX, curY;
    Spot start, end,  currentSpot;


    public SketchModel() {
        this.subscribers = new ArrayList<>();
        this.drawing = true;
        this.spotGrid = new ArrayList<List<Spot>>();
        this.rows = 48;
        this.cols = 24;
        this.start = null;
        this.end = null;

    }

    public Spot getCurrentSpot() {
        return currentSpot;
    }

    public void setCurrentSpot(Spot currentSpot) {
        this.currentSpot = currentSpot;
    }

    public Spot getStart() {
        return start;
    }

    public void setStart(Spot start) {
        this.start = start;
    }

    public Spot getEnd() {
        return end;
    }

    public void setEnd(Spot end) {
        this.end = end;
    }

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public List<List<Spot>> getSpotGrid() {
        return spotGrid;
    }

    public void setSpotGrid(List<List<Spot>> spotGrid) {
        this.spotGrid = spotGrid;
    }

    public float getCurX() {
        return curX;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public float getCurY() {
        return curY;
    }

    public void setCurY(float curY) {
        this.curY = curY;
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
        this.notifySubscribers();
    }

    public void setSpot(float x, float y, int color) {
        Spot curSpot = new Spot(-1,-1,-1,-1,-1,-1, -1, -1);
        float distance = 9999999999f;

        for (List<Spot> row : this.getSpotGrid()) {
            for (Spot spot : row) {
                float tempDist = Math.abs(x - spot.getMidx()) + Math.abs(y - spot.getMidy());
                if (tempDist < distance) {
                    distance = tempDist;
                    curSpot = spot;
                }
            }
        }

        this.currentSpot = curSpot;
        this.currentSpot.setColor(color);

        if (this.currentSpot == start) {
            this.setStart(null);
        }
        if (this.currentSpot == end) {
            this.setEnd(null);
        }

        this.notifySubscribers();
    }

    private List<Spot> binSearchFirst(List<List<Spot>> arr, float givenY, int low, int high) {
        int mid = (low + high) / 2;


        if (givenY-arr.get(mid).get(0).getY() <= this.spotHeight && givenY-arr.get(mid).get(0).getY() >= 0) {
            return arr.get(mid);
        } else if (givenY-arr.get(mid).get(0).getY() < 0) {
            return binSearchFirst(arr, givenY, low, mid-1);
        } else {
            return binSearchFirst(arr, givenY, mid+1, high);
        }

    }

    private Spot binSearchSecond(List<Spot> arr, float givenX, int low, int high) {
        int mid = (low + high) / 2;


        if (givenX-arr.get(mid).getX() <= this.spotHeight && givenX-arr.get(mid).getX() >= 0) {
            return arr.get(mid);
        } else if (givenX-arr.get(mid).getX() < 0) {
            return binSearchSecond(arr, givenX, low, mid-1);
        } else {
            return binSearchSecond(arr, givenX, mid+1, high);
        }

    }

    public void reset() {
        this.setStart(null);
        this.setEnd(null);
        this.spotGrid = new ArrayList<List<Spot>>();
        this.createGrid();
    }

    public float h(Spot s1, Spot s2) {
        return Math.abs(s1.getMidx() - s2.getMidx()) + Math.abs(s1.getMidy() - s2.getMidy());
    }


//    public void findPath() {
//        // TODO write A star pathfinding algorithm
//        int count = 0;
//        LinkedList<Spot> open_set = new LinkedList<>();
//        this.start.setCount(count);
//        open_set.add(this.start);
//        Hashtable<Spot, Spot> came_from = new Hashtable<>();
//        this.start.setG_score(0f);
//        this.start.setF_score(h(this.start, this.end));
//
//        ArrayList<Spot> open_set_hash = new ArrayList<>();
//        open_set_hash.add(this.start);
//
//        while (!open_set.isEmpty()) {
//
//            Spot current = open_set.poll();
//            open_set_hash.remove(current);
//
//            if (current == this.end) {
//                // TODO this happens when it finds the end
//                this.drawPath(came_from, current);
//                return;
//            }
//
//            for (Spot neighbor : current.getNeighbors()) {
//                float temp_g_score = current.getG_score() + 1;
//
//                if (temp_g_score < neighbor.getG_score()) {
//                    came_from.put(neighbor, current);
//                    neighbor.setG_score(temp_g_score);
//                    neighbor.setF_score(temp_g_score + h(neighbor, this.end));
//
//                    if (!open_set_hash.contains(neighbor)) {
//                        count++;
//                        neighbor.setCount(count);
//                        open_set.add(neighbor);
//                        open_set_hash.add(neighbor);
//                        neighbor.make_open();
//                    }
//                }
//            }
//
//            this.notifySubscribers();
//
//
//            if (current != this.start) {
//                current.make_closed();
//            }
//        }
//
//
//    }

    public void findPath() throws InterruptedException {
        Queue<Spot> open = new PriorityQueue<>(new CustomComparator());
        ArrayList<Spot> closed = new ArrayList<>();
        this.start.setF_score(0);
        open.add(this.start);

        while (!open.isEmpty()) {
            Spot current = open.poll();
            closed.add(current);

            if (current == this.end) {
                // TODO this is when it finds the path
                Spot path = current;
                while (path.getParent() != this.start) {
                    path.make_path();
                    path = path.getParent();
                }
                path.make_path();
                this.end.make_end();
                this.notifySubscribers();
                return;
            }

            for (Spot neighbor : current.getNeighbors()) {
                if (neighbor.getParent() == null) {
                    neighbor.setParent(current);
                }
                if (closed.contains(neighbor)) {
                    continue;
                }

                neighbor.setG_score(current.getG_score() + 45f);
                neighbor.setF_score(neighbor.getG_score() + h(neighbor, this.end));

                for (Spot open_spot : open) {
                    if (neighbor == open_spot && neighbor.getG_score() > open_spot.getG_score()) {
                        continue;
                    }
                }
                neighbor.make_open();
                open.add(neighbor);

            }

            if (current != this.start) current.make_closed();
        }

    }

    public void drawPath(Hashtable<Spot, Spot> came_from, Spot current) {
        while (came_from.contains(current)) {
            current = came_from.get(current);
            current.make_path();
            this.notifySubscribers();
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

    static class CustomComparator implements Comparator<Spot> {
        @Override
        public int compare(Spot s1, Spot s2) {
            if (s1.getF_score() > s2.getF_score()) {
                return 1;
            } else if (s1.getF_score() < s2.getF_score()) {
                return -1;
            }
            return 0;
        }
    }

}
