import java.io.*;
import java.util.*;

public class Main{
    static char[][] graph;
    static boolean[][] Gvisited;
    static boolean[] Cvisited;
    static int R, C;
    static int max;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    static void dfs(int x, int y, int count){
        for(int i=0; i<4; i++){
            int cx = x+dx[i];
            int cy = y+dy[i];
            if(cx<1 || cx>R || cy<1 || cy>C){
                continue;
            }
            if(!Gvisited[cx][cy]){
                if(!Cvisited[((int)graph[cx][cy])]){
                    Cvisited[((int)graph[cx][cy])] = true;
                    Gvisited[cx][cy] = true;
                    if(max < count+1){
                        max = count+1;
                    }
                    dfs(cx,cy,count+1);
                    Cvisited[((int)graph[cx][cy])] = false;
                    Gvisited[cx][cy] = false;      
                }
                
            }
        }
    } 

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R+1][C+1];
        Gvisited = new boolean[R+1][C+1];
        Cvisited = new boolean[100];
        for(int i=1; i<=R; i++){
            String input = br.readLine();
            for(int k=1; k<=C; k++){
                graph[i][k] = input.charAt(k-1);
            }
        }
        max = 1;

        Cvisited[((int)graph[1][1])] = true;
        dfs(1,1,1);
        System.out.print(max);
        
    }
}