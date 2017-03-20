package com.cacheserverdeploy.deploy;

import java.util.List;
import java.util.Map;

/**
 * 策略实现类
 */
public class SolverStrategy implements IStrategy {
    Graph graph;
    boolean[] possibleServer;

    public SolverStrategy(Graph graph) {
        this.graph = graph;
        possibleServer = new boolean[graph.netNodeNum];
    }

    /**
     * @param edgeSetMap  每个消费结点对应的区域边集合，并按照对应的消费需求进行了整理
     * @param vVisitedNum 每个网络结点访问次数数组
     */
    @Override
    public void solveProblem(Map<Integer, List<Integer>> edgeSetMap, Solution.VertexIdWrap[] vVisitedNum) {
        int upCost = graph.perServerCost * graph.consumerNum;
        for (int i = 0; i < graph.consumerNum; i++) {

        }
    }
}
