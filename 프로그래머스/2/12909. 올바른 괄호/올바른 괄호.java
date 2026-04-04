/**
알고리즘 :
    Stack
    
전략 : 
    1. '(' 가 들어오면 스택에 추가
    2. ')' 가 들어오면 스택에서 한개 제거
    3. ')' 가 들어왔을 때 스택에 한개도 없다면, false 처리
*/

import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Integer> stack = new Stack<>();
        boolean answer = true;
        for(int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            if(cur == '(') {
                stack.push(1); // 문제에서 괄호는 '(' 밖에 없음으로 아무꺼나 넣어줘도 무방함.
            }
            else if(cur == ')') {
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        if(!stack.isEmpty()){ // 스택이 비어있지 않으면 false
            return false;
        }
        return answer;
    }
}