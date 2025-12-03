// MST (최소 스패닝 트리)
import java.io.*;
import java.util.*;

public class Main{
    static ArrayList<int[]>[] list;
    static boolean[] visited;
    static int N, M; // N개 집, M개 길
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list[A].add(new int[]{B,C});
            list[B].add(new int[]{A,C});

        }
        long result = prim();
        System.out.print(result);
    }

    static long prim(){
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> a[1]-b[1] );
        visited[1] = true;
        for(int[] next : list[1]){
            pq.offer(new int[]{next[0],next[1]});
        }
        long sum = 0;
        long maxEdge = 0;
        int count = 1;

        while(!pq.isEmpty() && count < N){
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];
            if(visited[curNode]) continue;

            visited[curNode] = true;
            count++;
            sum += curCost;
            if(curCost > maxEdge) maxEdge = curCost;

            for(int[] next : list[curNode]){
                int nextNode = next[0];
                int nextCost = next[1];
                if(!visited[nextNode]){
                    pq.offer(new int[]{nextNode, nextCost});
                }
            }
        }
        return sum - maxEdge;
    }
}