// 알고리즘
// 최단거리 찾기
// 현재 들어온 곳에 이전 값보다 크다면 버리는 방식으로 해서 최단 경로 업데이트 하기

import java.util.*;

class Solution {
    
    static final int MAX = Integer.MAX_VALUE;
    static ArrayList<Integer>[] graph;
    static int[] visited;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        graph = new ArrayList[n+1]; // 길 만들기
        
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>(); // 길 배열 초기화
        }
        
        // 길 연결해주기
        for(int i=0; i<roads.length; i++){
            int[] cur = roads[i];
            graph[cur[1]].add(cur[0]);
            graph[cur[0]].add(cur[1]); // 양방향 매핑하기
        }
        
        // 부대원 길 찾아주기 시작
        int[] answer = new int[sources.length];
        for(int i=0; i<sources.length; i++){
            visited = new int[n+1]; // 거리 초기화
            Arrays.fill(visited, MAX);
            int cur = sources[i]; // cur 부대원
            int result = search(cur, destination); // 탐색
            answer[i] = result;
        }
        
        
        
        return answer;
    }
    
    // 부대원 길 찾기 시작 , 있다면 최단경로 없다면 -1 로 return
    static int search(int i, int destination){
        
        // 방문 가능한 큐 만들기
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(i, 0));
        
        // 큐가 비어있을 때까지 반복
        while(!q.isEmpty()){
            Node cur = q.poll();
            // 첫번째가 아니고, 이전 cost 가 더 높다면 버리기
            if(visited[cur.node] <= cur.cost) continue;
            visited[cur.node] = cur.cost; // 거리 값 업데이트    
            
            for(int next : graph[cur.node]){
                if(visited[next] > cur.cost+1){
                    q.offer(new Node(next, cur.cost+1)); // 다음 방문 노드, cost + 1
                    if(next == destination){ // 발견 시 바로 return
                        return cur.cost+1;
                    }
                }
            }   
        }
        
        if(visited[destination] != MAX ){
            return visited[destination];
        }
        return -1;
    }
    
    static class Node{
        int node;
        int cost;
        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }
    
    
    
    
    
    
    
}