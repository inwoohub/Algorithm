import java.io.*;
import java.util.*;

public class Main{
    static int N, M;
    static int[][] graph;
    static int[][][] dist;
    static boolean[][][] visited;
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};

    static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0,0});
        visited[0][0][0] = true;
        while(!q.isEmpty()){
            int[] arr = new int[3];
            arr = q.poll();
            int A = arr[0];
            int B = arr[1];
            int C = arr[2];
            for(int i=0; i<4; i++){
                int CA = A+dx[i];
                int CB = B+dy[i];
                if(CA<0 || CA>=N || CB<0 || CB>=M) continue;
                //벽이 아니라면
                if(graph[CA][CB]==0){
                    //진행
                    if(!visited[C][CA][CB] ){
                        q.offer(new int[]{CA,CB,C});
                        visited[C][CA][CB]=true;
                        dist[C][CA][CB]=dist[C][A][B]+1;
                    }
                }   
                //벽이라면
                else{
                    //이전에 부쉈는지 체크,부쉈다면 더 안부심
                    if(!visited[1][CA][CB] && C==0){
                        q.offer(new int[]{CA,CB,1});
                        visited[1][CA][CB]=true;
                        dist[1][CA][CB]=dist[0][A][B]+1;
                    }
                }
            }
        }
    }
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        dist = new int[2][N][M];
        visited = new boolean[2][N][M];
        dist[0][0][0] = 1;
        dist[1][0][0] = 1;
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int k=0; k<M; k++){
                graph[i][k] = input.charAt(k)-'0' ;
            }
        }
        bfs();
        if(dist[0][N-1][M-1]==0 && dist[1][N-1][M-1]==0){
            System.out.print("-1");
        }
        else{
            if(dist[0][N-1][M-1]==0){
                System.out.print(dist[1][N-1][M-1]);
            }
            else if(dist[1][N-1][M-1]==0){
                System.out.print(dist[0][N-1][M-1]);
            }
            else{
                System.out.print(Math.min(dist[0][N-1][M-1],dist[1][N-1][M-1]));        
            }
            
        }
    }
}