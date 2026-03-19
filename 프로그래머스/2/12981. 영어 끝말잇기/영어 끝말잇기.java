// 알고리즘
// 순회?

import java.util.*;

class Solution {
    
    // n: 사람 수, words[]: 단어 배열
    public int[] solution(int n, String[] words) {
        
        // 끝말잇기 체크용
        boolean check = true;
        int[] answer = new int[2];
        
        // 해당 체크했는지 안했는지 확인 (풀고나서 HashMap 과 Map 의 차이점 찾아보자)
        HashMap<String, Integer> map = new HashMap<>(); // 0: 미사용, 1: 사용
        String pre = words[0];
        map.put(pre,1);
        String post = "";
        
        // 1. 단어 개수만큼 순회
        for(int i=1; i<words.length; i++){
            
            // 단어 꺼내기
            post = words[i];
            
            // 2. 해당 단어 사용했는지 확인 map 에서 꺼내보기
            int exist = map.getOrDefault(post, 0);
            
            // 3. 이미 사용했다면
            if(exist == 1){
                answer[0] = i%n + 1; // 걸린 사람
                answer[1] = i/n + 1; // 현재 바퀴 수
                break; // for문 탈출
            }
            
            // 4. 이전 끝 긑자와 첫 글자가 다르다면,
            if( pre.charAt(pre.length()-1) != post.charAt(0) ){
                answer[0] = i%n + 1; // 걸린 사람
                answer[1] = i/n + 1; // 현재 바퀴 수
                break; // for문 탈출
            }
            
            // 5. 3,4 둘 다 아니라면, 사용 처리하기
            map.put(words[i], 1);
            
            // 6. pre 단어 업데이트
            pre = post;
        }
        
        return answer;
    }
}