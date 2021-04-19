package com.example.a_star;

public class OpenSetObj {

    private int count;
    private Spot spot;

    public OpenSetObj(int count, Spot spot) {
        this.count = count;
        this.spot = spot;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

}
