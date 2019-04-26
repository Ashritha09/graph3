/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashritha
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class grphs3 {

    /**
     * @param args the command line arguments
     */
    
    class Edge implements Comparable<Edge> {
    public int begin, end, weight;
    public  Edge(int begin, int end, int weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
    public int other(int v) {
        if (v == begin) {
            return end;
        } else {
            return begin;
        }
    }
}
class Graph {
    public Set<Edge>[] adj;
    public Graph(int n, int m) {
        adj = new HashSet[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new HashSet<Edge>();
        }
    }
    public void addEdge(int v, int u, int w) {
        Edge e = new Edge(v, u, w);
        adj[v].add(e);
        adj[u].add(e);
    }
}
    public static void main(String[] args) {
        // TODO code application logic here
        
                Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Graph g = new Graph(n, m);
        while (m-- > 0) {
            g.addEdge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
        }
        int s = sc.nextInt() - 1;
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(new Integer(s));
        int cnt = 0;
        int weight = 0;
        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();
        while (cnt < n - 1 ) {
            for (Edge e: g.adj[s]) {
                if (!visited.contains(new Integer(e.other(s)))) {
                    minHeap.add(e);
                }
            }
            Edge nextE = findNext(visited, minHeap);
            if (nextE == null) {
                System.out.println("No single tree");
                return;
            }
            weight += nextE.weight;
            if (visited.contains(new Integer(nextE.begin))) {
                s = nextE.end;
            } else {
                s = nextE.begin;
            }
            visited.add(new Integer(s));
            cnt++;
        
        }  
        System.out.println(weight);
    }
    static Edge findNext(Set<Integer> visited, PriorityQueue<Edge> minHeap ) {
        if (minHeap.isEmpty()) {
            return null;
        }
        Edge e = minHeap.poll();
        if (visited.contains(new Integer(e.begin)) && visited.contains(new Integer(e.end))) {
            return findNext(visited, minHeap);
        } else {
            return e;
        }
    }

    }
    
}
