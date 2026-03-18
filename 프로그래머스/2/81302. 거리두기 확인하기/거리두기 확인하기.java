import java.util.*;

class Solution {
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] arr;
    static boolean[][] visited;
    
    public int[] solution(String[][] places) {
        
        int[] answer = new int[5];
        
        for(int tc=0; tc<5; tc++){
            
            // while 탈출용 boolean check
            boolean check = true;
            
            Queue<int[]> q = new LinkedList<>();  
            
            // places 데이터 꺼내기
            String[] input = places[tc];
            
            // char[][] 배열 생성 'P': 응시자, 'O': 빈테이블, 'X': 파티션
            arr = new char[5][5];
            
            // char[][] 채워 주기
            for(int j=0; j<5; j++){
                for(int k=0; k<5; k++){
                    arr[j][k] = (char) input[j].charAt(k);
                    if(arr[j][k] == 'P'){
                        q.offer(new int[]{j,k,0}); // 'P' 큐에 넣기
                    }
                }
            } // 사실상 데이터 매핑 끝
            
            while(!q.isEmpty()){
                visited = new boolean[5][5];
                int[] cur = q.poll();
                int cy = cur[0];
                int cx = cur[1];
                int dist = cur[2];
                visited[cy][cx] = true;
                check = dfs(cy, cx, 0);
                visited[cy][cx] = false;
                if(!check) break;
            }
            if(check){
                answer[tc] = 1;
            }
        } // End for
        return answer;
    }
    
    // dfs 탐색
    static boolean dfs(int y, int x, int dist){
        boolean check = true;
        
        if(dist == 2){
            return true;
        }
        
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny<0 || nx<0 || ny>=5 || nx>=5) continue;
            if(visited[ny][nx]) continue;
            // P 라면
            if(arr[ny][nx]=='P'){
                return false;
            }
            
            // O 라면 방문
            else if(arr[ny][nx] == 'O'){
                visited[ny][nx] = true;
                check = dfs(ny, nx, dist+1);
                visited[ny][nx] = false; // 백트래킹
                if(!check){
                    return false;
                }
            }
            
        }
        return check;
    }
}