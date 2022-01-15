package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import input.StreamEdge;
import input.StreamEdgeReader;
import struct.NodeMap;
import utility.EdgeHandler;
import utility.SetFunctions;

public class Main {
    public static void main(String args[]) {
        String inFileName = "/Users/annsi/Datasets/graph/com-lj.ungraph.txt";
        BufferedReader in = getBufferedReader(inFileName);

        long startTime = System.currentTimeMillis();
        NodeMap nodeMap = readGraph(in);
        System.out.println("Time taken to read edges: " + ((System.currentTimeMillis() - startTime) / 1000.0) + " secs");

        startTime = System.currentTimeMillis();
        long triangleCount = getTriangleCount(nodeMap);
        System.out.println("Time taken to count triangles: " + ((System.currentTimeMillis() - startTime) / 1000.0) + " secs");

        System.out.println("Total number of triangles: " + triangleCount); // 177820130
    }

    private static long getTriangleCount(NodeMap nodeMap) {
        long triangleCount = 0;

        Map<Long, HashSet<Long>> neighborMap = nodeMap.getMap();
        Set<Long> vertices = neighborMap.keySet();
        for (Long vertex : vertices) {
            List<Long> neighbors = new ArrayList<>(neighborMap.get(vertex));
            for (int i = 0; i < neighbors.size() - 1; i++) {
                Long neighborA = neighbors.get(i);
                if (vertex < neighborA) {
                    for (int j = i + 1; j < neighbors.size(); j++) {
                        Long neighborB = neighbors.get(j);
                        if (vertex < neighborB) {
                            if (neighborMap.get(neighborA).contains(neighborB) && neighborMap.get(neighborB).contains(neighborA)) {
                                triangleCount++;
                            }
                        }
                    }
                }
            }
        }
        return triangleCount;
    }

    private static NodeMap readGraph(BufferedReader in) {
        // structs for graph
        NodeMap nodeMap = new NodeMap();
        EdgeHandler utility = new EdgeHandler();
        int MILLION = 1000000;

        String sep = "\t";

        //initialize the input reader
        int count = 0;

        StreamEdgeReader reader = new StreamEdgeReader(in, sep);
        try {
            StreamEdge item = reader.nextItem();
            while (item != null) {
                utility.handleEdgeAddition(item, nodeMap);
                item = reader.nextItem();
                count++;
                if (count % MILLION == 0) {
                    System.out.println("Read " + (count / MILLION) + " million edges");
                }
            }
        } catch (Exception ex) {

        }
        return nodeMap;
    }

    private static BufferedReader getBufferedReader(String inFileName) {
        BufferedReader in = null;

        try {
            InputStream rawin = new FileInputStream(inFileName);
            in = new BufferedReader(new InputStreamReader(rawin));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
            System.exit(1);
        }
        return in;
    }
}
