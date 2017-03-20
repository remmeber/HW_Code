package com.cacheserverdeploy.deploy;

/**
 * 链路类
 */
public class Edge {
    int eID;
    int vertex1;
    int vertex2;
    /**
     * 每带宽耗费
     */
    int perBandCost;
    /**
     * 上下行带宽
     */
    int bandWidth;

    public Edge(int eID, int vertex1, int vertex2, int perBandCost, int bandWidth) {
        this.eID = eID;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.perBandCost = perBandCost;
        this.bandWidth = bandWidth;
    }

    public int getBandWidth() {
        return bandWidth;
    }

    public int getPerBandCost() {
        return perBandCost;
    }
}
