// ===========
// 알고리즘
// DP (dynamic programming)
// ===========
// 10
// 10 -4 3 1 5 6 -35 12 21 -1
// ===========
// 33
// ===========

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // 입력 배열 
        int[] dp = new int[N];  // 동적 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean check = false;
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }
        
        // 1) 순차 탐색
        int ans = arr[0];
        for(int i=1; i<N; i++){ // 1번째 부터 시작
            dp[i] =  Math.max(dp[i] , dp[i-1] + arr[i]); // 이전 합 + 현재값 vs 현재값
            ans = Math.max(dp[i], ans); // 최대값 갱신
        }

        // 데이터 출력
        System.out.print(ans);        

    }
}