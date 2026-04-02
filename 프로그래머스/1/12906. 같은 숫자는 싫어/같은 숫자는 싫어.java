/**
알고리즘 : ArrayDeque

전략 :
    1. 앞에서 부터 차근차근 쌓기 (addList)
    2. 맨 뒤 확인하기 (peekLast)
    3. 같다면 버리기
    4. 다르다면 1번 으로
    5. (1~4 반복)
*/

import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Deque<Integer> dq = new ArrayDeque<>(); // ArrayDeque 생성
        for(int next : arr){
            // 초기 (비어있는) 상태
            if(dq.isEmpty()){
                dq.addLast(next); // 맨 뒤 넣어주기
                continue;
            }
            int peekInt = dq.peekLast(); // 맨 마지막 숫자 꺼내기
            if(peekInt != next) dq.addLast(next); // 다르다면 채워주기
        }
        
        // ArrayDeque -> answer(정답) 배열에 옮겨 담기
        int[] answer = new int[dq.size()]; // 정답용 반환 배열 생성
        int idx = 0;
        while(!dq.isEmpty()){
            answer[idx++] = dq.pollFirst();
        }
        return answer;
    }
}