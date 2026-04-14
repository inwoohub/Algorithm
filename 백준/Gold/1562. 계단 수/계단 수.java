import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final long MOD = 1000000000;
        int N = Integer.parseInt(br.readLine());

        /**
        [N+1]  : 자리수 x, xx, xxx, xxxx
        [10]   : 마지막에 올 수 있는 수 0~9 가능
        [1<<10]: 사용한 수 비트로 표기 0~9 총 10 개 가능
        */
        long[][][] dp = new long[N+1][10][1<<10];

        // dp 초기 값 표기
        for(int i=1; i<10; i++){ // 1자리수 0 부터는 불가능.
            dp[1][i][1<<i] = 1; // 1자리 수, 끝에 올수 있는 수는 0~9, [1<<i] 로 사용처리
        }

        for(int mask=0; mask<(1<<10); mask++){ // 사용 안하기 ~ 전부 사용하기 mask 전부 접근
            for(int i=2; i<=N; i++){           // 1자리 수는 초기값 주었음으로 2자리 수 부터 시ㅏㅈㄱ
                for(int j=0; j<10; j++){       // 끝에 오는 수
                    int nextMask = (mask | (1<<j)); // 현재 마스크 + 끝에 오는 수해서 모두 사용 처리
                    if(j==0){      // 마지막에 0 이 왔다면, 계단수 이므로 이전에는 1밖에 올 수 없음 따라서 이전 마지막 수는 1
                        dp[i][j][nextMask] = (dp[i][j][nextMask] + dp[i-1][1][mask]) % MOD;    
                    }
                        
                    else if(j==9){ // 마지막에 9 이 왔다면, 계단수 이므로 이전에는 8밖에 올 수 없음 따라서 이전 마지막 수는 8
                        dp[i][j][nextMask] = (dp[i][j][nextMask] + dp[i-1][8][mask]) % MOD;
                    }

                    else{ // 그 외는 이전 +1 , -1 둘 다 가능함
                        dp[i][j][nextMask] = (dp[i][j][nextMask] + dp[i-1][j-1][mask] + dp[i-1][j+1][mask]) % MOD;
                    }
                }
            }
        }

        long answer = 0;
        for(int j=0; j<10; j++){
            answer = (answer + dp[N][j][(1<<10)-1]) % MOD;
        }
        System.out.print(answer);
    }
}