/**
알고리즘 :
    bfs

문제 요약 :
    주어진 number를 활용해서 더하거나 빼서 타겟 넘버 만들기
    모든 숫자 다 사용해야함
    
전략 :
    1. Queue 만들기 , 현재 Index 및 현재 value 넣어주기
    2. + , - 두 가지 경우 수 넣어주기
    3. index 가 마지막 까지 도달했다면 target 과 비교해서 같다면 count 증가
*/

import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,numbers[0]}); // 맨 첫번째 수 더한 경우
        q.offer(new int[]{0,numbers[0]*(-1)}); // 맨 마지막 수 뺀 경우
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curIndex = cur[0];
            int curValue = cur[1];
            // 마지막 까지 도달한 경우
            if(curIndex == numbers.length-1){            
                // target 과 같다면
                if(curValue == target){
                    answer++;
                }
            } else {
                q.offer(new int[]{curIndex+1,curValue + numbers[curIndex+1]}); // 맨 첫번째 수 더한 경우
                q.offer(new int[]{curIndex+1,curValue + numbers[curIndex+1]*(-1)}); // 맨 마지막 수 뺀 경우    
            }
        }
        return answer;
    }
}