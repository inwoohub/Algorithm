// 알고리즘
// 최단거리 찾기
// 현재 들어온 곳에 이전 값보다 크다면 버리는 방식으로 해서 최단 경로 업데이트 하기

import java.util.*;

class Solution {
    
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
            Arrays.fill(visited, -1);
            int cur = sources[i]; // cur: 부대원
            int result = search(cur, destination); // 탐색
            answer[i] = result;
        }
        
        return answer;
    }
    
    // 부대원 길 찾기 시작 , 있다면 최단경로 없다면 -1 로 return
    static int search(int i, int destination){
        
        // 시작점인경우
        if(i == destination){
            return 0;
        }
        
        // 방문 가능한 큐 만들기 int[0] : node, int[1] : cost
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a[1], b[1]) );
        pq.offer(new int[]{destination, 0});
        visited[destination] = 0;
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];
            for(int nextNode : graph[curNode]){
                if( visited[nextNode] == -1 ){
                    visited[nextNode] = curCost+1;
                    pq.offer(new int[]{nextNode, curCost+1});
                    if(nextNode == i){
                        return visited[i];
                    }
                }
            }
        }
        return visited[i];
    }
}