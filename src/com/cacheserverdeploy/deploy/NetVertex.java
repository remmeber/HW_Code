package com.cacheserverdeploy.deploy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 网络结点类
 */
public class NetVertex extends Vertex {

    /**
     * 保存的孩子结点
     */
    private HashMap<Integer, NetVertex> child = new HashMap<>(16);

    /**
     * 链路数
     */
    private int linkNumber;

    /**
     * 当前结点下的链路ID链表
     */
    private ArrayList<Integer> linkIDs = new ArrayList<>(20);

    public NetVertex(int vID) {
        super(vID);
    }

    public void putChildVertex(int relativeLinkID, int relativeLinkCost, NetVertex netVertex) {

        if (child.containsKey(relativeLinkCost))
            return;
        child.put(relativeLinkCost, netVertex);
        linkIDs.add(relativeLinkID);
        linkNumber++;
    }

    public int getLinkNumber() {
        return linkNumber;
    }

    public ArrayList<Integer> getLinkIDs() {
        return linkIDs;
    }
}
