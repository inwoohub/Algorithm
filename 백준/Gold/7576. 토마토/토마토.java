import java.io.*;
import java.util.*;

public class Main{
    static int[][] graph;
    static boolean[][] visited;
    static int count = 0;

    static void find(int M,int N){
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<N; i++){
            for(int k=0; k<M; k++){
                if(graph[i][k]==1){
                    q.offer(new int[]{i,k});
                    visited[i][k] = true;
                }
            }
        }
        while(!q.isEmpty()){
            int qsize = q.size();
            for(int c=0; c<qsize; c++){
                int[] arr = new int[2];
                arr = q.poll();
                int A = arr[0];
                int B = arr[1];
                for(int i=0; i<4; i++){
                    int cx = A+dx[i];
                    int cy = B+dy[i];
                    if(cx<0 || cx>N-1 || cy<0 || cy>M-1) continue;
                    if(!visited[cx][cy] && graph[cx][cy]==0){
                        graph[cx][cy] = 1;
                        visited[cx][cy] = true;
                        q.offer(new int[]{cx,cy});
                    }
                }    
            }
            count++;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<M; k++){
                graph[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        find(M,N);
        boolean stoped = false;
        
        for(int i=0; i<N; i++){
            if(stoped){
                break;
            }
            for(int k=0; k<M; k++){
                if(graph[i][k]==0){
                    stoped = true;
                    System.out.print("-1");
                    break;
                }
            }
        }
        
        if(!stoped){
            System.out.print(count-1);   
        }
    }
}