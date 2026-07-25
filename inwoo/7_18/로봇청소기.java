import java.io.*;
import java.util.*;

class Main{

    static final int[] dy = {-1,1,0,0};
    static final int[] dx = {0,0,-1,1};
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        // 1. 최단 동선 청소 시작
        clean();

        // 2. 정답 출력
        System.out.println(sb);

    }

    static void clean() throws IOException {
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) return;  // 프로그램 종료
            int rx = 0; int ry = 0;                         // 로봇 초기 위치
            char[][] map = new char[N][M];                  // 지도 원본
            HashMap<String, Integer> hashMap = new HashMap<>(); // 위치(string) -> node 변환
            ArrayDeque<int[]> q = new ArrayDeque<>();       // 쓰레기 위치 큐
            int count = 1;

            // 1. Map 만들기 & 쓰레기,로봇 위치 큐에 넣기
            for(int i=0; i<N; i++){
                String input = br.readLine();
                for(int j=0; j<M; j++){
                    map[i][j] = input.charAt(j);
                    if(map[i][j]=='*') {         // 쓰레기 위치 큐에 저장
                        q.offer(new int[]{i,j,count});
                        hashMap.put((String.valueOf(i)+","+String.valueOf(j)),count);
                        count++;
                    }
                    if(map[i][j]=='o'){          // 로봇 위치 저장
                        ry = i;
                        rx = j;
                    }
                }
            }

            // 2. 큐에서 꺼낸 후 방문 가능 및 거리 구하기
            ArrayList<Node>[] list = new ArrayList[count];
            for(int i=0; i<count; i++){
                list[i] = new ArrayList<>();
            }
            bfs(ry, rx, map, list, 0, N, M, hashMap); // list[0] 는 로봇청소기 -> 쓰레기 노드 담기
            for(Map.Entry<String,Integer> entry : hashMap.entrySet()){
                String[] yx = entry.getKey().split(",");
                int y = Integer.parseInt(yx[0]);
                int x = Integer.parseInt(yx[1]);
                int value = entry.getValue();
                bfs(y, x, map, list, value, N, M, hashMap);
            }

            // 3. 최단 거리 탐색 (비트마스킹)
            search(ry, rx, list, count);
        }
    }

    static void search(int ry, int rx, ArrayList<Node>[] list, int size) {
        int[][] dp = new int[size][1<<size]; //[] : 현재 위치, [][] : 이때까지 지나온 경로
        for(int i=0; i<size; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][1<<0] = 0; // 시작점 (로봇의 처음 위치)
        for(int mask=0; mask<(1<<size); mask++){

            for(int cur=0; cur<size; cur++){
                // 현재 경로에 i 포함 안되어있으면 패스
                if( (mask & (1<<cur)) == 0 ) continue;
                if(dp[cur][mask]==Integer.MAX_VALUE) continue; // 오버플로 방지

                for(Node next : list[cur]) {
                    if( (mask & (1<<next.node)) != 0 ) continue; // 다음 노드 이미 방문한 경우 패스
                    int nextMask = (mask|(1<<next.node));
                    dp[next.node][nextMask] = Math.min(dp[next.node][nextMask], dp[cur][mask]+next.dist);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<size; i++){
            answer = Math.min(answer, dp[i][(1<<size)-1]);
        }
        if(answer != Integer.MAX_VALUE){
            sb.append(answer);
        } else {
            sb.append("-1");
        }
        sb.append("\n");
    }

    static void bfs(int y, int x, char[][] map, ArrayList<Node>[] list, int index, int N, int M, HashMap<String, Integer> hashMap) {
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y,x,0});
        visited[y][x] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int cost = cur[2]; // 거리 비용
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                int nextCost = cost + 1;
                if( ny<0 || nx<0 || ny>=N || nx>=M ) continue;     // 맵 밖
                if(visited[ny][nx]) continue;                      // 재방문
                if(map[ny][nx] == 'x') continue;                   // 가구
                if(map[ny][nx] == '*'){                            // 쓰레기인 경우 list[index]에 추가!
                    int nextNode = hashMap.get( String.valueOf(ny)+","+String.valueOf(nx) );
                    list[index].add(new Node(nextNode, nextCost));
                }
                q.offer(new int[]{ny,nx,nextCost});
                visited[ny][nx] = true;
            }
        }
    }

    static class Node{
        int node;
        int dist;
        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

}