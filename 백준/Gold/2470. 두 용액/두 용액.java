// ==================
// 알고리즘
// 두 용액 -> 이건 투포인터다
// 양쪽 끝에서 시작. 만약 A + B > 0 B 한칸 줄이기, A - B < 0 이라면, A 한칸 늘리기
// 비교하면서 최소값 저장하기, 0이라면 바로 출력해도됨. 그렇게 만날때까지 반복
// ==================
// 5
// -2 4 -99 -1 98
// ==================
// -99 98
// ==================

import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1) 오름차순 정렬
        Arrays.sort(arr);

        // 2) 포인터 생성 | A : 포인터A(좌측), B: 포인터B(우측) 
        int A = 0;
        int B = N-1;
        int MIN = 2000000000; // 문제에서 최대값 ( -100000000 <= 주어진 수 <= 100000000 )
        int minA = 0; int minB = 0; // 최소값 인 경우 인덱스 저장
        boolean check = false; // 0 조건 확인용 bool
        
        // 3) 만나거나, A가 더 클 때까지 반복
        while(A<B){
            // 최소값 갱신
            if( Math.abs(MIN) > Math.abs(arr[A]+arr[B]) ){
                MIN = arr[A]+arr[B];
                minA = A;
                minB = B;
            }
            
            // 단, 해당 조건인 0이라면 종료
            if( arr[A] + arr[B] == 0 ){
                check = true;
                break;
            }

            if( arr[A] + arr[B] > 0 ) {
                B--;
            } else if ( arr[A] + arr[B] < 0 ) {
                A++;
            }   
        } // END of while

        // 결과 출력
        if(check){
            System.out.println(arr[A] + " " + arr[B]);
        } else {
            System.out.println(arr[minA] + " " + arr[minB]);
        }
        
    } // END of main
}