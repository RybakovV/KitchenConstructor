package com.ecru;

import java.util.Arrays;

/**
 * Created by Vitaliy Ryvakov on 04.10.2016.
 */
public class DataSet {

    private String name;
    private int count;

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public DataSet(String name, int count){
        this.name = name;
        this.count = count;
    }
}
