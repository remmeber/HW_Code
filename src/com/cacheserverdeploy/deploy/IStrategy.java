package com.cacheserverdeploy.deploy;

import java.util.List;
import java.util.Map;

/**
 * Created by rhg on 2017/3/19.
 */
public interface IStrategy {
    /**
     * @param edgeSetMap  每个消费结点对应的区域边集合
     * @param vVisitedNum 每个网络结点访问次数数组
     */
    void solveProblem(Map<Integer, List<Integer>> edgeSetMap, Solution.VertexIdWrap[] vVisitedNum);
}
