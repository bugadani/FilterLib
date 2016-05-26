package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;

public class NullFilter extends Filter {

    private static NullFilter sInstance;

    public static Filter instance() {
        if (sInstance == null) {
            sInstance = new NullFilter();
        }

        return sInstance;
    }

    private NullFilter() {
    }

    @Override
    public double filter(double value) {
        return value;
    }

    @Override
    public void clear() {
    }
}
