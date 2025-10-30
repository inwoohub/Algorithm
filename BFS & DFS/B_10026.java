import java.io.*;
import java.util.*;

public class Main{
    static char[][] graph;
    static char[][] GRgraph;
    static boolean[][] visited;
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};

    static void find(int i, int k,int N,char[][] graph){
        Queue<Integer[]> q = new LinkedList<>();
        q.offer(new Integer[]{i,k});
        visited[i][k] = true;
        while(!q.isEmpty()){
            Integer[] arr = new Integer[2];
            arr = q.poll();
            int A = arr[0];
            int B = arr[1];
            for(int j=0; j<4; j++){
                int cx = A+dx[j];
                int cy = B+dy[j];
                if(cx<0 || cx>=N || cy<0 || cy>=N) continue;
                if(!visited[cx][cy] && graph[i][k]==graph[cx][cy]){
                    visited[cx][cy]=true;
                    q.offer(new Integer[]{cx,cy});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        graph = new char[N][N];
        GRgraph = new char[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for(int k=0; k<N; k++){
                graph[i][k] = input.charAt(k);
                if(graph[i][k]=='G' || graph[i][k]=='R'){
                    GRgraph[i][k]='R';
                }
                else{
                    GRgraph[i][k]='B';
                }
            }
        }

        int GRcount=0;
        int NotGRcount=0;
        visited = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                if(!visited[i][k]){
                    find(i,k,N,GRgraph);
                    GRcount++;
                }
            }
        }
        
        visited= new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                if(!visited[i][k]){
                    find(i,k,N,graph);
                    NotGRcount++;
                }
            }
        }
        System.out.print(NotGRcount+" "+GRcount);
        
    }
}