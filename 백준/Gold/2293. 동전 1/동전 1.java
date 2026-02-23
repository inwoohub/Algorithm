// =================
// 알고리즘
// DP
// =================

import java.io.*;
import java.util.*;

public class Main{

    static int N, K;  // N: 동전의 종류 , K: 목표값
    static int[] arr; // 동전 종류 배열
    static int[] dp;
    
    public static void main(String[] args) throws IOException{
        
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 1) 편의를 위한 sort
        Arrays.sort(arr);

        // 2) DP 배열 생성 & dp[0] = 1 세팅
        dp = new int[K+1];
        dp[0] = 1;
        

        // 3) arr[0] ~ arr[N-1] 돌면서 dp 배열 채우기
        for(int i=0; i<N; i++){
            dp_fill(i);
        }

        // 데이터 출력
        System.out.print(dp[K]);
        
    }

    // x: 배열의 현재 인덱스
    static void dp_fill(int x){
        // 4) arr[x] 부터 1개씩 가능
        for(int i=arr[x]; i<=K; i++){
            dp[i] = dp[i] + dp[i - arr[x]];   
        }   
    }
}