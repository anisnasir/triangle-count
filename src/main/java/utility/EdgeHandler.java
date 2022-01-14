package utility;

import struct.NodeMap;
import input.StreamEdge;

public class EdgeHandler {
    public EdgeHandler() {

    }

    public void handleEdgeAddition(StreamEdge item, NodeMap nodeMap) {
        //System.out.println("+ " + item.toString());
        Long src = item.getSource();
        Long dest = item.getDestination();

        nodeMap.addEdge(src, dest);
        nodeMap.addEdge(dest, src);
    }
}
