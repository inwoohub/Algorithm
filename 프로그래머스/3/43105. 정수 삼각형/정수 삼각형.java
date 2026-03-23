// 알고리즘
// DP

class Solution {
    
    static int[][] dp;
    
    public int solution(int[][] triangle) {
        
        // dp 배열 생성
        int size = triangle.length;
        dp = new int[size+1][size+1];
        
        dp[0][0] = triangle[0][0];
        
        for(int i=1; i<size; i++){
            for(int j=0; j<=i; j++){
                if(j==0){
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                }
                else if(j==i){
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }
                else{
                    dp[i][j] = (Math.max( dp[i-1][j-1] , dp[i-1][j] ) ) + triangle[i][j];    
                }
                
            }
        }
        
        int answer = 0;
        for(int i=0; i<size; i++){
            answer = Math.max(dp[size-1][i], answer);
        }
        return answer;
    }
    
    
}