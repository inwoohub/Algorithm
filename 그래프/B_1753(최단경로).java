import java.io.*;
import java.util.*;

public class Main{

    static class Node{
        int end;
        int weight;

        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static int N,M,start;
    static boolean[] visited;
    static int[] dist;
    static ArrayList<Node>[] graph;

    static void Dijkstra(){
        PriorityQueue<Node> q = new PriorityQueue<>((a,b)->a.weight-b.weight);
        q.offer(new Node(start,0));
        while(!q.isEmpty()){
            Node cur = q.poll();
            visited[cur.end] = true;
            for(int i=0; i<graph[cur.end].size(); i++){
                Node next = graph[cur.end].get(i);
                if(!visited[next.end] && dist[cur.end]+next.weight < dist[next.end]){
                    dist[next.end] = dist[cur.end]+next.weight;
                    q.offer(new Node(next.end, dist[next.end]));
                }
            }
        }
        for(int i=1; i<=N; i++){
            if(dist[i] == Integer.MAX_VALUE){
                sb.append("INF\n");
                continue;
            }
            sb.append(dist[i]+"\n");
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        dist = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v,w));
        }
        Dijkstra();
        System.out.print(sb);
    }
}