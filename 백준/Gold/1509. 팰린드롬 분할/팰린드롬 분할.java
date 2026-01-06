// -------------------
// 알고리즘 - 팰린드롬 (거울처럼 같은 수)
// 1. 이중 for 문을 통해 모든 팰린드롬 찾기 (boolean 으로 [1][5] true 라면, 1~5는 팰린드롬임)
// 2. 이중 for 무을 통해 dp 계산
//    -> 이중 for문을 사용한 이유는, 이전 팰린드롬을 [][] 배열로 생성했기 때문
// -------------------
// 변수
// dp[] , arr[], pal[][], size
// -------------------

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static boolean[][] pal;
    static int[] dp;
    static char[] arr;
    static int size;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        size = input.length();
        pal = new boolean[size+1][size+1];
        dp = new int[size+1];
        arr = new char[size+1];

        for(int i=1; i<=size; i++){
            arr[i] = input.charAt(i-1);
        }
        Arrays.fill(dp,3000);
        dp[0] = 0;
        dp[1] = 1;

        palindrom();

        for(int i=1; i<=size; i++){
            for(int j=1; j<=i; j++){
                if(pal[j][i]){
                    dp[i] = Math.min(dp[i] , dp[j-1]+1);    
                }
            }
        }
        System.out.print(dp[size]);
    }

    static void palindrom(){
        for(int i=1; i<=size; i++){
            for(int j=1; j<=i; j++){
                boolean check = true;
                
                if(arr[i] != arr[j]){
                    continue;
                }
                int start = j;
                int end = i;
                while(start <= end){
                    if(arr[start] != arr[end]){
                        check = false;
                        break;
                    }
                    start++;
                    end--;
                }
                if(check){
                    pal[j][i] = true;
                }
            }
        }
    }
}