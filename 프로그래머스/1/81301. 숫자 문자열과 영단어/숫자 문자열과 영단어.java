/**
알고리즘 :
    HashMap, 구현
    
문제 요약 :
    네오가 프로드에게 숫자를 건넬 때 일부 자릿수를 영단어로 바꾼 카드를 건네줌
    그럼 원래 숫자를 찾는 게임
    * 1 <= s <= 50

전략 :
    1. HaspMap 만들기 <문자열, 숫자>
    2. s 탐색 중 문자열이 나오면 문자열 다 더하다가 HashMap 값이 있다면 숫자로 변환
    3. 변환된 숫자 붙여서 출력
*/

import java.util.*;

class Solution {
    public int solution(String s) {
        
        // 1. HashMap 생성
        HashMap<String, String> map = new HashMap<>();
        setting(map);
        
        StringBuilder sb = new StringBuilder();
        String curStr = "";
        
        // 2. s 탐색
        for(int i=0; i<s.length(); i++){
            int A = (int) s.charAt(i);
            // 문자열인 경우
            if( A >= 97 ){
                curStr = curStr + s.charAt(i);
                
                // 완성된 숫자 확인
                String B = map.getOrDefault(curStr, "null");
                if( !B.equals("null") ) { // 완성되었다면
                    sb.append(B);
                    curStr = ""; //초기화
                }
            }
            // 숫자인 경우
            else {
                sb.append( s.charAt(i) );
            }
        }
        int answer = Integer.parseInt(sb.toString());
        return answer;
    }
    
    static void setting(HashMap<String, String> map){
        map.put("zero", "0");
        map.put("one","1");
        map.put("two","2");
        map.put("three","3");
        map.put("four","4");
        map.put("five","5");
        map.put("six","6");
        map.put("seven","7");
        map.put("eight","8");
        map.put("nine","9");
    }    
}