package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;
import hu.bugadani.filterlib.utils.CircularDoubleBuffer;

public class MovingAverageFilter extends Filter {

    private CircularDoubleBuffer mBuffer;

    public MovingAverageFilter(int size) {
        mBuffer = new CircularDoubleBuffer(size);
    }

    @Override
    public double filter(double value) {
        mBuffer.add(value);
        return mBuffer.mean();
    }

    @Override
    public void clear() {
        mBuffer.clear();
    }
}
