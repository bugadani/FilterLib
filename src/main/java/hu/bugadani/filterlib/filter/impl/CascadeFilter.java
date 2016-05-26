package hu.bugadani.filterlib.filter.impl;

import hu.bugadani.filterlib.filter.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CascadeFilter extends Filter {
    private final List<Filter> mFilters = new ArrayList<>();

    public CascadeFilter(Filter... filters) {
        mFilters.addAll(Arrays.asList(filters));
    }

    @Override
    public double filter(double value) {
        for (Filter f : mFilters) {
            value = f.filter(value);
        }

        return value;
    }

    @Override
    public void clear() {
        for (Filter f : mFilters) {
            f.clear();
        }
    }
}
