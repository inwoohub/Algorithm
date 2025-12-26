// [1][1] 부터 시작
// dfs 로 탐색하면서 방문 처리 (dfs 함수 만들어서 방문 처리)
// 만약 탐색하다가 다음 노드가 '시작' == '끝' 즉, 사이클 이라면 +1
//    어떻게? 시작점 == 종착지 인 경우

// 만약 탐색하다가 다음 노드가 이미 방문한적 있다면, 그대로 끝
//    어떻게? 시작점 != 종착지 인 경우


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
        // System.out.println(sb);
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
                sb.append("카운트 발생! visited["+nextH+"]["+nextW+"] \n");
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
                sb.append("카운트 발생! visited["+nextH+"]["+nextW+"] \n");
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
                sb.append("카운트 발생! visited["+nextH+"]["+nextW+"] \n");
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
                sb.append("카운트 발생! visited["+nextH+"]["+nextW+"] \n");
                return;
            }else if(visited[nextH][nextW]==2){
                return;
            }
        }
    }
}