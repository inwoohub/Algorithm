// 알고리즘
// 네트워크 개수 구하기
// bfs + for문 사용 (전체 순회하면서 bfs처리 방문 안 한것이 네트워크 개수)

import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] graph; // 그래프
    static boolean[] visited; // 방문 처리용 배열
    
    public int solution(int n, int[][] computers) {
        
        // 방문 처리용 배열 생성
        visited = new boolean[n+1];
        
        // 그래프 생성 (비어있음)
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        // 노드 양방향 매핑
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j) continue; // 자기 자신은 패스
                if(computers[i][j] == 1){ // 연결 되어있는 지점 찾기 (단방향으로 이어주기) -> 어차피 양방향으로 만들어짐
                    graph[i+1].add(j+1);
                }
            }
        }
        
        // 네트워크 개수 탐색
        int answer = search(n);
        return answer;
    }
    
    // 네트워크 개수 탐색
    static int search(int n){
        int count = 0;
        for(int i=1; i<=n; i++){
            // 해당 노드 방문 전이라면,
            if(!visited[i]){
                count++; // 네트워크 개수 증가
                bfs(i);
                visited[i] = true; // 방문 처리
            }
        }
        return count;
    }
    
    // bfs 탐색
    static void bfs(int n){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(n);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : graph[cur]){ // 연결된 graph 탐색
                if(!visited[next]){
                    q.offer(next);
                    visited[next] = true; // 방문 후 큐에 넣기 (bfs)
                }
            }
        }
    }
    
}