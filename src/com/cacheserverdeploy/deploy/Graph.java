package com.cacheserverdeploy.deploy;

/**
 * 图
 */
public class Graph {
    /**
     * 网络结点的数量
     */
    int netNodeNum;
    /**
     * 链路数量
     */
    int linkNum;
    /**
     * 消费结点的数量
     */
    int consumerNum;
    /**
     * 每台服务器的费用
     */
    int perServerCost;
    /**
     * 网络结点的数组
     */
    NetVertex[] netVertices;
    /**
     * 消费结点的数组
     */
    ConsumerVertex[] consumerVertices;
    /**
     * 边/链路的数组
     */
    Edge[] edges;

    public Edge[] getEdges() {
        return edges;
    }
}
