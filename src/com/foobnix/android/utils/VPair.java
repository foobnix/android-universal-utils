package com.foobnix.android.utils;

import java.io.Serializable;

import android.util.Pair;

public class VPair<F, S> implements Serializable {
    public F first;
    public S second;

    /**
     * Constructor for a Pair.
     *
     * @param first
     *            the first object in the Pair
     * @param second
     *            the second object in the pair
     */
    public VPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

}