package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;

public class MaxFilter extends Filter {

    private double mMaxValue = Double.MAX_VALUE;

    @Override
    public double filter(double value) {

        mMaxValue = Math.max(mMaxValue, value);

        return mMaxValue;
    }

    @Override
    public void clear() {
        mMaxValue = Double.MAX_VALUE;
    }
}
