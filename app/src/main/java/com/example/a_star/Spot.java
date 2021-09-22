package com.example.a_star;

import android.graphics.Color;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Spot {

    int row, col, width, height, total_rows, total_cols;
    float x, y;
    float midx, midy;
    int color;
    float f_score, g_score;
    Spot parent;
    ArrayList<Spot> neighbors;


    public Spot(int row, int col, int width, int height, int total_rows, int total_cols, float x, float y) {
        this.row = row;
        this.col = col;
        this.width = width;
        this.height = height;
        this.total_rows = total_rows;
        this.total_cols = total_cols;
        this.x = x;
        this.y = y;
        this.midx = this.x + ((float)this.width / 2);
        this.midy = this.y + ((float)this.height / 2);
        this.color = Color.WHITE;
        this.f_score = 0f;
        this.g_score = 0f;
        this.parent = null;
        this.neighbors = new ArrayList<>();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getMidx() {
        return midx;
    }

    public void setMidx(float midx) {
        this.midx = midx;
    }

    public float getMidy() {
        return midy;
    }

    public void setMidy(float midy) {
        this.midy = midy;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Pair<Integer, Integer> get_pos() {
        return new Pair(this.row, this.col);
    }

    public float getF_score() {
        return f_score;
    }

    public void setF_score(float f_score) {
        this.f_score = f_score;
    }

    public float getG_score() {
        return g_score;
    }

    public void setG_score(float g_score) {
        this.g_score = g_score;
    }

    public Spot getParent() {
        return parent;
    }

    public void setParent(Spot parent) {
        this.parent = parent;
    }

    public boolean is_closed() {
        return this.color == Color.RED;
    }

    public boolean is_open() {
        return this.color == Color.GREEN;
    }

    public boolean is_barrier() {
        return this.color == Color.BLACK;
    }

    public boolean is_start() {
        return this.color == Color.YELLOW;
    }

    public boolean is_end() {
        return this.color == Color.BLUE;
    }

    public void reset() {
        this.color = Color.WHITE;
    }

    public void make_closed() {
        this.color = Color.RED;
    }

    public void make_open() {
        this.color = Color.GREEN;
    }

    public void make_barrier() {
        this.color = Color.BLACK;
    }

    public void make_end() {
        this.color = Color.BLUE;
    }

    public void make_start() {
        this.color = Color.YELLOW;
    }

    public void make_path() {
        this.color = Color.MAGENTA;
    }

    public ArrayList<Spot> getNeighbors() {
        return neighbors;
    }

    public void update_neighbors(List<List<Spot>> grid) {
        this.neighbors.clear();

        if (row < total_rows - 1 && !grid.get(row+1).get(col).is_barrier()) {
            neighbors.add(grid.get(row+1).get(col));
        }

        if (row > 0 && !grid.get(row-1).get(col).is_barrier()) {
            neighbors.add(grid.get(row-1).get(col));
        }

        if (col < total_cols - 1 && !grid.get(row).get(col+1).is_barrier()) {
            neighbors.add(grid.get(row).get(col+1));
        }

        if (col > 0 && !grid.get(row).get(col-1).is_barrier()) {
            neighbors.add(grid.get(row).get(col-1));
        }
    }

}





































