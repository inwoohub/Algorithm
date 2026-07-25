import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[][] answer;
    static int[][] arr;  // 원본배열
    static int[][] dist; // 해당 자리의 범위 크기

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        // 1. 초기 값 입력 받기
        init();

        // 2. 답 찾기
        search();

        // 3. 정답 출력
        System.out.println(sb);

    }

    static void search() {
        // 1. 0의 영역의 크기 미리 구하기
        int index = 1;                           // 영역은 1번 index부터
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);                             // 도달 못하는 곳의 영역의 크기는 모두 1 , 0번 index
        boolean[][] visited = new boolean[N][M]; // 방문 배열 생성
        ArrayDeque<int[]> q = new ArrayDeque<>();// q에 이동 가능한 위치 전부 미리 넣기
        ArrayDeque<int[]> q2 = new ArrayDeque<>(); // 벽을 담는 큐
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(arr[i][j]==0){
                    q.offer(new int[]{i,j});
                } else {
                    q2.offer(new int[]{i,j});
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            if(visited[cy][cx]) continue; // 이미 영역이 정해졌으면 패스
            int cost = bfs(cy, cx, index, visited);
            list.add(cost); // 인덱스 : index, 넓이 : cost
            index++;        // 인덱스 증가
        }

        // 2. 벽 부시고 이동 가능한 크기 구하기
        while(!q2.isEmpty()){
            HashSet<Integer> set = new HashSet<>();
            int[] cur = q2.poll();
            int cy = cur[0];
            int cx = cur[1];
            set.add(dist[cy][cx]);
            int cost = list.get( dist[cy][cx] );

            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if(ny<0||nx<0||ny>=N||nx>=M) continue;   // 범위 밖
                if(arr[ny][nx] == 1) continue;           // 벽
                if( set.contains(dist[ny][nx]) ) continue; // 이미 합친 영역
                set.add(dist[ny][nx]);
                cost += list.get( dist[ny][nx] );
            }
            answer[cy][cx] = cost % 10;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(answer[i][j]);
            }
            sb.append("\n");
        }
    }

    static int bfs(int y, int x, int index, boolean[][] visited) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y,x});
        dist[y][x] = index;
        visited[y][x] = true;
        int cost = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0; i<4; i++){
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if(ny<0||nx<0||ny>=N||nx>=M) continue; // 범위 밖
                if(visited[ny][nx]) continue;          // 이미 방문
                if(arr[ny][nx] == 1) continue;         // 벽

                // 위에 3조건 다 해당하지 않아서 접근 가능 한 경우
                cost++;                 // cost 증가 (넓이)
                dist[ny][nx] = index;   // 영역 표기
                visited[ny][nx] = true; // 방문 처리
                q.offer(new int[]{ny, nx});
            }
        }
        return cost; // 비용 반환
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<int[]> q = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dist = new int[N][M];
        answer = new int[N][M];

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = input.charAt(j)-'0';
            }
        }
    }

}