package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;

public class MinFilter extends Filter {

    private double mMinValue = Double.MAX_VALUE;

    @Override
    public double filter(double value) {

        mMinValue = Math.min(mMinValue, value);

        return mMinValue;
    }

    @Override
    public void clear() {
        mMinValue = Double.MAX_VALUE;
    }
}
