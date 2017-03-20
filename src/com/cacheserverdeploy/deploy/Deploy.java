package com.cacheserverdeploy.deploy;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Deploy {

    /**
     * 你需要完成的入口
     * <功能详细描述>
     *
     * @param graphContent 用例信息文件
     * @return [参数说明] 输出结果信息
     * @see [类、类#方法、类#成员]
     */
    public static String[] deployServer(String[] graphContent) {
        Graph graph = new Graph();
        retrieveData(graphContent, graph);
        Solution solution = new Solution(graph);
        solution.getSolution();

        /**do your work here**/
        return new String[]{"17", "\r\n", "0 8 0 20"};
    }

    private static void retrieveData(String[] graphContent, Graph graph) {
        String[] str = graphContent[0].split("\\s");
        graph.netNodeNum = Integer.parseInt(str[0]);
        graph.linkNum = Integer.parseInt(str[1]);
        graph.consumerNum = Integer.parseInt(str[2]);
        graph.perServerCost = Integer.parseInt(graphContent[2]);

        NetVertex[] netNodes = new NetVertex[graph.netNodeNum];
        ConsumerVertex[] consumerVertices = new ConsumerVertex[graph.consumerNum];
        Edge[] edges = new Edge[graph.linkNum];
        int startID, endID;

        /**
         * 获取消费结点
         */
        for (int i = graph.linkNum + 5, length = graph.linkNum + 5 + graph.consumerNum; i < length; i++) {
            str = graphContent[i].split("\\s");
            startID = Integer.parseInt(str[0]);
            endID = Integer.parseInt(str[1]);
            netNodes[endID] = new NetVertex(endID);
            consumerVertices[startID] = new ConsumerVertex(startID, Integer.parseInt(str[2]), endID);
        }
        graph.consumerVertices = consumerVertices;

        int linkBandWidth, costPerBand;
        /**
         * 获取网络结点
         */
        for (int i = 4, eID = 0, length = graph.linkNum + 4; i < length; i++, eID++) {
            str = graphContent[i].split("\\s");
            startID = Integer.parseInt(str[0]);
            endID = Integer.parseInt(str[1]);
            linkBandWidth = Integer.parseInt(str[2]);
            costPerBand = Integer.parseInt(str[3]);

            Edge edge = new Edge(eID, startID, endID, costPerBand, linkBandWidth);
            edges[eID] = edge;

            if (netNodes[startID] == null) {
                netNodes[startID] = new NetVertex(startID);
            }
            if (netNodes[endID] == null) {
                netNodes[endID] = new NetVertex(endID);
            }

            netNodes[startID].putChildVertex(eID, linkBandWidth, netNodes[endID]);
            netNodes[endID].putChildVertex(eID, linkBandWidth, netNodes[startID]);

        }

        graph.netVertices = netNodes;

        graph.edges = edges;

    }

}
