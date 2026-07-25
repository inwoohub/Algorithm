import java.util.*;
import java.io.*;

class Main {

    static int answer;
    static int N, M;
    static int[][] map;
    static int[][] visited;

    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1}; // 상/하/좌/우 순서

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main (String[] args) throws IOException {
        // 1. 초기 입력값 받기 (N, M, Map)
        init();

        // 2. 사각 지대 최소 크기 찾기
        search();

        // 3. 정답 출력
        System.out.println(answer);
    }

    static void search() {
        answer = Integer.MAX_VALUE;
        visited = new int[N][M]; // 방문 표기 (비트로 표현)

        // 2-1. 맵 순회하며 모든 CCTV를 리스트에 담기
        ArrayList<int[]> list = new ArrayList<>(); // [0]:y, [1]:x, [2]:CCTV.type
        cctvSearch(list);

        // 2-2. CCTV를 꺼내보며 모든 경우 탐색하며 최소 값 찾기 (DFS+백트래킹)
        dfs(0, list);
    }

    static void dfs(int index, ArrayList<int[]> list) {
        // 종료 조건 (모든 CCTV 전부 사용한 경우)
        if(index >= list.size()) {
            int count = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(visited[i][j] == 0){
                        if(map[i][j]==6) continue; // 벽 제외
                        count++;
                    }
                }
            }
            answer = Math.min(answer, count); // 최소값 갱신
            return;
        }

        // 현재 CCTV 꺼내기
        int[] cur = list.get(index);
        int cy = cur[0];
        int cx = cur[1];
        int type = cur[2];

        // 현 위치 색칠
        visited[cy][cx] += (1<<index);

        // CCTV 타입에 맞게 다르게 감시
        if(type == 1) {
            // 1. [상]
            fill(cy, cx, index, 0);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 0);

            // 2. [하]
            fill(cy, cx, index, 1);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 1);

            // 3. [좌]
            fill(cy, cx, index, 2);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 2);

            // 4. [우]
            fill(cy, cx, index, 3);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 3);
        }
        else if (type == 2){
            // 1. [상+하]
            fill(cy, cx, index, 0);
            fill(cy, cx, index, 1);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 0);
            fillCancel(cy, cx, index, 1);

            // 2. [좌+우]
            fill(cy, cx, index, 2);
            fill(cy, cx, index, 3);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 2);
            fillCancel(cy, cx, index, 3);
        }

        else if (type == 3) {
            // 1. [상+좌]
            fill(cy, cx, index, 0);
            fill(cy, cx, index, 2);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 0);
            fillCancel(cy, cx, index, 2);

            // 2. [상+우]
            fill(cy, cx, index, 0);
            fill(cy, cx, index, 3);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 0);
            fillCancel(cy, cx, index, 3);

            // 3. [하+좌]
            fill(cy, cx, index, 1);
            fill(cy, cx, index, 2);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 1);
            fillCancel(cy, cx, index, 2);

            // 4. [하+우]
            fill(cy, cx, index, 1);
            fill(cy, cx, index, 3);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 1);
            fillCancel(cy, cx, index, 3);
        }

        else if (type == 4) {
            // 1. [상+하+좌]
            fill(cy, cx, index, 0);
            fill(cy, cx, index, 1);
            fill(cy, cx, index, 2);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 0);
            fillCancel(cy, cx, index, 1);
            fillCancel(cy, cx, index, 2);

            // 2. [상+하+우]
            fill(cy, cx, index, 0);
            fill(cy, cx, index, 1);
            fill(cy, cx, index, 3);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 0);
            fillCancel(cy, cx, index, 1);
            fillCancel(cy, cx, index, 3);

            // 3. [좌+우+상]
            fill(cy, cx, index, 2);
            fill(cy, cx, index, 3);
            fill(cy, cx, index, 0);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 2);
            fillCancel(cy, cx, index, 3);
            fillCancel(cy, cx, index, 0);

            // 4. [좌+우+하]
            fill(cy, cx, index, 2);
            fill(cy, cx, index, 3);
            fill(cy, cx, index, 1);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 2);
            fillCancel(cy, cx, index, 3);
            fillCancel(cy, cx, index, 1);
        }

        else if (type == 5) {
            // 4. [상+하+좌+우]
            fill(cy, cx, index, 0);
            fill(cy, cx, index, 1);
            fill(cy, cx, index, 2);
            fill(cy, cx, index, 3);
            dfs(index+1, list);
            fillCancel(cy, cx, index, 0);
            fillCancel(cy, cx, index, 1);
            fillCancel(cy, cx, index, 2);
            fillCancel(cy, cx, index, 3);
        }

        // 현 위치 색칠 해제
        visited[cy][cx] -= (1<<index);
    }


    static void fill(int y, int x, int index, int type) {
        int ny = y + dy[type];
        int nx = x + dx[type];
        if(ny<0||nx<0||ny>=N||nx>=M) return; // 범위 밖 종료
        if(map[ny][nx] == 6) return;         // 벽 종료
        visited[ny][nx] += (1<<index);       // 방문 표기
        fill(ny, nx, index, type);           // 다음 방문
    }

    static void fillCancel(int y, int x, int index, int type) {
        int ny = y + dy[type];
        int nx = x + dx[type];
        if(ny<0||nx<0||ny>=N||nx>=M) return; // 범위 밖 종료
        if(map[ny][nx] == 6) return;         // 벽 종료
        visited[ny][nx] -= (1<<index);       // 방문 해제
        fillCancel(ny, nx, index, type);     // 다음 방문
    }

    static void cctvSearch(ArrayList<int[]> list) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(1 <= map[i][j] && map[i][j] <= 5) {
                    list.add(new int[]{i,j,map[i][j]});
                }
            }
        }
    }


    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}