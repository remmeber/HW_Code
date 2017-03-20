package com.cacheserverdeploy.deploy;

/**
 * 结点的抽象类
 */
public abstract class Vertex {
    int vID;

    public Vertex(int vID) {
        this.vID = vID;
    }

    public int getvID() {
        return vID;
    }
}
