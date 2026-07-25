import java.io.*;
import java.util.*;

class Main{

    static int N, blackMax, whiteMax;
    static int[][] chess;

    static int[] dy = {-1,-1,1,1}; // 좌측상단/우측상단/좌측하단/우측하단
    static int[] dx = {-1,1,-1,1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        // 1. 체스판 크기 N, 체스판 입력 받기
        init();

        // 2. 서로가 서로를 잡을 수 없는 위치에 놓을 수 있는 비숍 최대 개수 구하기
        search();

        // 3. 정답 출력
        int answer = blackMax + whiteMax;
        System.out.println(answer);
    }

    // 비숍 최대 개수 구하기
    static void search() {
        blackMax = 0;
        whiteMax = 0;
        // 1. 미리 놓을 비숍을 놓을 수 있는 위치 탐색 후 ArrayList에 추가하기
        ArrayList<Node> listWhite = new ArrayList<>();
        ArrayList<Node> listBlack = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(chess[i][j]==1){
                    if( (i+j)%2 == 0){
                        listWhite.add(new Node(i,j)); // 화이트
                    } else {
                        listBlack.add(new Node(i,j)); // 블랙
                    }

                }
            }
        }

        // 2. 비숍의 흔적 배열
        int[][] visited = new int[N][N]; // 비숍의 감시 자리 카운팅 (백트래킹 오염 방지로 카운트)

        // 3. ArrayList 순회하며 dfs 탐색 시작
        dfs(0, listWhite, 0, visited, true);
        dfs(0, listBlack, 0, visited, false);
    }

    // 체스 dfs 탐색
    static void dfs(int index, ArrayList<Node> list, int count, int[][] visited, boolean whiteOrBlack) {
        if(index==list.size()) return; // dfs 끝까지 간 경우

        Node cur = list.get(index);

        // 1. 현재 비숍이 체스판에 놓을 수 있는지 검사
        boolean checkA = putOrNot( cur.y, cur.x, visited );

        // 2. 체스판에 놓을 수 있다면
        if(checkA) {
            // 2-1. 방문
            pathCheck(cur.y, cur.x, 0, visited, 1);           // 지나가는 경로 표기하기
            pathCheck(cur.y, cur.x, 1, visited, 1);           // 지나가는 경로 표기하기
            pathCheck(cur.y, cur.x, 2, visited, 1);           // 지나가는 경로 표기하기
            pathCheck(cur.y, cur.x, 3, visited, 1);           // 지나가는 경로 표기하기
            count++;                                          // 비숍 놓임 개수 증가

            // 2-2. 방문 성공 후 다음 탐색
            if(whiteOrBlack){
                whiteMax = Math.max(whiteMax, count);         // 정답 최대값 갱신
            } else {
                blackMax = Math.max(blackMax, count);         // 정답 최대값 갱신
            }

            dfs(index+1, list, count, visited, whiteOrBlack); // 놓인 상태로 다음 탐색

            // 2-3. 방문 해제
            pathCheck(cur.y, cur.x, 0, visited, -1);          // 지나가는 경로 해제하기
            pathCheck(cur.y, cur.x, 1, visited, -1);          // 지나가는 경로 해제하기
            pathCheck(cur.y, cur.x, 2, visited, -1);          // 지나가는 경로 해제하기
            pathCheck(cur.y, cur.x, 3, visited, -1);          // 지나가는 경로 해제하기
            count--;                                          // 비숍 놓임 개수 감소
        }
        // 3. 체스판에 안 두는 경우도 실행
        dfs(index+1, list, count, visited, whiteOrBlack);     // 놓지 않고 다음 탐색
    }

    static void pathCheck(int y, int x, int c, int[][] visited, int check) {
        while(true){
            if( y<0 || x<0 || y>=N || x>=N ) return; // 범위 밖 까지 도착한 경우
            visited[y][x] = visited[y][x] + check;
            y = y+dy[c];
            x = x+dx[c];
        }
    }

    // 체스를 놓을 수 있다면 true, 없다면 false 반환
    static boolean putOrNot(int y, int x, int[][] visited) {
        if(visited[y][x] > 0){
            return false;
        }
        return true;
    }

    // [init] 체스판 크기 N, 체스판 입력 받기
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        chess = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Node {
        int y;
        int x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

}