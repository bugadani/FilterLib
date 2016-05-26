package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;

public class DerivatingFilter extends Filter {

    private double mLast;

    public DerivatingFilter() {
        mLast = Double.NaN;
    }

    @Override
    public double filter(double value) {
        double filtered;
        if (!Double.isNaN(mLast)) {
            filtered = value - mLast;
        } else {
            filtered = value;
        }
        mLast = value;
        return filtered;
    }

    @Override
    public void clear() {
        mLast = Double.NaN;
    }
}
