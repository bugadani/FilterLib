package hu.bugadani.filterlib.filter;

public abstract class Filter {
    public static double convolve(double[] buffer, double[] coefficients, int baseIndex) {
        double result = 0;

        for (int i = 0; i < buffer.length && i < coefficients.length; ++i) {
            result += buffer[(baseIndex + i) % buffer.length] * coefficients[buffer.length - i - 1];
        }

        return result;
    }

    public abstract double filter(double value);

    public abstract void clear();
}
