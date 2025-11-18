import java.io.*;
import java.util.*;

public class Main{
    // N 노드 개수, M 수색 범위, R 길의 개수
    static int N, M, R;
    static ArrayList<Integer>[] list;
    static int[][] dist;
    static int[] item;
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        dist = new int[N+1][N+1];
        item = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            item[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[A].add(B);
            list[B].add(A);
            dist[A][B] = C;
            dist[B][A] = C;
        }
        
        int result = 0;

        // dijkstra로 최단거리 탐색
        for(int i=1; i<=N; i++){
            int sum = 0;
            int[] bestDist = dijkstra(i);
            for(int j=1; j<=N; j++){
                if(bestDist[j] <= M ){
                    sum = sum+item[j];
                }
            }
            result = Math.max(result, sum);
        }
        
        System.out.print(result);
        
    }

    // 최단경로 탐색
    // bestDist로 더 짧은 거리 저장,
    // 이때 PriorityQueue 사용하여 거리가 더 짧은거 먼저 실행
    static int[] dijkstra(int i){
        int[] bestDist = new int[N+1];
        Arrays.fill(bestDist,Integer.MAX_VALUE);
        bestDist[i] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        q.offer(new int[]{i,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(bestDist[curNode] < curDist) continue; //늦게 들어온게 더 멀면 패스
            for(int nextNode : list[curNode]){
                int nextDist = curDist + dist[curNode][nextNode];
                if( nextDist <= bestDist[curNode] ){
                    bestDist[nextNode] = nextDist;
                    q.offer(new int[]{nextNode, nextDist});
                }
            }
        }
        return bestDist;
    }
}