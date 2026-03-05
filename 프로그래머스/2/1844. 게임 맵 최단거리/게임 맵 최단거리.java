import java.util.*;

class Solution {
    
    static class Node{
        int x;
        int y; 
        int count; // 횟수
        Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        
        // 데이터 초기 값
        int answer = -1;
        
        // maps 크기 뽑아내기
        int h = maps.length;    // 세로
        int w = maps[0].length; // 가로
        
        // 방문 처리 배열
        visited = new boolean[h][w];
        
        // 큐 생성
        Queue<Node> q = new LinkedList<>();
        // 초기값 세팅
        q.offer(new Node(0,0,1));
        // 방문 처리
        visited[0][0] = true;
        
        // bfs 탐색
        while(!q.isEmpty()){
            Node cur = q.poll();
            // 도착 시 종료
            if(cur.x == w-1 && cur.y == h-1){
                answer = cur.count;
                break;
            }
            
            // 도착 x
            for(int i=0; i<4; i++){
                int ny = dy[i] + cur.y;
                int nx = dx[i] + cur.x;
                
                // 범위 밖
                if( ny<0 || nx<0 || nx>=w || ny>=h ) continue;
                
                // 벽
                if(maps[ny][nx] == 0) continue;
                
                // 미 방문시
                if(!visited[ny][nx]){
                    // 큐 추가
                    q.offer(new Node(nx, ny, cur.count+1));
                    // 방문 처리
                    visited[ny][nx] = true;
                }    
            } 
            
        } // End while(!q)
        
        // 데이터 반환 값
        return answer;
    }
}