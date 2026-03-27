// 알고리즘
// 우선순위 큐

import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    
    public int solution(int[][] jobs) {
        
        // 작업 마다 걸린 시간을 저장하는 배열
        int[] times = new int[jobs.length];
        
        // 작업이 끝나는 시간
        int endTime = 0;
        
        // 우선순위 큐 생성
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> {
            if( a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if( a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);}); // [0] : 소요시간 -> [1] : 요청시간 ->  [2] : 작업 번호 순
        
        // jobs 배열 정렬하기 (요청 시간이 빠른순 만약, 요청 시간이 같다면, 소요 시간이 빠른 순)
        Arrays.sort(jobs, (a,b) -> {
            if(a[0] == b[0]){ return Integer.compare(a[1], b[1]); }
            return Integer.compare(a[0], b[0]);
        });
        
        // jobs 배열 순회하면서 탐색
        for(int i=0; i<jobs.length; i++){
            int[] curJob = jobs[i];
            int x = curJob[0]; // x: 요청 시각
            int y = curJob[1]; // y: 소요 시각
            
            // endTime 시간 전이라면 큐에 넣고 넘어가기
            if( x <= endTime){
                pq.offer(new int[]{y,x,i});
                continue;
            }
            
            // endTime 이 지났다면, 이전 작업 처리하기
            while( endTime < x ){
                if(!pq.isEmpty()){
                    int[] job = pq.poll();
                    int a = job[0]; // a: 소요 시각
                    int b = job[1]; // b: 요청 시각
                    int c = job[2]; // c: 순서

                    // 요청 시간이 더 뒤인 경우
                    if(endTime < b){
                        endTime = b + a; // 이게 더 멀리있음
                        times[c] = endTime - b; // 반환 시간
                    }
                    // 요청 시간이 사이에 낀 경우
                    else {
                        endTime = endTime + a;  // 현재 시간 갱신
                        times[c] = endTime - b; // 끝나는 시간 ( 늦게 들어온 만큼 더해주기 )    
                    }
                }
                // 큐가 비었다면 멈추기
                else {
                    break;
                }
            }
            pq.offer(new int[]{y,x,i});
        }
        
        // 순회 끝났지만, 남은 작업 털어주기
        while(!pq.isEmpty()){
            int[] job = pq.poll();
            int a = job[0]; // a: 소요 시각
            int b = job[1]; // b: 요청 시각
            int c = job[2]; // c: 순서
            
            // 요청 시간이 더 뒤인 경우
            if(endTime < b){
                endTime = b + a; // 이게 더 멀리있음
                times[c] = endTime - b; // 반환 시간
            }
            // 요청 시간이 사이에 낀 경우
            else {
                endTime = endTime + a;  // 현재 시간 갱신
                times[c] = endTime - b; // 끝나는 시간 ( 늦게 들어온 만큼 더해주기 )    
            }   
        }
        
        
        // 평균 구해주기
        int sum = 0;
        for(int next : times){
            sum += next;
        }
        
        return sum / times.length;
        
    }
    
}