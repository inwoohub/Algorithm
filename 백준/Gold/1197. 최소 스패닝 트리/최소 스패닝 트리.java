// 최소 스패닝 트리 : 모든 정점을 연결하는 부분 그래프에서 가중치 합이 최소
// 우선순위큐 사용 (단 낮은 순으로) - 다익스트라?

import java.io.*;
import java.util.*;

public class Main{

    static class Edge{
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    
    static int V, E;
    static ArrayList<Edge>[] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<=E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Edge(B,C));
            graph[B].add(new Edge(A,C));

        }

        System.out.println(dijkstra());
    }

    static long dijkstra(){
        int cnt = 0;
        int result = 0;
        boolean[] visited = new boolean[V+1];
        PriorityQueue<Edge> q = new PriorityQueue<>((a,b)-> a.weight-b.weight);
        q.offer(new Edge(1,0));

        while(!q.isEmpty()){
            Edge cur = q.poll();
            int curNode = cur.to;
            int curWeight = cur.weight;
            if(visited[curNode]) continue;
            visited[curNode] = true;
            result = result + curWeight;
            cnt++;
            if(cnt==V) break; // 모든 정점 방문시 종료
            for(Edge next : graph[curNode]){
                if(!visited[next.to]){
                    q.offer(next);
                }
            }
        }
        return result;
    }
    
}