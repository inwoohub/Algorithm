// ----------------------------------------
// 사용 변수
// N : 구하고 싶은 자리수
// MOD : 나머지를 구하기 위한 수
// DP : 최대 값을 구하기 위한 배열
// ----------------------------------------
// 알고리즘
// 비트마스킹 (사용한 수 비트로 표기하기)
// → 문제 에서 0~9 를 모두 사용하라고 함,
//   따라서 0~9 를 모두 체크하기 위해 boolean 보다 비트로 사용 0~1023
// ----------------------------------------


import java.io.*;
import java.util.*;

public class Main{

    static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N =Integer.parseInt(br.readLine());
        long[][][] DP = new long[N+1][10][1024];

        // DP 초기값 세팅 (DP[1][i][1<<i] : 1자리 숫자, i로 끝나는수, 사용된 수 비트로 표기) 이때, 시작은 0으로 불가능하여 1로 초기값 세팅
        for(int i=1; i<10; i++){
            DP[1][i][1<<i] = 1;
        }

        
        for(int i=2; i<N+1; i++){          // i=1 인경우는 위에서 이미 초기화 했기 때문 2~N 자리수까지 진행
            for(int j=0; j<10; j++){       // 마지막으로 끝나는 수는 0~9 까지 모두 가능
                for(int k=0; k<1024; k++){ // k는 사용한 수 모두 탐색하기 위한 비트마스킹 [9][8][7][6][5][4][3][2][1][0] 
                    int bit = (k | 1<<j);  // 1<<j : 마지막 숫자가 j이기 때문 마스킹을 위함 (k 는 현재 수, j는 사용되었으니 포함 시키기위해 | (or) 연산
                    if(j==0){ // j=0 즉, 0으로 끝나기 위해서는 이전 앞에서 1로 끝나야함
                        DP[i][j][bit] = ( DP[i][j][bit] + DP[i-1][j+1][k] ) % MOD;
                    }
                    else if(j==9){ //j=9 즉, 9으로 끝나기 위해 이전 앞에서 8로 끝나야함
                        DP[i][j][bit] = ( DP[i][j][bit] + DP[i-1][j-1][k] ) % MOD;
                    }
                    else{  // j=1~8 은 j-1 , j+1 인 경우 다 가능
                        DP[i][j][bit] = ( DP[i][j][bit] + DP[i-1][j-1][k] + DP[i-1][j+1][k] ) % MOD;
                    }
                }
            }    
        }

        long sum = 0;
        for(int i=0; i<10; i++){
            sum = sum + DP[N][i][1023];
            sum = sum % MOD;
        }
        System.out.print(sum);
    }
}