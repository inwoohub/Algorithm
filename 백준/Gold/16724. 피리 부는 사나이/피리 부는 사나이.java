// visited : 0 (미방문), 1 (진행중) , 2(방문완료))
// 1. [1][1] 부터 시작 (2중 for문)
// 2. 미방문시 dfs 실행
//     2-1. 방문하면서 visited = 1 (진행중) 처리
//     2-2. 만약 다음 노드 상태가 visited = 1 (진행중) 이라면, 싸이클 발생
//     2-3. dfs 빠져 나오면서 visited = 2 (방문완료) 처리


import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int height, width, ans;
    static Character[][] graph;
    static byte[][] visited; // byte : 0 (미방문), 1 (진행중) , 2(방문완료))

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        ans = 0;
        graph = new Character[height+1][width+1];
        visited = new byte[height+1][width+1];
        
        for(int h=1; h<=height; h++){
            String input = br.readLine();
            for(int w=1; w<=width; w++){
                graph[h][w] = input.charAt(w-1);
            }
        }

        for(int h=1; h<=height; h++){
            for(int w=1; w<=width; w++){
                if(visited[h][w]==0){
                    visited[h][w] = 1;
                    dfs(h, w);
                    visited[h][w] = 2;
                }
            }
        }
        System.out.print(ans);
    }

    static void dfs(int h, int w){
        if(graph[h][w] == 'U'){
            int nextH = h-1;
            int nextW = w;
            if(visited[nextH][nextW]==0){
                visited[h][w] = 1;
                dfs(nextH, nextW);
                visited[h][w] = 2;
            }else if(visited[nextH][nextW]==1){
                ans++;
                return;
            }else if(visited[nextH][nextW]==2){
                return;
            }
        }

        if(graph[h][w] == 'D'){
            int nextH = h+1;
            int nextW = w;
            if(visited[nextH][nextW]==0){
                visited[h][w] = 1;
                dfs(nextH, nextW);
                visited[h][w] = 2;
            }else if(visited[nextH][nextW]==1){
                ans++;
                return;
            }else if(visited[nextH][nextW]==2){
                return;
            }
        }

        if(graph[h][w] == 'L'){
            int nextH = h;
            int nextW = w-1;
            if(visited[nextH][nextW]==0){
                visited[h][w] = 1;
                dfs(nextH, nextW);
                visited[h][w] = 2;
            }else if(visited[nextH][nextW]==1){
                ans++;
                return;
            }else if(visited[nextH][nextW]==2){
                return;
            }
        }

        if(graph[h][w] == 'R'){
            int nextH = h;
            int nextW = w+1;
            if(visited[nextH][nextW]==0){
                visited[h][w] = 1;
                dfs(nextH, nextW);
                visited[h][w] = 2;
            }else if(visited[nextH][nextW]==1){
                ans++;
                return;
            }else if(visited[nextH][nextW]==2){
                return;
            }
        }
    }
}