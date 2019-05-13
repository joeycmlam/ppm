package com.am.portfolio;

import java.util.HashMap;

public class nodeHolding {

    private int curLevel;
    private String curItem;
    private HashMap<String, nodeHolding> curTree;
    private nodeHolding childNode;
    private holding curHolding;

    public int currentLevel() {
        return this.curLevel;
    }

    public nodeHolding(int addLevel, String addItem) {
        this.curLevel = addLevel;
        this.curItem = addItem;
        this.curTree = new HashMap<>();
    }

    public void addNode(holding aHolding) {
        String value = "";
        int nextLevel = this.curLevel +1;
        switch (this.curLevel) {
            case 0:
                value = "Total Investment";
                break;
            case 1:
                value = aHolding.region;
                break;
            case 2:
                value = aHolding.country;
                break;
            case 3:
                value = aHolding.sector;
                break;
        }
        if ("".equals(value) ) {
            //create leaf
            this.curHolding = aHolding;
        } else {
            nodeHolding aNode;
            if (this.curTree.containsKey(value)) {
                aNode = this.curTree.get(value);
            } else {
                //create node
                aNode = new nodeHolding(nextLevel, value);
                aNode.addNode(aHolding);
            }
        }

    }
}
