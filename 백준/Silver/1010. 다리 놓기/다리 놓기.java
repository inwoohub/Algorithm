// ===============
// 알고리즘
// 다리 경우의 수 -> dp 로 계산
// ===============
// 3
// 2 2
// 1 5
// 13 29
// ===============
// 1
// 5
// 67863915
// ===============

import java.io.*;
import java.util.*;



public class Main{

    static int[][] dp;

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        // 테스트케이스 만큼 반복
        for(int tC=0; tC<testCase; tC++){

            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken()); // 서쪽 사이트 개수
            int E = Integer.parseInt(st.nextToken()); // 동쪽 사이트 개수

            dp = new int[31][31];

            // 데이터 출력
            System.out.println(factorial(E,W));
            
        } // End of for
    }

    static int factorial(int n, int r){

        if(r>n) return 0;

        if(dp[n][r] > 0){
            return dp[n][r];
        }

        if(n==r || r==0){
            return dp[n][r] = 1;
        }

        return dp[n][r] = factorial(n-1, r-1) + factorial(n-1, r);
        
    }
}


// 아래 방식 -> 시간 초과

// public class Main{

//     static int ans;
    

//     public static void main(String[] args) throws IOException{
//         // 데이터 입력
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         int testCase = Integer.parseInt(st.nextToken());

//         // 테스트케이스 만큼 반복
//         for(int tC=0; tC<testCase; tC++){

//             st = new StringTokenizer(br.readLine());
//             int W = Integer.parseInt(st.nextToken()); // 서쪽 사이트 개수
//             int E = Integer.parseInt(st.nextToken()); // 동쪽 사이트 개수
            
//             // 조건1. W <= E 
//             // 조건2. 다리끼리 서로 겹치면 안됨

//             // W 는 결국 개수로 들어감
//             // E 는 될수있는 범위임

//             ans = 0;
//             int count = 0;
//             for(int i=0; i<E; i++){
//                 dfs(i, W, 1, E);
//             }

//             // 데이터 출력
//             System.out.println(ans);
            
//         } // End of for
        
//     }

//     static void dfs(int x, int target, int count, int E){
//         if(target == count){
//             ans++;
//             return;
//         }
//         for(int i=x+1; i<E; i++){
//             dfs(i, target, count+1, E);    
//         }
//     } // End of dfs
// }