/**
두 개의 단어 begin -> target 으로 변환
1. 한 번에 한 개의 알파벳만 바꿀 수 있음
2. words에 있는 단어로만 변환 가능함

제한사항
- 모든 단어 소문자 (모든 단어 길이 같음)
- 각 단어의 길이는 3 이상 10 이하
- words 단어 3개 이상, 50 개 이하 (중복x)
- begin != target
- 불가능시 '0'

- 전략 (dfs)
1. 한글자만 차이나는 것이라면 무조건 바꾸기
2. 만약 이전에 사용했던 거라면 return 해서 무한 반복 방지
3. 하나도 없으면 0 반환 or 이미 방문했던거라면 0 반환
*/

import java.util.*;

class Solution {
    
    static boolean[] visited; // 방문 처리용 배열
    
    public int solution(String begin, String target, String[] words) {
        
        // 방문 처리용 배열 생성
        visited = new boolean[words.length];
        
        // dfs 탐색 시작
        return search(begin, target, words, 0);
        
    }
    
    // dfs 탐색
    static int search(String begin, String target, String[] words, int count){
        
        // begin 이랑 target 이랑 비교 후 맞다면, return
        if(begin.equals(target)){
            return count;
        }
        
        // 다르다면 바꿀 수 있는 단어 탐색
        for(int i=0; i<words.length; i++) {
            
            if(visited[i]) continue; // 바꿨던 것은 넘어가기
            
            boolean check = match(begin, words[i]); // T: 1개만 다름, F: 그 외 (못 바꿈)
            
            // 변경 해야한다면 begin 이랑 바꿔주고
            if(check){
                
                String stayStr = words[i];
                visited[i] = true;
                words[i] = begin;
                begin = stayStr;
                
                int cc = search(begin, target, words, count+1);
                
                if( cc != 0 ) {
                    return cc;
                }
                
                visited[i] = false; // 백트래킹으로 재사용 가능
                
            }
            
        }
        
        return 0;
        
    }
    
    // 단어 비교
    static boolean match(String begin, String next){
        int count = 0;
        for(int i=0; i<begin.length(); i++){
            if(begin.charAt(i) != next.charAt(i)){
                count++;
            }
        }
        if(count == 1){
            return true;
        }
        return false;
    }
    
    
}