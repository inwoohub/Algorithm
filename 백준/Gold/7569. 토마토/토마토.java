import java.io.*;
import java.util.*;

public class Main{
    static int[][] graph;
    static boolean[][] visited;
    static int count = 0;

    static void find(int size,int M,int N){
        int[] dx = {1,-1,0,0,N,-N};
        int[] dy = {0,0,1,-1,0,0};
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<size; i++){
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
                for(int i=0; i<6; i++){
                    int cx = A+dx[i];
                    int cy = B+dy[i];
                    if(cx<0 || cx>size-1 || cy<0 || cy>M-1) continue;
                    if((i==0||i==1) && cx/N != A/N) continue;
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
        int H = Integer.parseInt(st.nextToken());
        int size = N*H;
        graph = new int[size][M];
        visited = new boolean[size][M];
        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<M; k++){
                graph[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        find(size,M,N);
        boolean stoped = false;
        
        for(int i=0; i<size; i++){
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