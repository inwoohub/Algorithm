/**
알고리즘 :
    위상 정렬

문제 요약 :
    - n명의 권투선수 권투 대회 참여
    - 1번 ~ n번 번호 존재
    - 권투 경기 1:1 방식으로 진행
    - A선수가 B선수보다 실력이 좋다면, A선수는 B선수를 항상 이김
    - 주어진 경기 결과 가지고 선수들의 순위 매김
    - 정확하게 순위를 매길 수 있는 선수의 수 ! 리턴하기
        * 정확하게 순위 ? 이걸 어떻게 정의 ? 
        -> 해당 노드에서 모든 노드를 방문 할 수 있냐 없냐로 판단.
    
전략 :
    1. 이기는 경로 만들기
    2. 지는 경로 만들기
    3. 해당 노드에서 이기는 경로 및 지는 경로로 모든 노드 방문 가능한지 판단
    4. 모든 노드 방문 했다면 +1
*/

import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        
        int answer = 0;
        ArrayList<Integer>[] winPath = new ArrayList[n+1];  // 이기는 경로
        ArrayList<Integer>[] losePath = new ArrayList[n+1]; // 지는 경로
        for(int i=0; i<=n; i++){
            winPath[i] = new ArrayList<>();
            losePath[i] = new ArrayList<>();
        }
        for(int i=0; i<results.length; i++){
            int A = results[i][0]; // 승
            int B = results[i][1]; // 패
            winPath[A].add(B);
            losePath[B].add(A);
        }
        
        // i : 현재 노드
        for(int i=1; i<=n; i++){
            boolean[] visited = new boolean[n+1];
            pathCheck(i, winPath, visited);
            pathCheck(i, losePath, visited);            
            if(countCheck(visited, n)){
                answer++;
            }
        }
        return answer;
    }
    
    // 방문 체크
    static boolean countCheck(boolean[] visited, int n){
        int count = 0;
        for(int i=1; i<=n; i++){
            if(visited[i]) count++;
        }
        if( count == n ){
            return true;
        }
        return false;
    }
    
    // 경로 체크
    static void pathCheck(int i, ArrayList<Integer>[] list, boolean[] visited){
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(i);
        visited[i] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : list[cur]){
                if(visited[next]) continue;
                q.offer(next);
                visited[next] = true;
            }
        }
    }
}