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
        this.neighbors = new ArrayList<>();
    }

    public Pair<Integer, Integer> get_pos() {
        return new Pair(this.row, this.col);
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
        this.color = Color.BLACK;
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

    public void update_neighbors(List<List<Spot>> grid) {

    }

}
