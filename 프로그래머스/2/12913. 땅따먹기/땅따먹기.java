// 알고리즘

import java.util.*;

class Solution {
    
    static int[][] dp;
    
    int solution(int[][] land) {
        
        dp = new int[land.length][4];
        
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];
        
        // bottom - top
        for(int i=1; i<land.length; i++){
            dp[i][0] = Math.max(  dp[i-1][1]  ,Math.max(dp[i-1][2], dp[i-1][3]) ) + land[i][0];
            dp[i][1] = Math.max(  dp[i-1][0]  ,Math.max(dp[i-1][2], dp[i-1][3]) ) + land[i][1];
            dp[i][2] = Math.max(  dp[i-1][0]  ,Math.max(dp[i-1][1], dp[i-1][3]) ) + land[i][2];
            dp[i][3] = Math.max(  dp[i-1][0]  ,Math.max(dp[i-1][1], dp[i-1][2]) ) + land[i][3];
        }
        
        int MAX = 0;
        
        for(int i=0; i<4; i++){
            MAX = Math.max(dp[land.length-1][i], MAX);
        }
        
        return MAX;

    }

    
}