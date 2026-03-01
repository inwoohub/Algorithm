// 알고리즘
// dfs (모든 경우의 수)

import java.io.*;
import java.util.*;

public class Main{

    static int N, K;
    static int[] arr, dp;
    static int ans = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 동전의 가치
        K = Integer.parseInt(st.nextToken()); // 동전의 개수
        arr = new int[N];  // 동전의 가치 배열
        dp = new int[K+1];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        
        // dfs 탐색
        int pre = -1;
        for(int i=0; i<N; i++){
            if(pre == arr[i]) continue;
            dfs(arr[i], K, 1);
            pre = arr[i];
        }

        // 데이터 출력
        if(dp[K] ==Integer.MAX_VALUE){
            System.out.print("-1");
        } else{
            System.out.print(dp[K]);    
        }

        // 디버깅
        // for(int i=1; i<=K; i++){
        //     System.out.println("dp["+i+"] : "+ dp[i]);
        // }
        
    }

    // dfs
    // sum: 합, target: 찾아야하는 수, count: 횟수
    static void dfs(int sum, int target, int count){
        
        if(sum>target) return;
        if(sum == target ){
            dp[sum] = Math.min(dp[sum],count);
            return;
        }
        
        if(dp[sum] <= count ){
            return;
        }

        dp[sum] = count;
        
        int pre = -1;
        for(int i=0; i<N; i++){
            if(pre == arr[i]) continue;
            dfs(sum+arr[i], target, (count+1));
            pre = arr[i];
        }
        
    }
}