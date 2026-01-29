// ===============
// 알고리즘
// dfs
// ===============
// 5
// 5 1 3 2 4
// 1 2
// 2 3
// 3 4
// 3 5
// ===============
// 5
// 1
// 5
// 5
// 4
// ===============

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int size;
    static int[] power;
    static boolean[] visited;
    static int[][] dp;
    static int[] result;
    
    public static void main(String[] args) throws IOException{
        // 1) 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        power = new int[size+1];
        dp = new int[size+1][2];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=size; i++){
            power[i] = Integer.parseInt(st.nextToken());
        }
        result = new int[size+1];
        for(int i=1; i<size; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            // 이웃 2개 1,2등 기록해두기
            if( power[B] > power[dp[A][0]] ){
                int C = dp[A][0];
                dp[A][0] = B;
                dp[A][1] = C;
            }
            else if( power[B] > power[dp[A][1]] ){
                dp[A][1] = B;
            }

            if( power[A] > power[dp[B][0]] ){
                int C = dp[B][0];
                dp[B][0] = A;
                dp[B][1] = C;
            }
            else if( power[A] > power[dp[B][1]] ){
                dp[B][1] = A;
            }
        }

        visited = new boolean[size+1];
        // 2) 탐색 시작
        for(int i=1; i<=size; i++){
            visited[i] = true;
            int ans = walk(i);
            sb.append(ans+"\n");
        }
        System.out.print(sb);
        
    }

    static int walk(int start){
        int parent = 0;
        int cur = start;
    
        while (true) {
            int a = dp[cur][0];
            int b = dp[cur][1];
    
            int next = 0;
            if (a != 0 && a != parent) next = a;
            else if (b != 0 && b != parent) next = b;
            else return cur; // 더 갈 곳 없음
    
            parent = cur;
            cur = next;
        }
    }
    
}