package com.example.a_star;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    float spotWidth;
    float spotHeight;
    List<List<Spot>> spots;

    public Grid(float spotWidth, float spotHeight) {
        this.spots = new ArrayList<List<Spot>>();
    }

}
