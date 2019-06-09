package com.tsystems.javaschool.tasks.pyramid;

import java.util.Comparator;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */


    public int[][] buildPyramid(List<Integer> inputNumbers) {
        int stageCount=0;
        try {
            inputNumbers.sort(Comparator.naturalOrder());
        }catch (Throwable e) {
            throw new CannotBuildPyramidException();
        }
        for (int i=1, tempListSize = inputNumbers.size(); tempListSize !=0; i++, stageCount++) {
            if ((tempListSize-=i) < 0) {
                throw new CannotBuildPyramidException();
            }
        }
        int inputListSize=inputNumbers.size()-1;
        int[][] result = new int [stageCount][stageCount*2-1];

        for (int y=stageCount-1, offset=0; y>=0; y--, offset++) {
            for (int x=(stageCount*2-2-offset); x>=offset; x-=2) {
                result[y][x]= inputNumbers.get(inputListSize--);
            }
        }
        return result;
    }
}