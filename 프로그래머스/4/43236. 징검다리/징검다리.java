/**
알고리즘 :
    - 이분 탐색

문제 요약 :
    - 출발지 ~ 도착지점 거리 : distance   (1 ~ 10억)
    - 그 사이에 바위들이 있음 : rocks      (1 ~ 50000)
    - 바위들 제거함 : n 개 (아무거나 n개)   (1 ~ 50000)
    - 바위 사이 거리에서 최솟값 중 가장 큰 거 구하기
    
    
문제 전략 :
    브로드포스 : 50000 x 50000 = 2,500,000,000 = 25억
    -> n개를 모든 조합으로 지우고 거리를 구한 경우
    -> 효율성 테스트 부적합
    
    0 2 11 14 17 21 25 라는 가정하에 n개를 지운다.
    여기서 n개를 어떻게 뽑을까?
    
    1. rocks 배열 정렬하기
    2. 초기 left, right 값 지정하기 (left = 0, right = distance)
    3. 2분 탐색 시작 1차 while문 (left<=right) 최대 mid 값 찾기
        - mid = (left + right) / 2 지정
        - 2차 while문 바위 제거
            - 종료조건! 1. 바위 제거를 n개 보다 많이 한 경우 종료
            - 종료조건! 2. 끝까지 탐색 완료한 경우
            - 바위 사이 거리가 mid 보다 작다면 제거후 비교
            - 크다면 패스하고 다음 거리 비교
        - 바위를 n개 이하로 제거했을 경우 최대값 갱신
    4. 결과 출력 후 종료
*/

import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        // 1. rocks 배열 정렬하기
        Arrays.sort(rocks);
        
        // 2. left, right 값 지정
        int left = 0;
        int right = distance;
        
        // 3. 최소값중 최대값 구하는 이분 탐색
        while( left <= right ){
            int mid = (left+right)/2;
            int count = 0; // 지운 바위 개수
            int leftValue = 0;
            int pointer = 0;
            
            // 4. 바위 거리 비교
            while(true){
                if(count > n ){ // 5. n개 보다 더 지움
                    break;
                }
                if(pointer == rocks.length){ // 6. 다 검사 완료
                    if( distance - leftValue < mid) count++; // 유종의 미
                    break;
                }
                
                // 7. 바위 사이 거리 비교하기
                if( rocks[pointer] - leftValue < mid ){ // 거리가 작다면 바위 제거
                    count++;
                    pointer++;
                } else { // 거리가 크다면 패스하기
                    leftValue = rocks[pointer];
                    pointer++;
                }
            } // End while
            
            if(count <= n){ // n으로 최대값 갱신 여부
                answer = Math.max(answer, mid); // 갱신
                left = mid+1;
            } else {
                right = mid-1;
            }   
        }
        return answer;
    }
}