import java.io.*;
import java.util.*;

public class Main{
    static int MAX = 100000;
    static int N,M;
    static int[][] dist;    
    static int[] ways;
    static boolean[] visited;

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        visited[N] = true;
        while(!q.isEmpty()){
            int A = q.poll();
            int[] arr = {A-1, A+1, A*2};
            for(int next : arr){
                if(next<0 || next>MAX) continue;
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                    dist[0][next] = dist[0][A]+1;
                    ways[next] = ways[A];
                    dist[1][next] = dist[1][A]+1;
                }
                else if( dist[1][next] == dist[1][A]+1){
                    ways[next] = ways[next]+ways[A];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[2][MAX+1];
        visited = new boolean[MAX+1];
        ways = new int[MAX+1];
        ways[N] = 1;
        dist[0][N] = 0;
        dist[1][N] = 0;
        bfs();
        System.out.print(dist[0][M]+"\n"+ways[M]);
    }
}