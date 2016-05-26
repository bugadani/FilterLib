package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;

public class DampenedMaxFilter extends Filter {

    private double mValue;
    private double mDampening;

    public DampenedMaxFilter(double dampening) {
        mDampening = dampening;
        mValue = Double.NEGATIVE_INFINITY;
    }

    @Override
    public double filter(double value) {
        mValue *= mDampening;
        if (value > mValue) {
            mValue = value;
        }
        return mValue;
    }

    @Override
    public void clear() {
        mValue = Double.NEGATIVE_INFINITY;
    }
}
