import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 배열 사이즈
        int[] arr= new int[N];
        int[] dp = new int[N]; // dp 배열
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }        
        
        // 1) 2중 for문으로 탐색
        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j]){ // 2) i보다 작은 경우
                    dp[i] = Math.max(dp[i], dp[j]+arr[i]); // 3) 최대 값 비교
                }
            }
        }

        // 4) 가장 큰 부분 수열의 합 탐색
        int ans = 0;
        for(int i=0; i<N; i++){
            ans = Math.max(ans, dp[i]);
        }

        // 데이터 출력
        System.out.print(ans);
        
    } // End of main
}