package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;
import hu.bugadani.filterlib.utils.CircularDoubleBuffer;

public class Biquad extends Filter {

    private final double[] a;
    private final double[] b;

    private CircularDoubleBuffer ws;

    public Biquad(double[] num, double[] den) {
        if (den.length != 3 && num.length != 3) {
            throw new IllegalArgumentException();
        }

        this.a = den;
        this.b = num;

        ws = new CircularDoubleBuffer(3);
    }

    @Override
    public double filter(double x) {
        double y = 0;

        for (int i = 0; i < ws.size(); i++) {
            y += b[i] * ws.at(ws.size() - i - 1);
        }

        double w = x;

        for (int i = 1; i < ws.size(); i++) {
            w -= a[i] * ws.at(ws.size() - i);
        }

        ws.add(w);

        return y;
    }

    @Override
    public void clear() {

    }
}
