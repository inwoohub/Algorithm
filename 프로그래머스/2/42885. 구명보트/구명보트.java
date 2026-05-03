/**
알고리즘 :
    이분 탐색
    
문제 요약 :
    구명보트 최대한 적게 사용해서 모든 사람 구출하기
    
전략 :
    1. 정렬
    2. left, right 쓰기
    3. right 수 < limit 이라면
        left수 + right 수 < limit 이라면 둘 다 한칸 씩 이동
        left수 + right 수 > limit 이라면 rigth 만 한칸 이동
    4. left == right라면 1번 계산 후 빠져나오기
*/

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length-1;
        int answer = 0;
        
        while(left <= right){
            if(left == right){
                answer++;
                break;
            }
            
            int leftValue = people[left];
            int rightValue = people[right];
            
            // 혼자 사용해야함
            if( rightValue > limit || leftValue+rightValue > limit ){
                right--;
                answer++;
                continue;
            }
            
            // 같이 사용 가능함
            left++;
            right--;
            answer++;
        }
        return answer;
    }
}