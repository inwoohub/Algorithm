/**
알고리즘 :
    백트래킹

문제 요약 :
    CCTV 는 1 ~ 5 종류
    1 : 한 쪽 방향 (1)
    2 : 서로 반대 방향 (2)
    3 : 직각 방향 (2)
    4 : 세 방향 (3)
    5 : 네 방향 (4)

    * 0 , CCTV는 : 통과 가능
    * 6 : 벽은 통과 x
    * 1 <= N, M <= 8
    * CCTV < 9
    
    return -> CCTV 최소 크기 출력하기

전력 : 
    1. map 만들기
    2. CCTV는 ArrayList 에 별도고 관리하기
    3. ArrayList 에서 1개씩 꺼내가면서 길 체크하기
    4. dfs -> 다음 CCTV로 길체크 (가능한 방향으로)
    5. 전부 다 썼다면, 사각지대 크기 구하기
    6. 빠져나오면서 방문 처리 해제하기 (백트래킹)
        -> 근데 다른 애들이 건드리고 있으면 건드리면 안됨 (이거 int 로 처리하자)
        -> bit 로 하려다가 bit 로 하면 중복 처리가 어려워짐
*/

import java.io.*;
import java.util.*;

public class Main{

    static ArrayList<int[]> list; // CCTV 리스트
    static int[][] visited; // 방문 처리용 배열
    static int[][] map; // 맵
    static int N, M, answer;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1}; // 상하좌우 순서
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1, 2 : map 만들고 CCTV 리스트에 담아두기
        list = new ArrayList<>();
        map = new int[N][M];
        visited = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int A = Integer.parseInt(st.nextToken());
                map[i][j] = A;
                if( 1<=A && A<=5){ // CCTV 인 경우 리스트에 담기
                    list.add(new int[]{i,j});
                    visited[i][j] = A;
                }
                else if(A == 6){
                    visited[i][j] = A;
                }
            }
        }
        
        // 3. CCTV 앞에서 부터 꺼내보며 dfs 탐색
        answer = Integer.MAX_VALUE;
        dfs(0);

        // 결과 출력
        System.out.println(answer);
    }

    // dfs 탐색
    static void dfs(int cctv){
    
        // 종료 조건
        if(cctv == list.size()){
            answer = Math.min( answer, searchMin() );
            return;
        }
        
        int[] cur = list.get(cctv);
        int number = search(cur);
        if(number == 1){
            for(int i=0; i<4; i++){
                // 방문 처리
                visitCheck(i, cur, 1, true);
                dfs(cctv+1);
                // 방문 해제
                visitCheck(i, cur, 1, false);
            }
        }

        else if (number == 2){
            for(int i=0; i<2; i++){
                int A = i*2;
                // 방문 처리
                visitCheck(A, cur, 2, true);
                visitCheck(A+1, cur, 2, true);
                dfs(cctv+1);
                // 방문 해제
                visitCheck(A, cur, 2, false);
                visitCheck(A+1, cur, 2, false);
            }
        }

        else if (number == 3){
            // 방문 처리
            visitCheck(0, cur, 3, true);
            visitCheck(2, cur, 3, true);
            dfs(cctv+1);
            // 방문 해제
            visitCheck(0, cur, 3, false);
            visitCheck(2, cur, 3, false);

            // 방문 처리
            visitCheck(0, cur, 3, true);
            visitCheck(3, cur, 3, true);
            dfs(cctv+1);
            // 방문 해제
            visitCheck(0, cur, 3, false);
            visitCheck(3, cur, 3, false);

            // 방문 처리
            visitCheck(1, cur, 3, true);
            visitCheck(2, cur, 3, true);
            dfs(cctv+1);
            // 방문 해제
            visitCheck(1, cur, 3, false);
            visitCheck(2, cur, 3, false);

            // 방문 처리
            visitCheck(1, cur, 3, true);
            visitCheck(3, cur, 3, true);
            dfs(cctv+1);
            // 방문 해제
            visitCheck(1, cur, 3, false);
            visitCheck(3, cur, 3, false);
        }


        else if (number == 4){
            for(int i=0; i<4; i++){
                int A = (i+1)%4;
                int B = (i+2)%4;
                int C = (i+3)%4;
                
                // 방문 처리
                visitCheck(A, cur, 4, true);
                visitCheck(B, cur, 4, true);
                visitCheck(C, cur, 4, true);
                dfs(cctv+1);
                // 방문 해제
                visitCheck(A, cur, 4, false);
                visitCheck(B, cur, 4, false);
                visitCheck(C, cur, 4, false);
            }
        }

        else {
            // 방문 처리
            visitCheck(0, cur, 5, true);
            visitCheck(1, cur, 5, true);
            visitCheck(2, cur, 5, true);
            visitCheck(3, cur, 5, true);
            dfs(cctv+1);
            // 방문 해제
            visitCheck(0, cur, 5, false);
            visitCheck(1, cur, 5, false);
            visitCheck(2, cur, 5, false);
            visitCheck(3, cur, 5, false);
        }
    }

    // 방문 처리
    // dir : 방향
    // cur : 현재 위치
    // value : 카메라 Value
    // check : 방문 or 방문 해제
    static void visitCheck(int dir, int[] cur, int value, boolean check){
        int ny = cur[0];
        int nx = cur[1];
        while(true){
            ny = ny + dy[dir];
            nx = nx + dx[dir];
            if(ny<0 || nx<0 || ny >= N || nx >= M) return; // 맵 밖
            if( map[ny][nx] == 6 ) return; // 다음은 벽
            if(check){
                visited[ny][nx] = visited[ny][nx] + value; // 방문 처리
            }
            else {
                visited[ny][nx] = visited[ny][nx] - value; // 방문 해제
            }
        }
    }

    // 방문 체크하면서 사각지대 찾기
    static int searchMin(){
        int count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j] == 0){
                    count++;
                }
            }
        }    
        return count;
    }

    // 현재 카메라 번호 반환
    static int search(int[] cur){
        int A = cur[0];
        int B = cur[1];
        if(map[A][B] == 1){
            return 1;   
        }
        else if(map[A][B] == 2){
            return 2;   
        }
        else if(map[A][B] == 3){
            return 3;   
        }
        else if(map[A][B] == 4){
            return 4;   
        }
        return 5;
    }
    
}