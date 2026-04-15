/**
알고리즘 :
    bfs + 비트마스킹

문제 요약 :
    '.' : 깨끗한 칸 -> 이동 가능
    '*' : 더러운 칸 -> 청소해야야함 -> 깨끗한 칸으로 바꿈
    'x' : 가구 -> 이동 불가능한 벽
    'o' : 로봇청소기 시작 위치

전략 :
    1. while 돌면서 0 0 이 아니라면 반복 실행
    2. int[][] -> Integer 좌표 변환기 만들기 
    3. char[][] map 만들기
    4. map 매핑 과정중
        'o' or '*' 모두 큐에 담기
    5. 큐에서 하나씩 뽑아가면서 bfs 탐색으로 거리 구하기
    6. ArrayList<Node>[] list 만들기 : 현재 노드 -> n: 다음 노드, d: 거리
    7. TSP : 0부터 시작하며 비트마스킹 하면서 최단 거리 구하기
        1. dp 배열 생성
        2. 현재 마스킹(방문 경로) 에 다음 경로 포함
        3. 더 작은 값으로 갱신
        4. 모든 mask 검사
    8. 정답 출력 (없다면 "-1")
*/

import java.util.*;
import java.io.*;

public class Main{

    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if(h==0 || w==0) break; // 0인 경우 종료
            int number = 1; // 시작점의 number 는 0이라고 고정

            char[][] map = new char[h][w]; // 맵 만들기
            int[][] compression = new int[h][w]; // [][] -> Integer 컴프레션
            for(int i=0; i<h; i++){
                Arrays.fill(compression[i], -1); // 변환 필요없는 곳은 -1 고정
            }

            /**
            시작점 및 쓰레기 담는 큐
            [0] : 번호
            [1] : y좌표
            [2] : x좌표
            */
            ArrayDeque<int[]> q = new ArrayDeque<>(); 
            for(int i=0; i<h; i++){
                String input = br.readLine();                
                for(int j=0; j<w; j++){
                    map[i][j] = input.charAt(j);
                    if(map[i][j]=='o'){
                        q.offer(new int[]{0, i, j});
                        compression[i][j] = 0; // 0번 노드
                    }
                    else if(map[i][j]=='*'){
                        q.offer(new int[]{number, i, j});
                        compression[i][j] = number; // number 노드
                        number++;
                    }
                }
            }

            // 쓰레기가 없는 경우
            if(number == 1){
                System.out.println("0");
                continue;
            }

            // list에 거리 담기
            ArrayList<Node>[] list = new ArrayList[number];
            for(int i=0; i<number; i++){
                list[i] = new ArrayList<>(); // 배열 초기화
            }
            // 1개씩 빼면서 거리 계산하기
            while(!q.isEmpty()){
                boolean[][] visited = new boolean[h][w];
                ArrayDeque<int[]> bfsQ = new ArrayDeque<>();
                int[] cur = q.poll();  // 여기서 부터 거리를 구해야함
                int curNumber = cur[0];
                int curY = cur[1];
                int curX = cur[2];
                bfsQ.offer(new int[]{curY, curX, 0}); // [0]: y, [1]: x, [2]: 거리
                visited[curY][curX] = true;
                while(!bfsQ.isEmpty()){
                    int[] innerCur = bfsQ.poll();
                    int innerY = innerCur[0];
                    int innerX = innerCur[1];
                    int innerDist = innerCur[2];
                    for(int i=0; i<4; i++){
                        int nextY = innerY + dy[i];
                        int nextX = innerX + dx[i];
                        if(nextY<0 || nextX<0 || nextY>=h || nextX >=w ) continue; // 범위 밖 검사
                        if(map[nextY][nextX] == 'x') continue; // 가구라면 패스
                        
                        if(!visited[nextY][nextX]){
                            visited[nextY][nextX] = true; // 방문처리
                            bfsQ.offer(new int[]{nextY, nextX, innerDist+1}); // bfs 이동 큐에 추가
                            if( map[nextY][nextX] == '*' ){ // 주워야하는 먼지 발견했다면
                                int nextNumber = compression[nextY][nextX]; // int[][] -> Integer 변환
                                list[curNumber].add(new Node(nextNumber, innerDist+1)); // 단방향 거리 매핑
                            }
                        }
                    }
                } // Eed While bfs.Q
            } // End While q  // 거리 계산 끝            

            // TSP 알고리즘
            int[][] dp = new int[number][(1<<number)]; // []: 현재 위치, [][] : 주운 쓰레기
            for(int i=0; i<number; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][1<<0] = 0; // 0 위치 및 0 사용은 0 으로 초기화 (시작점)

            for(int mask=0; mask<(1<<number); mask++){
                for(int cur=0; cur<number; cur++){ // 현재 노드
                    if( (mask&(1<<cur)) == 0 ) continue; // 현재 노드 포함안되면 패스
                    if( dp[cur][mask] == Integer.MAX_VALUE ) continue; // 오버플로우 방지
                    
                    for(Node next : list[cur]){
                        int nextMask = (mask | (1<<next.n)); // 현재 경로에 다음 경로 포함하기
                        dp[next.n][nextMask] = Math.min(dp[next.n][nextMask], dp[cur][mask] + next.d);
                    }
                }
            }

            int answer = Integer.MAX_VALUE;
            for(int i=0; i<number; i++){
                answer = Math.min(answer, dp[i][(1<<number)-1]);
            }
            if(answer == Integer.MAX_VALUE){
                System.out.println("-1");
            } else {
                System.out.println(answer);    
            }
        } // TestCase 끝
    }

    // ArrayList에 넣을 타입
    static class Node{
        int n; // 노드
        int d; // 거리
        Node(int n, int d){
            this.n = n;
            this.d = d;
        }
    }    
}