import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int MAX = 200000000;
    static int N,E,v1,v2;
    static ArrayList<int[]>[] list;
    static int[] dist;

    static int Dijkstra(int start, int end){
        dist = new int[N+1];
        Arrays.fill(dist,MAX);
        dist[start]=0;
        PriorityQueue<int[]> q = new PriorityQueue<>( (a,b) -> a[1]-b[1] );
        q.offer(new int[]{start,0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(curDist > dist[curNode]) continue;
            for(int i=0; i<list[curNode].size(); i++){
                int[] arr = list[curNode].get(i);
                int nextNode = arr[0];
                int nextDist = arr[1];
                if(dist[nextNode] > dist[curNode]+nextDist){
                    dist[nextNode] = dist[curNode]+nextDist;
                    q.offer(new int[]{nextNode,dist[nextNode]});
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[A].add(new int[]{B,C});
            list[B].add(new int[]{A,C});
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        int resultA = Dijkstra(1,v1)+Dijkstra(v1,v2)+Dijkstra(v2,N);
        int resultB = Dijkstra(1,v2)+Dijkstra(v2,v1)+Dijkstra(v1,N);
        int result = Math.min(resultA, resultB);
        if(result>=MAX){
            sb.append("-1");
        }
        else{
            sb.append(result);
        }
        System.out.print(sb);
    }
}