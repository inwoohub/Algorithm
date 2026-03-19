// 알고리즘
// bfs?

import java.util.*;

class Solution {

    static ArrayList<Integer>[] list; // 그래프
    static boolean[] visited; // 방문 배열

    public int solution(int n, int[][] edge) {
        
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edge.length; i++){
            int[] cur = edge[i];
            int a = cur[0];
            int b = cur[1];
            list[a].add(b); list[b].add(a); // 양방향 매핑
        }
        
        // bfs
        int answer = bfs();
        return answer;
    }
    
    static int bfs(){
        int count = 0;
        int maxDist = 0;
        
        Queue<int[]> q = new LinkedList<>(); // [0]: 현재 노드, [1]: 거리
        q.offer(new int[]{1,0});
        visited[1] = true; // 방문 처리
        
        // bfs 탐색시작
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            for(int nextNode : list[curNode] ){
                int nextDist = curDist+1;
                // 방문전
                if(!visited[nextNode]){
                    q.offer(new int[]{nextNode, nextDist});
                    visited[nextNode] = true; //방문 처리
                    
                    if(maxDist == nextDist){ // 최대 거리라면 count 증가
                        count++;
                    }
                    else if(maxDist < nextDist){ // 최대값 갱신
                        maxDist = nextDist;
                        count = 1;
                    }
                }
            }
        }
        // count 만 반환
        return count;
    }
    
}