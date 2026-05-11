/**
알고리즘 :
    HashMap
    
문제 요약 :
    1. 나만의 카카오 성격 유형 검사지 만들기
    2. RT, CF, JM, AN (2x2x2x2) 처럼 MBTI 같은 성격들이 있음
    3. 점수는 (3,2,1,0,1,2,3) 순으로 있음
    4. 더 많은 점수가 들어있는걸로 반환 하면됨, 다만 점수가 같다면 사전순으로 앞서는걸로 넣으면 됨
    
    * survey  ["AN", "CF", "MJ", "RT", "NA"]
    * choices [5, 3, 2, 7, 5]
    * 7 매우 동의 ~ 1 매우 비동의
    
문제 전략 :
    1. HashMap<성격, Integer> 로 해시맵 만들기
    2. 비교 후 높은 곳에 점수 넣어주기
    3. 다시 꺼내서 비교 후 MBTI 출력하기
*/

import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        // 1. HashMap 만들기
        HashMap<Character, Integer> map = new HashMap<>();
        settingMap(map); // 해시맵 초기값 세팅하기 (전부다 0 넣어줌)
        
        // 2. for 문으로 비교 후 맵에 점수 채워넣기
        for(int i=0; i<choices.length; i++){
            String sv = survey[i];
            int choice = choices[i];
            select(sv,choice, map);
        }
        
        // 3. 더 큰값 가져오기
        return getAnswer(map);
    }
    
    // 성격 유형 선택하기
    public void select(String sv, int choice, HashMap<Character, Integer> map){
        char A = sv.charAt(0);
        char B = sv.charAt(1);
        if(choice == 4) return; // 고르지 않음
        if(choice == 1){        // 매우 비동의
            addPoint(A,3,map);
        } else if(choice == 2){ // 비동의
            addPoint(A,2,map);
        } else if(choice == 3){ // 약간 비동의
            addPoint(A,1,map);
        } else if(choice == 5){ // 약간 동의
            addPoint(B,1,map);
        } else if(choice == 6){ // 동의
            addPoint(B,2,map);
        } else if(choice == 7){ // 매우 동의
            addPoint(B,3,map);
        }
    }
    
    // 점수 증가
    public void addPoint(char c, int p, HashMap<Character, Integer> map){
        map.put(c, map.get(c) + p); // 점수 업데이트
    }
    
    
    // 초기 map 세팅 (키 없음 방지)
    // 물론 getOrDefault 쓰면 되지만, 많지 않기 때문에 미리 생성
    public void settingMap(HashMap<Character,Integer> map){
        map.put('R',0);
        map.put('T',0);
        map.put('C',0);
        map.put('F',0);
        map.put('J',0);
        map.put('M',0);
        map.put('A',0);
        map.put('N',0);
    }
    
    // 정답 가져오기
    public String getAnswer(HashMap<Character,Integer> map){
        StringBuilder sb = new StringBuilder();
        if(map.get('R') >= map.get('T') ){
            sb.append("R");
        } else {
            sb.append("T");
        }
        if(map.get('C') >= map.get('F') ){
            sb.append("C");
        } else {
            sb.append("F");
        }
        if(map.get('J') >= map.get('M') ){
            sb.append("J");
        } else {
            sb.append("M");
        }
        if(map.get('A') >= map.get('N') ){
            sb.append("A");
        } else {
            sb.append("N");
        }
        return sb.toString();
    }
    
}