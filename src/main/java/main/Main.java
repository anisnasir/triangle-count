package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

import input.StreamEdge;
import input.StreamEdgeReader;
import struct.NodeMap;
import utility.EdgeHandler;

public class Main {
    public static void main(String args[]) {
        int MILLION = 1000000;
        String inFileName = "/Users/annsi/Datasets/graph/com-lj.ungraph.txt";

        //input reader
        String sep = "\t";
        BufferedReader in = getBufferedReader(inFileName);

        // structs for graph
        NodeMap nodeMap = new NodeMap();
        EdgeHandler utility = new EdgeHandler();

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

        
    }

    private static int getMaxDegree(NodeMap nodeMap) {
        // iterate through the graph and find the max degree edges
        // also sum all the edges to calculate sumEdges
        int maxDegree = -1;
        long sumEdges = 0;
        Set<Long> vertices = nodeMap.map.keySet();
        for (Long vertex : vertices) {
            int degree = nodeMap.getDegree(vertex);
            sumEdges += degree;
            if (degree > maxDegree) maxDegree = degree;

        }

        // calculate average degree by dividing with the number of vertices in the graph
        System.out.println(sumEdges / (double) nodeMap.map.size());

        return maxDegree;
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
