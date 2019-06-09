package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")

    public boolean find(List x, List y) {

        if (x == null || y == null) {
            throw new IllegalArgumentException("Oops, input collection is null!");
        }

        if (x.isEmpty()) {
            return true;
        }

        int size = x.size();
        int currentIndex = 0;
        Object xItem = x.get(currentIndex);

        for (Object yItem : y) {
            if (xItem.equals(yItem)) {
                if (currentIndex == size - 1) {
                    return true;
                }
                xItem = x.get(++currentIndex);
            }
        }
        return false;
    }
}