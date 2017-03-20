package com.cacheserverdeploy.deploy;


import com.filetool.util.SortUtil;

import java.util.*;

/**
 * 问题解决类
 */
public class Solution {

    /**
     * 记录每个结点被每个子块访问的次数
     */
    class VertexIdWrap {
        public VertexIdWrap(int vId, int visitedCnt) {
            this.vId = vId;
            this.visitedCnt = visitedCnt;
        }

        private int vId;
        private int visitedCnt;
        private ArrayList<Integer> consumerList;

        void addOne() {
            visitedCnt++;
        }

        void addConsumer(int vID) {
            if (consumerList == null) {
                consumerList = new ArrayList<>();
            }
            consumerList.add(vID);
        }
    }

    Graph graph;
    NetVertex vertex;
    VertexIdWrap[] vVisitedNum;
    IStrategy solverStrategy;


    public Solution(Graph graph) {
        this.graph = graph;
        vVisitedNum = new VertexIdWrap[graph.netNodeNum];
        solverStrategy = new SolverStrategy(graph);
    }

    public void getSolution() {
        int depth = getDepth(graph.netNodeNum);//获取搜索深度
        Map<Integer, List<Integer>> edgeSetMap = new HashMap<>(graph.consumerNum);
        for (int i = 0; i < graph.consumerNum; i++) {
            List<Integer> solvedEdge = new ArrayList<>(graph.linkNum);
            traverse(graph.consumerVertices[i], solvedEdge, depth);
            dealEdgeByDemands(solvedEdge, graph.consumerVertices[i]);
            edgeSetMap.put(graph.consumerVertices[i].vID, solvedEdge);
        }

        Arrays.asList(vVisitedNum).sort(new Comparator<VertexIdWrap>() {
            @Override
            public int compare(VertexIdWrap o1, VertexIdWrap o2) {
                if (o1 == null) {
                    return 0;
                }
                if (o2 == null) {
                    return 0;
                }
                if (o1.visitedCnt > o2.visitedCnt)
                    return -1;
                else if (o1.visitedCnt == o2.visitedCnt) {
                    return 0;
                }
                return 1;
            }
        });


        solverStrategy.solveProblem(edgeSetMap, vVisitedNum);

        System.out.print("");
    }

    /**
     * 处理每个消费者扩展边
     *
     * @param solvedEdge 已经寻找到的扩展变集合
     * @param consumer   消费结点
     */
    private void dealEdgeByDemands(List<Integer> solvedEdge, ConsumerVertex consumer) {
        int demand = consumer.getConsumeDemand();
        SortUtil.sortByNumber(graph, solvedEdge, demand);
    }


    /**
     * 遍历每个消费结点下面的depth层深度
     *
     * @param root
     * @param solvedEdge
     * @param depth
     */
    private void traverse(ConsumerVertex root, List<Integer> solvedEdge, int depth) {
        NetVertex tempVertex = graph.netVertices[root.getDirectVertexID()];//与服务器直连的网络结点
        Deque<Integer> deque = new ArrayDeque<>();//用于广度优先遍历
        deque.add(tempVertex.vID);//将根入队列
        boolean[] vFlag = new boolean[graph.netNodeNum];//标记访问过的点，一定是所有的度都遍历完才标记
        boolean[] eFlag = new boolean[graph.linkNum];//标记访问过的边
        int loopNum;//记录每层需要循环的次数
        int d = 0;//记录当前遍历的层数
        while (d < depth) {
            loopNum = deque.size();
            while (loopNum != 0) {
                int vIndex = deque.pollFirst();
                if (vFlag[vIndex]) {
                    loopNum--;
                    continue;
                }
                if (vVisitedNum[vIndex] == null) {
                    vVisitedNum[vIndex] = new VertexIdWrap(vIndex, 0);
                }
                vVisitedNum[vIndex].addOne();
                vVisitedNum[vIndex].addConsumer(root.vID);//将当前关联的消费结点添加到访问的顶点中


                tempVertex = graph.netVertices[vIndex];
                vFlag[tempVertex.vID] = true;
                for (Integer link : tempVertex.getLinkIDs()) {
                    if (!eFlag[link]) {
                        eFlag[link] = true;
                        solvedEdge.add(link);
                    } else
                        continue;
                    if (graph.edges[link].vertex1 != tempVertex.vID) {
                        deque.add(graph.edges[link].vertex1);
                    } else
                        deque.add(graph.edges[link].vertex2);
                }
                loopNum--;
            }
            d++;
        }
    }

    /**
     * 获取搜索的有效深度
     *
     * @param totalVertexNumber
     * @return
     */
    public int getDepth(int totalVertexNumber) {
        int depth = 0;
        while (totalVertexNumber > 1) {
            totalVertexNumber >>= 1;
            depth++;
        }
        return depth - 1;
    }
}
