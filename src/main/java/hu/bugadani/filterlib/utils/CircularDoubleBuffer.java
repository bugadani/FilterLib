package hu.bugadani.filterlib.utils;

import java.util.Arrays;

public class CircularDoubleBuffer {

    public interface Function {
        double apply(double data);
    }

    public interface Filter {
        boolean accept(double data);
    }

    private double[] mBuffer;
    private int mWriteIndex;
    private boolean mIsFull;

    public CircularDoubleBuffer(int capacity) {
        mBuffer = new double[capacity];
        mWriteIndex = 0;
    }

    public void clear() {
        mWriteIndex = 0;
        mIsFull = false;
    }

    public int capacity() {
        return mBuffer.length;
    }

    public int size() {
        if (mIsFull) {
            return mBuffer.length;
        } else {
            return mWriteIndex;
        }
    }

    public void fill(double d) {
        Arrays.fill(mBuffer, d);
        mWriteIndex = 0;
        mIsFull = true;
    }

    public void add(double d) {
        mBuffer[mWriteIndex++] = d;
        if (mWriteIndex == mBuffer.length) {
            mWriteIndex = 0;
            mIsFull = true;
        }
    }

    public boolean isFull() {
        return mIsFull;
    }

    public double at(int index) {
        if (mIsFull) {
            index = modifyIndex(index);
        }
        return mBuffer[index];
    }

    public double mean() {
        return sum() / mBuffer.length;
    }

    public double sum() {
        double sum = 0;
        if (mIsFull) {
            for (double d : mBuffer) {
                sum += d;
            }
        } else {
            for (int i = 0; i < mWriteIndex; i++) {
                sum += mBuffer[i];
            }
        }
        return sum;
    }

    public int maxIndex(int from, int to) {
        int index = from;
        if (mIsFull) {
            from = modifyIndex(from);
            to = modifyIndex(to);
            if (to < from) {
                for (int i = from + 1; i < mBuffer.length; i++) {
                    if (mBuffer[i] > mBuffer[index]) {
                        index = i;
                    }
                }
                for (int i = 0; i < to; i++) {
                    if (mBuffer[i] > mBuffer[index]) {
                        index = i;
                    }
                }
            } else {
                for (int i = from + 1; i < to; i++) {
                    if (mBuffer[i] > mBuffer[index]) {
                        index = i;
                    }
                }
            }
        } else {
            for (int i = from + 1; i < to; i++) {
                if (mBuffer[i] > mBuffer[index]) {
                    index = i;
                }
            }
        }
        return index;
    }

    private int modifyIndex(int to) {
        to += mWriteIndex;
        while (to >= mBuffer.length) {
            to -= mBuffer.length;
        }
        return to;
    }

    public double max(int from, int to) {
        double max = 0;
        if (mIsFull) {
            from = modifyIndex(from);
            to = modifyIndex(to);
            if (to < from) {
                for (int i = from; i < mBuffer.length; i++) {
                    max = Math.max(mBuffer[i], max);
                }
                for (int i = 0; i < to; i++) {
                    max = Math.max(mBuffer[i], max);
                }
            } else {
                for (int i = from; i < to; i++) {
                    max = Math.max(mBuffer[i], max);
                }
            }
        } else {
            for (int i = from; i < to; i++) {
                max = Math.max(mBuffer[i], max);
            }
        }
        return max;
    }

    public double min(int from, int to) {
        double min = 0;
        if (mIsFull) {
            from = modifyIndex(from);
            to = modifyIndex(to);
            if (to < from) {
                for (int i = from; i < mBuffer.length; i++) {
                    min = Math.min(mBuffer[i], min);
                }
                for (int i = 0; i < to; i++) {
                    min = Math.min(mBuffer[i], min);
                }
            } else {
                for (int i = from; i < to; i++) {
                    min = Math.min(mBuffer[i], min);
                }
            }
        } else {
            for (int i = from; i < to; i++) {
                min = Math.min(mBuffer[i], min);
            }
        }
        return min;
    }

    public double max() {
        double max = 0;
        if (mIsFull) {
            for (double d : mBuffer) {
                max = Math.max(d, max);
            }
        } else {
            for (int i = 0; i < mWriteIndex; i++) {
                max = Math.max(mBuffer[i], max);
            }
        }
        return max;
    }

    public double min() {
        double min = 0;
        if (mIsFull) {
            for (double d : mBuffer) {
                min = Math.min(d, min);
            }
        } else {
            for (int i = 0; i < mWriteIndex; i++) {
                min = Math.min(mBuffer[i], min);
            }
        }
        return min;
    }

    public double[] apply(Function f) {
        double[] result;
        if (mIsFull) {
            result = new double[mBuffer.length];
            for (int i = mWriteIndex; i < mBuffer.length; i++) {
                result[i] = f.apply(mBuffer[i]);
            }
        } else {
            result = new double[mWriteIndex];
        }
        for (int i = 0; i < mWriteIndex; i++) {
            result[i] = f.apply(mBuffer[i]);
        }
        return result;
    }

    public double[] filter(Filter f) {
        double[] result;
        int accepted = 0;
        if (mIsFull) {
            result = new double[mBuffer.length];
            for (int i = mWriteIndex; i < mBuffer.length; i++) {
                if (f.accept(mBuffer[i])) {
                    result[i] = mBuffer[i];
                    accepted++;
                }
            }
        } else {
            result = new double[mWriteIndex];
        }
        for (int i = 0; i < mWriteIndex; i++) {
            if (f.accept(mBuffer[i])) {
                result[i] = mBuffer[i];
                accepted++;
            }
        }
        return Arrays.copyOfRange(result, 0, accepted);
    }
}
