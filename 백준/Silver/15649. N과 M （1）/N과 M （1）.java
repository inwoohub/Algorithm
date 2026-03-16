// 알고리즘
// dfs + backtracking

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[N+1];

        for(int i=1; i<=N; i++){
            visited[i] = true;
            dfs(1, String.valueOf(i) , visited);
            visited[i] = false;
        }
        System.out.print(sb);
    }

    // dfs
    static void dfs(int curSize, String str, boolean[] visit){
        // 1. 도착한 경우 (종료 조건)
        if(curSize == M){
            sb.append(str+"\n");
            return;
        }

        // 2. 방문 안한 수 탐색
        for(int i=1; i<=N; i++){
            if(!visit[i]){
                visit[i] = true;
                String x = String.valueOf(i);
                dfs(curSize+1, (str+" "+x), visit);
                visit[i] = false; // 백트래킹 
            }
        }
    }
}