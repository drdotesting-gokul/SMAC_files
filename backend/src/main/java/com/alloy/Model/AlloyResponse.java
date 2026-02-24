package com.alloy.Model;

public class AlloyResponse {

    private double deltaHmix;
    private double deltaSmix;

    public AlloyResponse(double deltaHmix, double deltaSmix) {
        this.deltaHmix = deltaHmix;
        this.deltaSmix = deltaSmix;
    }

    public double getDeltaHmix() {
        return deltaHmix;
    }

    public double getDeltaSmix() {
        return deltaSmix;
    }
}