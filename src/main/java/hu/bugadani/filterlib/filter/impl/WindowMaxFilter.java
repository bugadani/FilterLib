package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;
import hu.bugadani.filterlib.utils.CircularDoubleBuffer;

public class WindowMaxFilter extends Filter {

    private final CircularDoubleBuffer mBuffer;

    public WindowMaxFilter(int size) {
        mBuffer = new CircularDoubleBuffer(size);
    }

    @Override
    public double filter(double value) {
        mBuffer.add(value);
        return mBuffer.max();
    }

    @Override
    public void clear() {
        mBuffer.clear();
    }
}
