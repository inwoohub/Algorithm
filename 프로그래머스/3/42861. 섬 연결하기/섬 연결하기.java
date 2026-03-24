// 알고리즘
// MST (우선순위 큐)

// 어느 한 점에서 시작해서, 인접하는 노드 싹 다 우선순위 큐에 넣기
// 큐에서 하나씩 뽑아서 방문 했다면, 버리고 안 했다면 해당 노드 방문 처리 후
// 또 해당 노드에 인접한 노드 다 넣어주기

import java.util.*;

class Solution {
    
    static ArrayList<Node>[] list; // 노드가 들어있는 리스트
    
    public int solution(int n, int[][] costs) {
        
        int answer = 0; // 정답 반환용 변수 생성
        
        list = new ArrayList[n]; // 연결 리스트 생성
        for(int i=0; i<n; i++){
            list[i] = new ArrayList<>(); // 리스트 초기화
        }
        
        for(int i=0; i<costs.length; i++){ // cost배열 돌면서 연결 리스트에 추가
            int a = costs[i][0]; // a 노드
            int b = costs[i][1]; // b 노드
            int c = costs[i][2]; // c 비용
            list[a].add(new Node(b,c));
            list[b].add(new Node(a,c)); // 양방향 매핑 (노드, 비용)
        }
        
        boolean[] visited = new boolean[n]; // 방문 처리용 배열 (T/F) , Default : F
        
        // 우선순위 큐 생성 (cost 가 낮을 수록 우선순위가 높음)
        PriorityQueue<Node> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a.cost, b.cost) );
        
        // 우선순위 큐에 '0' 노드 넣어주기 (어떤 노드가 초기로 들어가던지 상관없음)
        pq.offer(new Node(0, 0));
        
        // 큐가 빌 때 까지 반복하기
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(!visited[cur.node]){ // 처음 방문이라면, 방문 처리
                visited[cur.node] = true;
                answer += cur.cost; // 총 비용 업데이트
            }
            
            for(Node next : list[cur.node]){ // 연결된 다음 노드 탐색
                if(!visited[next.node]){ // 처음 방문이라면, 우선순위 큐에 추가해주기
                    pq.offer(next);
                }
            }
        }
        
        return answer;
    }
    
    
    // Node 객체
    static class Node{
        int node;
        int cost;
        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }
    
}