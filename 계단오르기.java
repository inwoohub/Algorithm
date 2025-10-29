import java.io.*;
import java.util.*;

public class Main{

    //static 배열 dp[] , arr[] 생성
    static Integer dp[];
    static int arr[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new Integer[N+1];
        arr = new int[N+1];

        //arr 배열에 N만큼 입력값 넣기 단, arr[0] = 0 임
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        //dp[0], dp[1], dp[2] 초기화
        dp[0] = arr[0];
        dp[1] = arr[1];
        if(N>=2){ dp[2] = arr[1]+arr[2]; }

        System.out.print(find(N));
        
    }

    //find() 함수 (계단오르기 단, 조건 성립해야함)
    static int find(int n){
        if(dp[n] == null){
            dp[n] = Math.max( find(n-2), find(n-3)+arr[n-1]  ) + arr[n];
        }
        return dp[n];
    }
}