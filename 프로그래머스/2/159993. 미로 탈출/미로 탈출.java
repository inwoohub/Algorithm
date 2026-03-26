// 알고리즘
// bfs

import java.util.*;

class Solution {
    
    static char[][] map; // 맵
    static boolean[][] visited; //  방문 배열
    static int SY, SX; // 시작점
    static int LY, LX; // 레버 위치
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public int solution(String[] maps) {
        
        // map & 방문 배열 생성
        map = new char[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        
        // maps -> map 으로 매핑
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length(); j++){
                map[i][j] = maps[i].charAt(j);
                if(map[i][j]=='S'){ // 시작점
                SY = i;
                SX = j;
                } else if (map[i][j]=='L'){ // 레버
                    LY = i;
                    LX = j;
                }
            }
        }
        
        int lever = bfs_L(); // 레버 찾기 시작
        if(lever==-1){
            return -1;
        }
        
        visited = new boolean[maps.length][maps[0].length()]; // 방문 배열 초기화하기
        return  bfs_E(lever); // 목적지 탐색
    }
    
    // 목적지 찾기 | lever: 레버 탐색까지 걸린 시간
    static int bfs_E(int lever){
        ArrayDeque<int[]> q = new ArrayDeque<>(); // LinkedList인 큐보다 ArrayDeque 가 성능이 더 좋음 왜? 배열이라서 메모리구조에서 붙어있음
        q.offer(new int[]{LY, LX, lever});
        visited[LY][LX] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int count = cur[2];
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if( ny<0 || nx<0 || ny>=visited.length || nx>= visited[0].length ) continue; // 맵 범위 밖 초과
                if(map[ny][nx] == 'X') continue; // 못 가는 곳
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny,nx,count+1});
                    if(map[ny][nx]=='E') return count+1; // 목적지 찾으면 바로 반환
                }   
            }
        }
        return -1;
    }
    
    // 레버 찾기
    static int bfs_L(){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{SY, SX, 0});
        visited[SY][SX] = true;
        
        // 큐가 빌 때 까지 레버 탐색
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int count = cur[2];
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if( ny<0 || nx<0 || ny>=visited.length || nx>= visited[0].length ) continue; // 맵 범위 밖 초과
                if(map[ny][nx] == 'X') continue; // 못 가는 곳
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny,nx,count+1});
                    if(map[ny][nx]=='L') return count+1; // 레버 찾으면 즉시 반환
                }   
            }
        }
        return -1;
    }
    
    
}