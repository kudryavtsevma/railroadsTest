package com.railroads.dto;

import java.util.ArrayList;

public class BlockTrains {
    public int countWagons;
    public ArrayList<String> trains;

    public BlockTrains(int countWagons, ArrayList<String> trains) {
        this.countWagons = countWagons;
        this.trains = trains;
    }
}
