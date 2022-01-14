package struct;

import input.StreamEdge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;


/*
 * Node map store <node identifiers, HashSet<Long>> Node Neighbors> in a HashMap
 */
public class NodeMap {

    public HashMap<Long, HashSet<Long>> map;
    public int numEdges;

    public NodeMap() {
        map = new HashMap<>();
        numEdges = 0;
    }

    public void addEdge(Long src, Long dest) {
        numEdges++;
        if (map.containsKey(src)) {
            HashSet<Long> neighbors = map.get(src);
            neighbors.add(dest);
            map.put(src, neighbors);
        } else {
            HashSet<Long> neighbors = new HashSet<Long>();
            neighbors.add(dest);
            map.put(src, neighbors);
        }

    }

    public void removeEdge(Long src, Long dest) {
        if (map.containsKey(src)) {
            HashSet<Long> neighbors = map.get(src);
            if (neighbors.contains(dest)) {
                numEdges--;
            }
            neighbors.remove(dest);

            if (!neighbors.isEmpty()) {
                map.put(src, neighbors);
            } else {
                map.remove(src);
            }
        }
    }

    public int getDegree(Long vertex) {
        if (map.containsKey(vertex))
            return map.get(vertex).size();
        else
            return 0;
    }

}
