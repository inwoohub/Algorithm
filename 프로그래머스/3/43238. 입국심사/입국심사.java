// 알고리즘
// 이분탐색

import java.util.*;

class Solution {
    
    static long MIN; // 최소값
    
    public long solution(int n, int[] times) {
        
        // times 배열 오름차순으로 변환
        Arrays.sort(times);
        
        // high 값 지정하기 (배열에서 가장 작은 것만 사용했을 때 만들 수 있는 수)
        long high = 1L * times[0] * n ;
        MIN = 1L * n * times[0]; // 초기 최소값 지정
        
        // low 값은 0 지정
        long low = 0L;
        
        // 탐색 하기
        binarySearch(low, high, (long) n, times);
        
        long answer = MIN;
        return answer;
    }
    
    // n : 사람의 수
    static void binarySearch(long low, long high, long n, int[] times){
        
        // 이분 탐색이 끝날 때 까지 반복하기
        while(low<high){
            long mid = (low + high) / 2;
            long count = 0;
            
            // 심사관이 몇명이나 검사 가능한지 확인하기
            for(int i=0; i<times.length; i++){
                if( 1L * mid / times[i] == 0 ) break; // 한명도 심사 하지 못하는 경우부터는 끊어 주기
                count = count + (1L * mid / times[i]);
            }
            
            // 만약 사람을 더 많이 받을 수 있다면 high 값 줄여주기
            if(count >= n ){
                high = mid;
                MIN = mid; // 최소 시간 갱신
            }
            // 심사관 부족하다면 시간 더 늘려주기
            else {
                low = mid+1;
            }
        }

    }    
    
}