// ----------------------------------
// 알고리즘
// 2차원 배열 (행렬) 사용
// 각 거리는 '1'
// 행렬x행렬 (제곱) 을 통해서 거리 계산
//
// ----------------------------------

import java.io.*;
import java.util.*;

public class Main{
    
    static StringBuilder sb = new StringBuilder();
    static final int MOD = 1000000007;
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // [0] : 정보과학관
        // [1] : 전산관
        // [2] : 미래관
        // [3] : 신양관
        // [4] : 한경직기념관
        // [5] : 진리관
        // [6] : 학생회관
        // [7] : 형남공학관
        long[][] arr = new long[8][8];
        
        // 거리 '1' 인 길
        arr[0][1] = arr[0][2] = 1;
        arr[1][0] = arr[1][2] = arr[1][3] = 1;
        arr[2][0] = arr[2][1] = arr[2][3] = arr[2][4] = 1;
        arr[3][1] = arr[3][2] = arr[3][4] = arr[3][5] = 1;
        arr[4][2] = arr[4][3] = arr[4][5] = arr[4][7] = 1;
        arr[5][3] = arr[5][4] = arr[5][6] = 1;
        arr[6][5] = arr[6][7] = 1;
        arr[7][6] = arr[7][4] = 1;

        arr = divide(arr,N);
        System.out.print(arr[0][0]);
        
    }

    static long[][] divide(long[][] arr , int N){
        // 마지막인 경우
        if(N==1){
            return arr;
        }

        // 2로 나누어 떨어질 경우
        if(N%2==0){
            long[][] newArr = divide(arr, N/2);
            return square(newArr, newArr);
        }

        // 2로 나누어 떨어지지 않는 경우
        else{
            return square( divide(arr,N-1), arr);
        }
        
    }

    // 행렬곱 함수
    static long[][] square(long[][] arr1, long[][] arr2){
        // 반황용 새 배열 생성
        long[][] newArr = new long[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                for(int k=0; k<8; k++){
                    newArr[i][j] = (newArr[i][j] + (arr1[i][k] * arr2[k][j]) % MOD) % MOD; // 수가 너무 큰거 방지 나머지 연산 각각 해주기 
                }
            }
        }
        return newArr;
    }
}