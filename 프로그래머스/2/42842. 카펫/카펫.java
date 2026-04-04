/**
알고리즘 :
    
문제 요약 :
    1. 테두리는 갈색임
    2. brown 개수, yellow 개수 주어짐
    3. 전체 카펫의 크기
    4. 가로 길이 >= 세로 길이
    
전략 :
    1. 고정된 값인 brown과 yellow 활용하기
    2. 가로의 최대 크기는 brown/2-1
    3. brown/2-1 ~ 3 까지 점진적으로 내려가면서 탐색하기
    4. 현재 세로는 borwn/2 - 현재 가로크기 + 2
*/

import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int half = brown/2;
        for( int width = half-1; width>2; width-- ){
            int height = half - width + 2 ;
            if( yellow  == ((width-2) * (height-2)) ){
                answer[0] = width;
                answer[1] = height;
                return answer;
            }
        }
        return answer;
    }
}