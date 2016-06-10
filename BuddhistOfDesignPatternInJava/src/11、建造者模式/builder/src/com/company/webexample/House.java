package com.company.webexample;

import java.util.ArrayList;
import java.util.List;

public class House {
    private String type = null;
    private List<String> features = new ArrayList<String>();

    public House() {

    }

    public House(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setProgress(String s) {
        features.add(s);
    }

    public String toString() {
        StringBuffer ff = new StringBuffer();
        String t = type.substring(6);
        ff.append(t + "\n ");
        for (int i = 0; i < features.size(); i ++) {
             ff.append(features.get(i) + "\n ");
        }
        return ff.toString();
    }
}


