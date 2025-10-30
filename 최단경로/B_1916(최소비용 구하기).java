import java.io.*;
import java.util.*;

public class Main{
    static class Node{
        int end;
        int dist;
        public Node(int end, int dist){
            this.end = end;
            this.dist = dist;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static int size;
    static ArrayList<Node>[] list;
    static int[] distance;
    
    static void Dijkstra(int start, int end, int dist){
        distance[start] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>( (a,b)->a[1]-b[1] );
        q.offer(new int[]{start,0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(curDist > distance[curNode]) continue;
            for(int i=0; i<list[curNode].size(); i++){
                Node next = list[curNode].get(i);
                if(distance[next.end] > distance[curNode]+next.dist){
                    distance[next.end] = distance[curNode]+next.dist;
                    q.offer(new int[]{ next.end,distance[next.end]});
                }
            }
        }
        sb.append(distance[end]);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        list = new ArrayList[size+1];
        for(int i=1; i<=size; i++){
            list[i] = new ArrayList<>();
        }
        distance = new int[size+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        int startNode,endNode,NodeDist;
        st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            startNode = Integer.parseInt(st.nextToken());
            endNode = Integer.parseInt(st.nextToken());
            NodeDist = Integer.parseInt(st.nextToken());
            list[startNode].add(new Node(endNode, NodeDist));
        }
        st=new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());
        Dijkstra(startNode,endNode, 0);
        System.out.print(sb);
    }
}