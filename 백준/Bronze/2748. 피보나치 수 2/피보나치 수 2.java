// ===========
// 알고리즘
// 피보나치 수
// ===========
// 10
// ===========
// 55
// ===========

import java.io.*;
import java.util.*;

public class Main{

    static long[] dp;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new long[N+1];
        dp[0] = 0; dp[1] = 1; // 초기값 세팅

        System.out.print(fibonacci(N));
        
    }

    static long fibonacci(int n){
        
        if(n == 0) return 0;
        
        if(n == 1) return 1;

        if(dp[n]!=0) return dp[n];

        return dp[n] = fibonacci(n-1) + fibonacci(n-2);
        
    }
}