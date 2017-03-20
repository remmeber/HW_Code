package com.cacheserverdeploy.deploy;

/**
 * 消费结点类
 */
public class ConsumerVertex extends Vertex {
    /**
     * 消费结点的消费需求
     */
    private int consumeDemand;
    /**
     * 与消费结点的直连结点ID
     */
    private int directVertexID;

    public ConsumerVertex(int vID,int consumeDemand,int directVertexID) {
        super(vID);
        this.consumeDemand = consumeDemand;
        this.directVertexID = directVertexID;
    }

    public int getConsumeDemand() {
        return consumeDemand;
    }

    public int getDirectVertexID() {
        return directVertexID;
    }
}
