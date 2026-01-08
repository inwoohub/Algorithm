// -------------------------
// 알고리즘
// 해당 문제는 dfs+backtracking 로 푸려다가 구현 과정이 너무 어려워서 bfs로 전환
// bfs 장점 : count 를 이용하면, 가장 먼저 되는거 찾기 유용함 + 구현이 dfs보다 쉬움
// 1. 큐에 현재 빨간공, 파란공 위치, 횟수 저장
// 2. 방문하지 않고 이동 가능하면 한 방향으로 이동
// 3. 같은 위치라면 더 많이 이동한거 -1 칸
// 4. 탈출 성공 시 체크해두기, 빨간거만 탈출 성공시 바로 return
// -------------------------
// 주요 변수
// visited[][][][] : 빨간공, 파란공 위치 방문 표시
// graph : 경로
// -------------------------
// 테스트 Input
// 7 7
// #######
// #...RB#
// #.#####
// #.....#
// #####.#
// #O....#
// #######
// -------------------------
import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static char[][] graph;
    static boolean[][][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int ans = 11; // 결과 (초기값 11 세팅)
    
    static class Move {
        int x, y, dist;
        boolean hole;
        Move(int x, int y, int dist, boolean hole) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.hole = hole;
        }
    }

    static Move roll(int x, int y, int dir) {
        int dist = 0;
        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
    
            if (graph[nx][ny] == '#') break;  // 벽이면 멈춤
    
            x = nx;
            y = ny;
            dist++;
    
            if (graph[x][y] == 'O') {         // 구멍이면 즉시 종료
                return new Move(x, y, dist, true);
            }
        }
        return new Move(x, y, dist, false);
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int redX = 0;
        int redY = 0;
        int blueX = 0;
        int blueY = 0;
        graph = new char[N][M];
        visited = new boolean[N][M][N][M];
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                graph[i][j] = input.charAt(j);
                if(graph[i][j] == 'R'){
                    redX = i; // 빨간공 x
                    redY = j; // 빨간공 y
                    graph[i][j] ='.'; // 공은 보드에 없다고 가정하에 시뮬레이션만
                }
                if(graph[i][j] == 'B'){
                    blueX = i; // 파란공 x
                    blueY = j; // 파란공 y
                    graph[i][j] ='.';
                }
                sb.append(graph[i][j]+" ");
            }
            sb.append("\n");
        }
        visited[redX][redY][blueX][blueY] = true;
        bfs(redX, redY, blueX, blueY);
        if(ans==11){
            System.out.print(-1);
        } else {
            System.out.println(ans);    
        }
        
    }

    static void bfs(int x1, int y1, int x2, int y2){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x1,y1,x2,y2,0}); // 빨간공 넣기
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cx2 = cur[2];
            int cy2 = cur[3];
            int count = cur[4];
            if(count>=10) continue; //최대치 인 경우 다음 x
            
            for (int i = 0; i < 4; i++) {
                int nc = count + 1;
            
                // 1) 끝까지 굴리기
                Move r = roll(cx, cy, i);
                Move b = roll(cx2, cy2, i);
            
                // 2) 파랑이 구멍이면 실패
                if (b.hole) continue;
            
                // 3) 빨강만 구멍이면 성공
                if (r.hole) {
                    ans = nc;
                    return;
                }
            
                int nx = r.x;
                int ny = r.y;
                int nx2 = b.x;
                int ny2 = b.y;
            
                // 4) 겹치면 더 많이 움직인 구슬을 한 칸 뒤로
                if (nx == nx2 && ny == ny2) {
                    if (r.dist > b.dist) {
                        nx -= dx[i];
                        ny -= dy[i];
                    } else {
                        nx2 -= dx[i];
                        ny2 -= dy[i];
                    }
                }
            
                // 5) 방문 체크 후 큐 넣기
                if (!visited[nx][ny][nx2][ny2]) {
                    visited[nx][ny][nx2][ny2] = true;
                    q.offer(new int[]{nx, ny, nx2, ny2, nc});
                }
            }
            
        }
    }
}