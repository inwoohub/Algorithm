import java.io.*;
import java.util.*;

public class Main{

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] graph;
    static boolean[][] visited;
    static int max;
    
    static void dfs(int i, int j,int N, int M, int depth, int sum){
        if(depth == 4){
            max = Math.max(max,sum);
            return;
        }
        for(int k=0; k<4; k++){
            int cx = i+dx[k];
            int cy = j+dy[k];
            //경계값 검사
            if(cx<0 || cy<0 || cx>=N || cy>=M) continue;
            if(!visited[cx][cy]){
                visited[cx][cy] = true;
                dfs(cx,cy,N,M,depth+1,sum+graph[cx][cy]);
                visited[cx][cy]=false;
            }
        }
    }

    static void find1(int i, int j, int N, int M,int sum){
        int count = 0;
        int[] fx1 = {0,0,0,-1};
        int[] fy1 = {0,-1,1,0};
        for(int k=0; k<4; k++){
            int cx = i+fx1[k];
            int cy = j+fy1[k];
            if(cx<0 || cy<0 || cx>=N || cy>=M) return;
            sum = sum+graph[cx][cy];
            count++;
        }
        if(count==4){
            max = Math.max(max,sum);
        }
    }
    static void find2(int i, int j, int N, int M,int sum){
        int count = 0;
        int[] fx1 = {0,0,0,1};
        int[] fy1 = {0,-1,1,0};
        for(int k=0; k<4; k++){
            int cx = i+fx1[k];
            int cy = j+fy1[k];
            if(cx<0 || cy<0 || cx>=N || cy>=M) return;
            sum = sum+graph[cx][cy];
            count++;
        }
        if(count==4){
            max = Math.max(max,sum);
        }
    }
    static void find3(int i, int j, int N, int M,int sum){
        int count = 0;
        int[] fx1 = {0,0,-1,1};
        int[] fy1 = {0,1,0,0};
        for(int k=0; k<4; k++){
            int cx = i+fx1[k];
            int cy = j+fy1[k];
            if(cx<0 || cy<0 || cx>=N || cy>=M) return;
            sum = sum+graph[cx][cy];
            count++;
        }
        if(count==4){
            max = Math.max(max,sum);
        }
    }
    static void find4(int i, int j, int N, int M,int sum){
        int count = 0;
        int[] fx1 = {0,0,-1,1};
        int[] fy1 = {0,-1,0,0};
        for(int k=0; k<4; k++){
            int cx = i+fx1[k];
            int cy = j+fy1[k];
            if(cx<0 || cy<0 || cx>=N || cy>=M) return;
            sum = sum+graph[cx][cy];
            count++;
        }
        if(count==4){
            max = Math.max(max,sum);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        max = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                graph[i][j] =  Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<N; i++){
            int depth = 0;
            int sum = 0;
            visited = new boolean[N][M];
            for(int j=0; j<M; j++){
                dfs(i,j,N,M,depth,sum);
            }
        }

        for(int i=0; i<N; i++){
            int sum = 0;
            for(int j=0; j<M; j++){
                find1(i,j,N,M,sum);
                find2(i,j,N,M,sum);
                find3(i,j,N,M,sum);
                find4(i,j,N,M,sum);
            }
        }
        System.out.print(max);
    }
}