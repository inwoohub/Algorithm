/**
알고리즘 :
    HashSet
    
문제 요약 :
    전화 번호부(phone_book)에 적힌 전화번호 중 다른 번호의 접두어인 경우가 있는지 확인
    있다면 -> false
    없다면 -> true
    
전략 :
    1. 브도르포스 : 최대 1,000,000 이니까 1 + 2 + 3 + 4 + 5 + .... 1000000 = 1,000,000 x 500,000 = 1조 즉, 연산 불가능
    2. 해시set ?
        - 전부다 해시셋에 넣어둠
        - 하나씩 꺼내가면서 접두사로 조회 : 1개당 최대 20번
            - 만약 있다면 false
        - 끝까지 다 돌았는데 없었다면 true 반환
        
        * 1,000,000 x 20 = 20,000,000 번으로 시간 복잡도 괜찮음
*/

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        // 1. HashSet 생성
        HashSet<String> hs = new HashSet<>();
        
        // 2. HashSet 에 미리 다 넣어두기
        for(String next : phone_book){
            hs.add(next);
        }
        
        // 3. 전화 번호부에서 번호 하나씩 꺼내기
        for(String next : phone_book){
            /**
            StringBuilder.append vs String + String
            1. String 은 불변(Immutable) 객체
                -> String + String 은 붙이는게 아님, 새로운 String 객체를 만드는 것
            2. StringBuilder 은 가변(Mutable) 갹체
                -> 내부에 넉넉한 공간(버퍼)를 가지고 있음, 기존 객체에 이어 붙이는 것
            따라서 계속해서 객체를 만들기 vs 하나의 객체로 사용하기가 됨으로 메모리 낭비면에서 StringBuilder 가 우수함
            즉, StringBuilder 가 코딩테스트에서는 더욱 이점이 많다.
            */
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for(int i=0; i<next.length()-1; i++){
                sb.append(next.charAt(i)); // 문자열 이어 붙이기
                if(hs.contains(sb.toString())){ // 접두사 확인하기
                    count++;
                }
            }
            if(count>=1){ // 발견한 경우
                return false;
            }
        }
        return true;
    }
}