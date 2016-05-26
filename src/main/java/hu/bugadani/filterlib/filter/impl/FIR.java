package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;

import java.util.Arrays;

public class FIR extends Filter {

    private final double[] mCoefficients;
    private final double[] mBuffer;
    private int mPointer;

    public FIR(double[] coefficients) {
        mCoefficients = coefficients;
        mBuffer = new double[coefficients.length];
        mPointer = 0;
    }

    @Override
    public double filter(double value) {
        mBuffer[mPointer] = value;
        double filtered = Filter.convolve(mBuffer, mCoefficients, mPointer);
        mPointer = (mPointer + 1) % mBuffer.length;
        return filtered;
    }

    @Override
    public void clear() {
        Arrays.fill(mBuffer, 0);
        mPointer = 0;
    }
}
