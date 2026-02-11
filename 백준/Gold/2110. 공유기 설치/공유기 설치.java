// =================
// 알고리즘
// 최대 거리 구하기 (이분 탐색을 통해 최대 거리 탐색)
// =================
// 5 3
// 1
// 2
// 8
// 4
// 9
// =================
// 3
// =================

import java.io.*;
import java.util.*;

public class Main{

    static int[] arr;    // 배열
    static int N, Router; // 집 개수, 공유기 수
    static int low, mid, high; // 2분 탐색용도

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 집 개수
        Router = Integer.parseInt(st.nextToken()); // 공유기 수
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 초기 값 세팅
        Arrays.sort(arr); // 정렬
        low = 0;
        high = arr[N-1]-arr[0];
        mid = high / 2; // 시작은 중간 값 부터
        int ans = search();

        // 데이터 출력
        System.out.print(ans);
        
    } // End of main

    static int search(){
        int ans = 0;
        while(low<=high){
            int count = 1;
            int curNode = 0;
            
            for(int i=1; i<N; i++){
                // 1) 두 노드 사이가 최대 거리보다 크다면, count 1 증가
                if( arr[i] - arr[curNode] >= mid ){
                    count++;
                    curNode = i; // 최근 노드 업데이트
                }
            }

            // 2) 집 개수 >= 라우터 개수
            if(count >= Router){
                ans = Math.max(ans, mid); // 최대 거리 업데이트
                LowBoundUp(); // 2분탐색 distance Up!
            }

            // 3) 거리가 널널한 경우
            else {
                LowBoundDown(); // 2분탐색 distance Down!
            }
            
        } // End of while

        return ans;
        
    } // End of search

    // 거리 증가
    static void LowBoundUp(){
        low = mid+1;
        mid = (high + low) / 2;
    }

    // 거리 감소
    static void LowBoundDown(){
        high = mid-1;
        mid = (high + low) / 2;
    }
    
}