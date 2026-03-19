// 알고리즘
// dp 점화식 -> dp[n][m] = dp[n-1][m] + dp[n][m-1]; 

import java.util.*;

class Solution {
    
    static final int MOD = 1000000007;
    static int[][] dp;
    static boolean[][] water;
    
    public int solution(int m, int n, int[][] puddles) {
        // 지도 생성 (dp배열)
        dp = new int[n+1][m+1];
        
        dp[1][1] = 1; // 이거 받아오면됨. (병원 오픈 화환)
        
        // 물 생성
        water = new boolean[n+1][m+1];
        
        for(int[] p : puddles){
            water[ p[1] ][ p[0] ] = true;
        }
        
        // 병원 오픈
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(i==1 && j==1) continue;
                if(water[i][j]){
                    continue;
                }
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
            }
        }
        
        return dp[n][m];
        
    }
    
}