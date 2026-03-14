// 알고리즘
// DP

import java.io.*;
import java.util.*;

public class Main{

    static final long DIV = 1000000000L;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // N : 길이기 N 인 계단 수 구하기
        long[][] dp = new long[N+1][10]; // [N+1]: 현재 길이 , [10]: 0~9 -> 현재 숫자
        // ex) [2][3]= 길이는 2, 그 숫자는 3 -> 3x , [3][8] = 길이는 3, 그 숫자는 8 -> 8xx

        // 길이가 1이면 1개밖에 없어서 dp[1][1~9] 까지는 1로 다 초기화, 다만 dp[1][0] 은 해당 문제가에서 0으로 시작할 수 없기 때문에 제외
        Arrays.fill(dp[1], 1L);
        dp[1][0] = 1L;

        // bottom-top 방식 사용 [2][] ~ [N][] 까지 순차적으로 올라감
        for(int i=2; i<=N; i++){
            // [][0] ~ [][9] 까지 탐색
            for(int j=0; j<10; j++){
                if(j==0) dp[i][j] = dp[i-1][1]; // 0의 다음은 무조건 1이 와야됨

                else if (j==9) dp[i][j] = dp[i-1][8]; // 9의 다음은 무조건 8이 와야됨

                else{
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]; // 2~8의 다음은 ex) 2인 경우 다음에 1, 3 즉, 2개가 가능함 따라서 2개의 경우의 수를 더해줌
                }

                dp[i][j] = dp[i][j] % DIV;
            }
        }

        // 결과: dp[N][1] ~ dp[N][9] 까지 더해주기
        long sum = 0L;
        for(int i=1; i<10; i++){
            sum = (sum + dp[N][i]) % DIV;
        }

        System.out.print(sum);
    }
}