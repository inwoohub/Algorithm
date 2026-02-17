// 알고리즘
// DP

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1) dp 모든 칸 1로 채움
        Arrays.fill(dp,1);

        // 2) 2중 for문으로 탐색
        for(int i=N-2; i>=0; i--){
            for(int j=N-1; j>i; j--){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        // 3) 최대값 탐색
        int ans = 0;
        for(int i=0; i<N; i++){
            ans = Math.max(ans, dp[i]);
        }

        // 데이터 출력
        System.out.println(ans);
        
        
    } // End of main
}