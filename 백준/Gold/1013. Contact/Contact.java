// =================
// 알고리즘
// 완~전 그리디
// =================
// 3
// 10010111
// 011000100110001
// 0110001011001
// =================
// NO
// NO
// YES
// =================

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tC = 0; tC < testCase ; tC++){

            // 0) 데이터 매핑
            String input = br.readLine();
            int size = input.length();
            int[] arr = new int[size];
            for(int i=0; i<size; i++){
                arr[i] = input.charAt(i)-'0';
                
            }


            // 1) idx : 현재 위치 , check : 완성 확인
            int idx = 0;
            boolean check = true;


            // 2) 탐색 시작
            while( idx<size ){
                
                // 3) "0" 로 시작
                if( arr[idx] == 0 ){

                    // 3-1) 0 다음이 없는 경우
                    if( idx+1 >= size ){
                        check = false;
                        break; 
                    }

                    if( arr[idx+1] == 1 ){
                        idx += 2; // 1까지 건너뛰기
                    } else {
                        check = false; 
                        break;
                    }
                }


                // 4) "1" 로 시작
                else{
                    
                    // 4-1) 최소 크기 "1001" 만족하는지 확인
                    if( idx+3 >= size ){
                        check = false; 
                        break;
                    }

                    // 4-2) "1" "0" "0" 만족 확인
                    if( arr[idx+1] == 0 && arr[idx+2] == 0 ){
                        idx += 2;
                    } else {
                        check = false; 
                        break;
                    }

                    if(idx+1>=size){
                        check = false; 
                        break;
                    }

                    // 4-3) 잔여 "0" 줍기
                    while( arr[idx+1]==0){
                        idx++;
                        if( idx+1 >= size){
                            check = false; 
                            break;
                        }
                    }

                    if(idx+1>=size){
                        check = false; 
                        break;
                    }


                    // 4-3) "다음 1이 오는지 확인"
                    if( arr[idx+1] == 1 ){
                        idx++;
                    } else {
                        check = false; 
                        break;
                    }

                    if(idx+1>=size){
                        break;
                    }

                    // 4-4) 잔여 "1" 줍기
                    int oneCount = 0;
                    while( arr[idx+1] == 1){
                        idx++;
                        oneCount++;
                        if( idx+1 >= size) break;
                    }

                    if(idx+1>=size){
                        break;
                    }
                    
                    // 4-5) "0" 마주침
                    // 4-5-1) "0" 뒤에 뭐가있는지 확인 "1" or "0"
                    idx++; // 현재 위치 마주친 0임.
                    if(idx+1>=size){
                        check = false;
                        break;
                    }
                    if(arr[idx+1]==0){
                        if(oneCount > 0){ // 앞에 "1" 이 여러개였다면 재도전
                            idx--;
                        } else { //앞에 "1"연속 없이 "0" 연속 2개 -> 실패
                            check = false;
                            break;
                        }
                    } else {
                        idx += 2;
                    }
                        
                }

            } // while 문 종료
            
            if(check){
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
            
        }

        System.out.println(sb);

        
    }
}