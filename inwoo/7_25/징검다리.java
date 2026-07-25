import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        // 1. 바위 오름차순 정렬하기
        Arrays.sort(rocks);

        // 2. 정답 구하기
        int answer = search(distance, rocks, n);

        // 3. 정답 반환
        return answer;
    }

    static int search(int distance, int[] rocks, int n) {
        if( rocks.length == n) { // 모든 다리를 제거하는 경우
            return distance;
        }

        int max = 0;           // 정답 최대값
        int left = 0;          // 정답이 될 수 있는 범위 좌측
        int right = distance;  // 정답이 될 수 있는 범위 우측

        while(left < right) {
            int mid = (left+right)/2;
            int cur = 0; // 현재 위치
            int count = 0;
            for(int i=0; i<rocks.length; i++){
                if( mid > rocks[i] - cur ) { // 두점 사이의 거리보다 큰 경우
                    count++;                 // i번째 바위 제거
                } else {
                    cur = rocks[i];          // 현재위치는 i번째 돌의 위치
                }
            }
            if( mid > distance - cur ) count++; // 현재 위치와 도착 지점 사이

            if(count <= n) { // 바위를 덜 제거하거나 딱 맞추면 갱신
                left = mid + 1;
                max = Math.max(max, mid);
            } else {
                right = mid;
            }
        }
        return max;
    }
}