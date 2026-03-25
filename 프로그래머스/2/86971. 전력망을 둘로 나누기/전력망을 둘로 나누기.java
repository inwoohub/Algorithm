// 알고리즘
// bfs + 백트래킹 

import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] graph; // 연결 리스트
    static boolean[][] status; // 해당 노드 연결 되어있는지 아닌지 상태 T: 연결 o, F: 연결 x
    static boolean[] visited; // 해당 노드 방문 했는지 안했는지 T: 방문, F: 미방문
    static int MIN;
    
    public int solution(int n, int[][] wires) {
        
        MIN = n; // MIN 값 초기 세팅 (연결 끊지 않았을 경우를 초기값으로)
        
        status = new boolean[n+1][n+1];
        
        graph = new ArrayList[n+1]; // 연결 리스트 배열 생성
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>(); // 연결 리스트 생성 및 초기화
        }
        
        for(int i=0; i<wires.length; i++){
            int[] wire = wires[i];
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]); // 연결 리스트 양방향 매핑
            status[wire[0]][wire[1]] = true;
            status[wire[1]][wire[0]] = true; // 양방향으로 연결 처리
        }
        
        // 연결 하나씩 끊고 다시 붙이면서 탐색 시작
        for(int i=0; i<wires.length; i++){
            int[] wire = wires[i];
            status[wire[0]][wire[1]] = false;
            status[wire[1]][wire[0]] = false; // 양방향으로 연결 해제 시키기
            bfs(n); // bfs 탐색
            status[wire[0]][wire[1]] = true;
            status[wire[1]][wire[0]] = true; // 다시 양방향으로 연결 처리
        }
        
        int answer = MIN;
        return answer;
    }
    
    // bfs 탐색
    static void bfs(int n){
        visited = new boolean[n+1]; // 방문 처리용 배열 생성
        int count = 1; // 현재 네트워크 수 (defalut : 1)
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(1); // 항상 1로 시작
        visited[1] = true; // 방문 처리
        
        while(!q.isEmpty()){
            int curNode = q.poll();
            
            for(int nextNode : graph[curNode]){
                if(!visited[nextNode]){ // 미 방문 노드인 경우
                    if(status[curNode][nextNode]){ // 연결이 해제되지 않은 경우
                        visited[nextNode] = true; // 방문처리
                        count++; // 네트워크 1 증가
                        q.offer(nextNode); 
                    }
                }
            }
            
        }
        MIN = Math.min(MIN , Math.abs( Math.abs(n-count) - Math.abs(count))); // 기존 MIN vs (n - count) 절대값 갱신
    }
    
}