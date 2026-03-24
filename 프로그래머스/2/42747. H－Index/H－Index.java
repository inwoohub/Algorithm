// 알고리즘
// 정렬문제

// n개의 논문이 있다.
// h의 최대 값을 정하는데, h이상이 h이상 개가 필요하다.
// 그럼 0 부터 시작

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        
        
        
        
        // citations 오름차순으로 정렬하기
        Arrays.sort(citations);
        
        int MAX = 0; // MAX 초기 세팅
        int curValue = 0; // 초기값은 0
        int curIdx = 0; // 현재 바라보는 인덱스
        int size = citations.length; // citations의 배열의 길이
        
        while(true){
            
            if(curIdx >= size) break; // 배열 터지는거 방지
            
            if(curValue <= citations[curIdx]){ // 만약 h 보다 큰 것을 찾았다면,
                if(size - curIdx >= curValue){ // 뒤에 남은 수가 h 이상이라면,
                    MAX = curValue; // MAX 업데이트
                    curValue++; // h값 1 증가
                }
                else {
                    break; // h번 이하 인용된 경우
                }
            } else {
                curIdx++; // 다음 배열 바라보기
            }
        }
        
        int answer = MAX;
        
        return answer;
    }
}