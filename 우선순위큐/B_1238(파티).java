import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int V,E,partyNode, minDist;
    static ArrayList<int[]>[] list;

    static void Dijkstra(int start, int[] Dist){
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        q.offer(new int[]{start,0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(Dist[curNode] < curDist) continue;
            for(int[] next : list[curNode]){
                if(Dist[next[0]] > curDist+next[1]){
                    q.offer(new int[]{next[0], curDist+next[1]});
                    Dist[next[0]] = curDist+next[1];    
                }
            }
        }
    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        partyNode = Integer.parseInt(st.nextToken());

        list = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[A].add(new int[]{B,C});
        }
        
        int result = 0;
        for(int i=1; i<=V; i++){
            int[] DistA = new int[V+1];
            Arrays.fill(DistA,Integer.MAX_VALUE);
            DistA[i] = 0;
            Dijkstra(i,DistA);
            
            int[] DistB = new int[V+1];
            Arrays.fill(DistB,Integer.MAX_VALUE);
            DistB[partyNode] = 0;
            Dijkstra(partyNode,DistB);
            result = Math.max(result , DistA[partyNode]+DistB[i]);
        }
        sb.append(result);
        System.out.print(sb);
    }
}