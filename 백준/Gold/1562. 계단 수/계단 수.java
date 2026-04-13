/**
알고리즘 :
    비트 마스킹

문제 요약 :
    N이 주어질 때
    1. 길이가 N 이면서
    2. 0부터 9까지 숫자가 모두 등장하는 계단 수가 총 몇개?
    * 0 으로 시작하는 수는 계단수가 아님

전략 :
    dp[i][j][mask] 배열 정의
    [i]    : i 자리 숫자
    [j]    : 끝나는 숫자
    [mask] : 사용한 숫자
    로 비트 마스킹
*/

import java.util.*;
import java.io.*;

public class Main{

    static final long MOD = 1000000000;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][][] dp = new long[N+1][10][1<<10];

        // dp 값 초기화
        for(int i=1; i<10; i++){
            dp[1][i][1<<i] = 1 ;
        }

        for(int mask=0; mask<(1<<10); mask++){
            for(int i=2; i<=N; i++){
                for(int j=0; j<10; j++){
                    int bit = (mask|(1<<j)); // 사용한 숫자 포함해서 비트 마스킹하기
                    
                    if(j==0){
                        dp[i][j][bit] = ( dp[i][j][bit] + dp[i-1][1][mask] ) % MOD ; // 끝나는 숫자가 0이라면, 이전 값은 1이여야함.
                    }
                        
                    else if(j==9){
                        dp[i][j][bit] = ( dp[i][j][bit] + dp[i-1][8][mask] ) % MOD ; // 끝나는 숫자가 9라면, 이전 값은 8이어야함.
                    }
                        
                    else{
                        dp[i][j][bit] = ( dp[i][j][bit] + dp[i-1][j+1][mask] + dp[i-1][j-1][mask] ) % MOD ;
                    }
                }
            }
        }

        long answer = 0;
        for(int i=0; i<10; i++){
            answer = ( answer + dp[N][i][ (1 << 10) - 1 ] ) % MOD;
        }
        System.out.print(answer);
    }
    
}