// 알고리즘
// 우선순위 큐

import java.util.PriorityQueue;

class Solution {
    
    
    public int solution(int[][] routes) {
        
        // 우선순위 큐 생성 (진입 시점으로 오름차순, 같다면 진출 시점으로 오름차순)
        PriorityQueue<int[]>  pq = new PriorityQueue<>( (a,b) -> {
            return Integer.compare(a[1], b[1]);
        });
        
        // routes 순회하면서 우선순위 큐에 넣어주기
        for(int i=0; i<routes.length; i++){
            int[] cur = routes[i];
            pq.offer(new int[]{cur[0], cur[1]});
        }
        
        // 카메라 초기 세팅
        int[] init = pq.poll();
        int end = init[1]; // 가장 처음꺼에서 진출 지점
        int camera = 1;
        
        // 큐가 빌 때까지 순회하면서 그리디하게 카메라 설치하기
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int s = cur[0]; // 현재 진입 시점
            int e = cur[1]; // 현재 진출 시점
            if( s<=end ) continue; // 구간 안쪽에 카메라가 있는경우
            end = e; camera++; // 안쪽에 없다면 카메라 설치
        }
        return camera;
    }
    
}