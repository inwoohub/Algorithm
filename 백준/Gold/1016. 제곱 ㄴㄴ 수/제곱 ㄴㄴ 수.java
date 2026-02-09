// ====================
// 알고리즘
// 에라토스테네스의 채
// ====================
// 1 1000
// ====================
// 608
// ====================

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long MIN = Long.parseLong(st.nextToken()); 
        long MAX = Long.parseLong(st.nextToken());
        int arrSize = (int) (MAX - MIN + 1); // [0] 생략 ex) 1 ~ 10 = 10 - 1 + 2 = 11 
        
        // 1) 배열 생성
        boolean[] arr = new boolean[arrSize];

        // 2) 2 ~ Math.sqrt(MAX) 제곱 수 필터링
        for(long i=2; i<=Math.sqrt(MAX); i++){
            
            long sq = i * i * 1L; // i의 제곱

            // (MIN / sq) * sq 는 MIN 이하의 sq의 가장 큰 배수
            // 3) -> 활용 ((MIN + sq - 1 ) / sq ) * sq 는 MIN 이상인 sq의 가장 작은 배수 찾기
            long start = ( (MIN + sq - 1) / sq ) * sq;

            // 4) sq를 계속 더해가면서 나누어 떨어지는거 true 로 값 바꾸기
            for( long x = start; x<= MAX; x=x+sq ){
                arr[(int) (x-MIN)] = true; // MIN, MAX 는 배열이 너무 큼으로 인덱스 보정
            }
            
        }

        // 5) 배열에서 false인 개수 세기
        int count = 0;
        for(int i=0; i<arrSize; i++){
            if(!arr[i]){
                count++;
            }
        }

        // 결과 출력
        System.out.print(count);   
    }
}