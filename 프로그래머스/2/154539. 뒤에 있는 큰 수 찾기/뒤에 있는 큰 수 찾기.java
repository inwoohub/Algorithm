// 알고리즘
// 스택

import java.util.*;

class Solution {
    
    public int[] solution(int[] numbers) {
        
        // 정답 배열
        int[] answer = new int[numbers.length];
        // 스택 생성
        Stack<int[]> stack = new Stack<>();
        for(int i=0; i<numbers.length; i++){
            // 1. 현재 인덱스 값
            int cur = numbers[i];
            // 2. 스택이 비어있지 않은 경우
            if(!stack.isEmpty()) {
                // 현재 수가 더 큰 경우
                if(cur > stack.peek()[1]){
                    // 비어있지 않는 경우 반복
                    while(!stack.isEmpty()){
                        // 스택에 탑이 더 큰경우는 종료
                        if(stack.peek()[1] >= cur ){
                            break;
                        }        
                        // 스택 꺼내기 pop
                        answer[stack.pop()[0]] = cur;
                    }   
                }
            }
            // 스택에 넣어주기
            stack.push(new int[]{i,cur});
        }
        // 남은 스택 처리 후 종료
        while(!stack.isEmpty()){
            int[] cur = stack.pop();
            answer[cur[0]] = -1;
        }
        
        return answer;
    }
    
}
