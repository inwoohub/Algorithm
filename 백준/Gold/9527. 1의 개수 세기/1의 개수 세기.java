// -------------------------------
// 알고리즘
// 비트마스킹 + DP
// 1~M 까지 2진수 변환시 나타나는 1의 총 개수 -  1~N 까지 2진수 변환시 나타나는 1의 총 개수
// 1. 자연수 최대값인 10^16 보다 큰 2^54 까지 1의 등장 수 구하기 DP 사용 (누적합)
//    → 점화식 사용 DP[i] = (DP[i-1]<<1) + (1L<<i);
// 2. N , M 까지 1 등장 누적합 구하기 
//    → count = count + DP[i-1] + (n - (1L<<i) +1);
// -------------------------------
// 변수
// DP[] : 누적합
// N , M (구간 N~M)
// -------------------------------

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static long[] DP;
    static long N, M;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        DP = new long[55];
        DP[0] = 1;
        setDP();

        long ans = calOne(M) - calOne(N-1);
        System.out.print(ans);
        
    }

    // x의 누적합 구하기
    static long calOne(long x){
        long n = x;
        long count = x & 1L;
        int size = (int) (Math.log(x) / Math.log(2)); // x보다 작은 2^n 의 최대 n 값
        for(int i=size; i>0; i--){
            if( (n & (1L<<i)) != 0L ){
                count = count + DP[i-1] + (n - (1L<<i) +1);
                n = n - (1L<<i);
            }    
        }
        return count;
    }

    // 누적합 구하기
    static void setDP(){
        for(int i=1; i<55; i++){
            DP[i] = (DP[i-1]<<1) + (1L<<i);
        }
    }   
}