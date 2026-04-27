/**
알고리즘 : 
    스택 (큰 수 만들기)

문제 요약 :
    k개의 수를 제거했을 때 얻을 수 있는 가장 큰 수 구하기
    
    * me : 문자에서 어디든 숫자를 지울 수 있음 -> dfs로 한다면 가능하겠지만, 1,000,000 개 라는점이 불편함.
    * me : 그래서 스택 or 큐를 사용하며 O(n) 으로 끝내고 싶음.
    * me : 스택
            -> 후입선출 (LIFO)
            -> 스택에 앞에서 부터 넣어주기
            -> 새로 들어오는 수가 stack의 root 보다 크다면 stack의 head 날려주기 ?
    
전략 : 
    1. 앞에서 부터 순차적으로 큐에 삽입
    2. 새로 들어오는 수 vs Stack의 head 비교
        만약 head가 더 작다면, head pop
        만약 head가 더 크다면, push
    3. 만약 전부 다 지워졌다면, stack 이어서 다 넣어주고
    4. 뽑은다음에 뒤집어서 출력.
*/

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        
        // String -> int[] 변환
        String[] strArr = new String[number.length()];
        strArr = number.split(""); // 잘라서 배열에 넣어주기
        int[] arr = new int[number.length()];
        for(int i=0; i<number.length(); i++){
            arr[i] = Integer.parseInt(strArr[i]);
        }
        
        // 스택 생성
        Stack<Integer> stack = new Stack<>();
        
        // 카운트 생성 (stack에서 뺄)
        int count = 0;
        
        // 1. stack에 넣고 빼면서 카운트 관리하며 큰수 만들기
        for(int i=0; i<arr.length; i++){
            
            int next = arr[i];
            
            // 3. 비어있다면 | 더 이상 지울 수 없는 경우 : 무조건 추가
            if(stack.isEmpty() || count == k){
                stack.push(next);
                continue;
            }
            
            // 2. 새로 들어오는 next vs stack의 머리 비교하기
            while(!stack.isEmpty()){
                // 하다가 k만큼 제거했다면,
                if(count == k){
                    break;
                }
                
                int head = stack.peek(); // Stack's head
                if(head < next){
                    stack.pop(); // 스택의 헤드 제거
                    count++; // 카운트 증가
                } else {
                    break;
                }
            }
            stack.push(next); // 스택에 넣어주기
        }
        
        // 4. 스택에서 꺼내면서 뒤집어서 StringBuilder 에 담기
        int[] answerArr = new int[stack.size()]; // 일단, 스택 사이즈만큼 다 꺼내서 배열 생성
        for(int i=0; i<answerArr.length; i++){
            answerArr[answerArr.length-1-i] = stack.pop(); // 뒤집어서 저장 (LIFO -> FIFO)
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<number.length() - k; i++){ // 뒤집은 다음에 number.length() - k - 1 개 만큼만 가져오기 (뒤에 쓰레기값 무시하기 위해서)
            sb.append(answerArr[i]);
        }
        
        String answer = sb.toString();
        return answer;
    }
}