package com.example.women_security_app;

public class petrolpump {

    private final String name;
    private final String open_now;
    private final Double rating;

    public petrolpump(String name, String open_now, Double rating) {

        this.name = name;
        this.open_now = open_now;
        this.rating = rating;
    }



    public String getName() {
        return name;
    }

    public String getOpen_now() {
        return open_now;
    }

    public Double getRating() {
        return rating;
    }
}
