// 알고리즘
// 그래프

import java.util.*;

class Solution {    
    static class Node{
        int x;
        int y;
        int w;    // 현재 수 (거리)
        int d;    // 방향 (0,1,2,3) -> (우,하,좌,상) 순서
        Node(int x, int y, int w, int d){ // 생성자
            this.x = x;
            this.y = y;
            this.w = w;
            this.d = d;
        }
    }
    
    static int[][] answer; // 맵
    static int[] dx = {1, 0, -1, 0}; // 우 -> 하 -> 좌 -> 상 (나선형)
    static int[] dy = {0, 1, 0, -1};
    
    public int[][] solution(int n) {
        // 배열 생성
        answer = new int[n][n];
        
        // 초기값 세팅
        answer[0][0] = 1; // 시작점
        
        // n이 1이라면 바로 리턴
        if(n==1){
            return answer;
        }
        
        // 큐 생성
        Queue<Node> q = new LinkedList<>();
        
        // 큐 초기값 넣어주기
        q.offer(new Node(0,0,1,0));
        
        // 숫자 채우기 시작
        while(!q.isEmpty()){
            Node cur = q.poll();
            int ny = cur.y + dy[cur.d]; // 방향에 따른 다음길 체크
            int nx = cur.x + dx[cur.d];
            
            // 맵 범위가 벗어났다면 방향 틀어주기
            if(ny<0 || nx<0 || nx>=n || ny>=n){
                if(cur.d == 0) cur.d = 1;
                else if (cur.d == 1) cur.d = 2;
                else if (cur.d == 2) cur.d = 3;
                else cur.d = 0;   
                ny = cur.y + dy[cur.d];
                nx = cur.x + dx[cur.d]; // 바뀐 방향에 대해 업데이트 하기
            }
            
            // 다음 갈 곳이 이미 지나온 길이라도 방향 틀어주기
            if( answer[ny][nx] != 0 ){
                if(cur.d == 0) cur.d = 1;
                else if (cur.d == 1) cur.d = 2;
                else if (cur.d == 2) cur.d = 3;
                else cur.d = 0;   
                ny = cur.y + dy[cur.d];
                nx = cur.x + dx[cur.d]; // 바뀐 방향에 대해 업데이트 하기
            }
            
            // 방문 가능 하다면
            if(answer[ny][nx] == 0){
                answer[ny][nx] = cur.w + 1;
                q.offer(new Node(nx, ny, cur.w+1, cur.d));
            }
        }
        return answer;
    }
    
}