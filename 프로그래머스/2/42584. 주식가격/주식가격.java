/** 
알고리즘 :
    Stack
    
문제 고민 :
    1. 뒤에 있는 숫자 중에 나보다 작고 가장 가까운 수 찾기 문제
        -> 스택을 활용하기

전략 : 
    1. 0 ~ prices.length-1 까지 순회
    2. Stack 이 비어있다면 push 하기
    3. Stack 이 비어있지 않다면,
        3-1. peek() <= 현재값 인 경우
            -> 증가함으로 현재값 Stack 에 넣기
        3-2. peek() > 현재값 인 경우
            -> 감소했음으로 pop 하기
    4. 전체 순회후 남은 Stack 에 남아있는 것 털어주기 (감소하지 않은 것들임)
*/

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length]; // 정답 반환용
        Stack<Integer> stack = new Stack<>();  // Stack 생성, Integer: index 들어감
        for(int i=0; i<prices.length; i++){    // 1. prices 배열 순회
            if(stack.isEmpty()) stack.push(i); // 2. 비어있다면, push
            else{ // 3. 비어있지 않다면,
                int peek = prices[stack.peek()]; // stack.peek() 의 price
                if(peek > prices[i]) { // 3-2. peek > 현재값인 경우
                    while(!stack.isEmpty()){
                        peek = prices[stack.peek()]; // 스택 peek 의 price
                        if( peek <= prices[i] ) break; // 종료! (감소하지 않는 상태)
                        int popIdx = stack.pop();
                        answer[popIdx] = i - popIdx; // 떨어지지 않은 기간 저장
                    }
                }
                stack.push(i); // push 하기 (3-1, 3-2 공통)
            }
        }
        // 4. 전체 순회후 남은 Stack 털어주기
        while(!stack.isEmpty()){
            int curIdx = stack.pop();
            answer[curIdx] = prices.length -1 -curIdx; // 배열 총 길이 - 감소하지지 않은 price의 Index
        }
        return answer;
    }
}