package com.filetool.util;


import com.cacheserverdeploy.deploy.Graph;

import java.util.List;

/**
 * Created by rhg on 2017/3/20.
 */
public class SortUtil {
    public static void sortByNumber(Graph graph, List<Integer> list, int number) {

        int last = list.size() - 1;
        int start = 0;
        int startNum, lastNum;

        while (last > start) {

            startNum = graph.getEdges()[list.get(start)].getBandWidth();

            if (startNum >= number) {
                start++;
                continue;
            }
            lastNum = graph.getEdges()[list.get(last)].getBandWidth();
            if (lastNum < number) {
                last--;
                continue;
            }
            list.set(start, list.get(start) ^ list.get(last));
            list.set(last, list.get(start) ^ list.get(last));
            list.set(start, list.get(start) ^ list.get(last));

        }
    }
}
