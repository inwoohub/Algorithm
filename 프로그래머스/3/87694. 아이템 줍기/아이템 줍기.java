// 알고리즘
// bfs

import java.util.*;

class Solution {
    
    static boolean[][] inner;   // 사각형 내부 표기
    static boolean[][] radius;  // 사각형 둘레 표기
    static boolean[][] visited; // 방문했던 곳 표기
    static boolean[][][][] status; // 연결 정보 저장 T: 연결, F: 연결 안되어있음
    static boolean[][][][] innerStatus; // 내부로 연결
    
    static int[][] logging; // 디버깅용도
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        inner = new boolean[102][102];
        radius = new boolean[102][102];
        visited = new boolean[102][102];
        status = new boolean[102][102][102][102];
        innerStatus = new boolean[102][102][102][102];
        // logging = new int[11][11];
        
        
        // 사각형 꺼내보면서 표기하기
        for(int i=0; i<rectangle.length; i++){
            int[] rtg = rectangle[i];
            int x1 = rtg[0];  // x1
            int y1 = rtg[1];  // y1
            int x2 = rtg[2];  // x2
            int y2 = rtg[3];  // y2
            
            // 둘레 표기하기
            radiusSearch(x1,y1,x2,y2);
            
            // 내부 표기하기
            innerSearch(x1,y1,x2,y2);   
        }
        
        
        int answer = bfs(characterX, characterY, itemX, itemY); // bfs 로 둘레 탐색
        
        return answer;
    
    }
    
    // bfs 탐색
    static int bfs(int cX, int cY, int iX, int iY){
        // bfs 시작 초기 세팅
        Queue<int[]> q = new LinkedList<>(); // 큐 생성
        q.offer(new int[]{cY, cX, 0}); // 캐릭터 시작점 출발 [0]: y, [1]: x , [2]: 거리
        visited[cY][cX] = true; // 방문처리
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int dist = cur[2];
            for(int i=0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if( ny < 0 || nx < 0 || nx > 101 || ny > 101 ) continue; // 맵 밖 검사
                if(visited[ny][nx]) continue; // 지나온 곳 검사
                if(inner[ny][nx]) continue; // 내부라면 접근 x
                if(!radius[ny][nx]) continue; // 둘레가 아니라면 접근 x
                
                if(!status[y][x][ny][nx]) continue; // 연결되어있지않음
                
                if(innerStatus[y][x][ny][nx]) continue; // 내부로 연결되어있음
                
                // 이제부터 접근 가능함!
                q.offer(new int[]{ny,nx,dist+1});
                visited[ny][nx] = true;
                // logging[ny][nx] = dist+1;
                if(ny == iY && nx == iX){ // 만약 아이템이 있다면,
                    return dist+1;
                }
                
            }
            
        }
        
        return -1;
        
    }
    
    // 둘레 표기하기
    static void radiusSearch(int x1, int y1, int x2, int y2){
        
        // 편의상 (낮은x, 낮은y) ~ (높은x, 높은y) 로 변환
        int A = Math.min(x1,x2); // A: 낮 x
        int B = Math.min(y1,y2); // B: 낮 y
        int C = Math.max(x1,x2); // C: 높 x
        int D = Math.max(y1,y2); // D: 높 y
        
        radius[B][A] = true;
        radius[D][A] = true;
        
        for(int i=A+1; i<=C; i++){
            radius[B][i] = true; // 아래 변
            radius[D][i] = true; // 윗 변
            
            // 양방향 매핑 (연결 상태)
            status[B][i-1][B][i] = true;
            status[B][i][B][i-1] = true;
            
            status[D][i-1][D][i] = true;
            status[D][i][D][i-1] = true;
        }
        
        
        radius[B][A] = true;
        radius[B][C] = true;
        for(int i=B+1; i<=D; i++){
            radius[i][A] = true; // 좌측 변
            radius[i][C] = true; // 우측 변
            
            // 양방향 매핑 (연결 상태)
            status[i][A][i-1][A] = true;
            status[i-1][A][i][A] = true;
            
            status[i][C][i-1][C] = true;
            status[i-1][C][i][C] = true;
        }
        
        
        
    }
    
    
    // 내부 표기하기
    static void innerSearch(int x1, int y1, int x2, int y2){
        
        boolean[][] visit = new boolean[51][51];
        
        // 편의상 (낮은x, 낮은y) ~ (높은x, 높은y) 로 변환
        int A = Math.min(x1,x2); // A: 낮 x
        int B = Math.min(y1,y2); // B: 낮 y
        int C = Math.max(x1,x2); // C: 높 x
        int D = Math.max(y1,y2); // D: 높 y
        
        for(int i=B+1; i<D; i++){
            for(int j=A+1; j<C; j++){
                inner[i][j] = true; // 내부 색칠
            }
        }
        
        A = Math.min(x1,x2)+1; // A: 낮 x
        B = Math.min(y1,y2)+1; // B: 낮 y
        C = Math.max(x1,x2)-1; // C: 높 x
        D = Math.max(y1,y2)-1; // D: 높 y
        
        // (A,B) ~ (C,D) 내부 전부 연결 처리하기
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{B,A});
        visit[B][A] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if( ny < B || nx < A || ny > D || nx > C ) continue; // 경로 밖 검사
                innerStatus[ny][nx][cy][cx] = true;
                innerStatus[cy][cx][ny][nx] = true; // 양방향 매핑 (내부로 연결되어있음)
                visit[ny][nx] = true;
                
                if(!visit[ny][nx]){
                    q.offer(new int[]{ny, nx}); // 방문 안한 곳만 추가해주기
                }
            }
        }
        
        // x가 1칸 밖에 차이가 안나는 경우
        A = Math.min(x1,x2); // A: 낮 x
        B = Math.min(y1,y2)+1; // B: 낮 y
        C = Math.max(x1,x2); // C: 높 x
        D = Math.max(y1,y2)-1; // D: 높 y
        if( Math.abs(A-C) == 1){
            for(int i=B; i<=D; i++){
                innerStatus[i][A][i][C] = true;
                innerStatus[i][C][i][A] = true;
            }
        }
        
        // y가 1칸 밖에 차이가 안나는 경우
        A = Math.min(x1,x2)+1; // A: 낮 x
        B = Math.min(y1,y2); // B: 낮 y
        C = Math.max(x1,x2)-1; // C: 높 x
        D = Math.max(y1,y2); // D: 높 y
        if( Math.abs(B-D) == 1){
            for(int i=A; i<=C; i++){
                innerStatus[B][i][D][i] = true;
                innerStatus[D][i][B][i] = true;
            }
        }
        
    }

    
    
}