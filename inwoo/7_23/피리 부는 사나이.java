import java.io.*;
import java.util.*;

class Main{

    static int N, M;
    static char[][] map;
    static int[] dy = {-1,1,0,0}; // 상/하/좌/우
    static int[] dx = {0,0,-1,1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. 초기 입력값 받기 (N, M, map)
        init();

        // 2. SAFE ZONE 탐색
        int answer = search();

        // 3. 정답 출력
        System.out.println(answer);
    }

    static int search() {
        int count = 0;
        boolean[][] visited = new boolean[N][M]; // 방문 표기용
        int[][] parent = new int[N][M];          // 이어진 지점용 (부모 없음 = 0)

        // 1. 전체 맵을 돌며 연결되어있는 공간 수 찾기 (SAFE ZONE 설치)
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                // 2. 방문하지 않은 곳이라면
                if(!visited[i][j]) {

                    // 순회하며 부모탐색
                    Node cur = searchParent(i,j,visited,parent);

                    if(cur.check){ // 부모도 첫 방문인 경우
                        count++;
                        bfs(i,j,parent,count);      // 부모까지 영역 표기
                    }

                    else{
                        bfs(i,j,parent,cur.parent); // 부모의 영역으로 표기
                    }
                }
            }
        }
        return count;
    }

    static void bfs(int y, int x, int[][] parent, int count) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y,x});
        parent[y][x] = count;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            char dir = map[cy][cx];

            if(dir=='U'){
                int ny = cy + dy[0];
                int nx = cx + dx[0];
                if(parent[ny][nx]==count){
                    return;
                }
                q.offer(new int[]{ny,nx});
                parent[ny][nx]=count;
            } else if (dir=='D'){
                int ny = cy + dy[1];
                int nx = cx + dx[1];
                if(parent[ny][nx]==count){
                    return;
                }
                q.offer(new int[]{ny,nx});
                parent[ny][nx]=count;
            } else if (dir=='L'){
                int ny = cy + dy[2];
                int nx = cx + dx[2];
                if(parent[ny][nx]==count){
                    return;
                }
                q.offer(new int[]{ny,nx});
                parent[ny][nx]=count;
            } else if (dir=='R'){
                int ny = cy + dy[3];
                int nx = cx + dx[3];
                if(parent[ny][nx]==count){
                    return;
                }
                q.offer(new int[]{ny,nx});
                parent[ny][nx]=count;
            }
        }
    }


    static Node searchParent(int y, int x, boolean[][] visited, int[][] parent) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y,x});
        visited[y][x] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            char dir = map[cy][cx];

            if(dir == 'U') {
                int ny = cy + dy[0];
                int nx = cx + dx[0];
                if(visited[ny][nx]){
                    if(parent[ny][nx]==0){
                        return new Node(0, true);
                    } else {
                        return new Node(parent[ny][nx], false);
                    }
                } else {
                    q.offer(new int[]{ny,nx});
                    visited[ny][nx] = true;
                }

            } else if (dir == 'D') {
                int ny = cy + dy[1];
                int nx = cx + dx[1];
                if(visited[ny][nx]){
                    if(parent[ny][nx]==0){
                        return new Node(0, true);
                    } else {
                        return new Node(parent[ny][nx], false);
                    }
                } else {
                    q.offer(new int[]{ny,nx});
                    visited[ny][nx] = true;
                }

            } else if (dir == 'L') {
                int ny = cy + dy[2];
                int nx = cx + dx[2];
                if(visited[ny][nx]){
                    if(parent[ny][nx]==0){
                        return new Node(0, true);
                    } else {
                        return new Node(parent[ny][nx], false);
                    }
                } else {
                    q.offer(new int[]{ny,nx});
                    visited[ny][nx] = true;
                }
            } else if (dir == 'R') {
                int ny = cy + dy[3];
                int nx = cx + dx[3];
                if(visited[ny][nx]){
                    if(parent[ny][nx]==0){
                        return new Node(0, true);
                    } else {
                        return new Node(parent[ny][nx], false);
                    }
                } else {
                    q.offer(new int[]{ny,nx});
                    visited[ny][nx] = true;
                }
            }

        }
        return null;
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = input.charAt(j);
            }
        }
    }

    static class Node {
        int parent;
        boolean check;
        Node (int parent, boolean check) {
            this.parent = parent;
            this.check = check;
        }
    }

}