// 최소값 구하기
// DP , 그리디 사용?

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N : 집의 개수
        int[][] RGB = new int[N+1][3];
        int[][] dp = new int[N+1][3];
        int answer = 10000000;

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            RGB[i][0] = A;
            RGB[i][1] = B;
            RGB[i][2] = C;
        }

        for(int k=0; k<3; k++){
            for(int i=0; i<3; i++){
                if( i==k ){
                    dp[1][i] = RGB[1][i];
                }else{
                    dp[1][i] = 10000000;    
                }
            }

            for(int i=2; i<=N; i++){
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+RGB[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+RGB[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+RGB[i][2];
            }
            for(int i=0; i<3; i++){
                if( i != k){
                    answer = Math.min(answer,dp[N][i]);
                }
            }
        }
        System.out.print(answer);       
    }
}