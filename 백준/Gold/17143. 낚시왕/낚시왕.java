// --------------------
// 알고리즘
// 상어의 이름은 1~M 까지
// 상어를 어떻게 관리할까? -> 맵, int[][] 비어있으면 0, 있다면 해당 상어 이름 (1~N)
// 채워져있음 바로 대결 !
// 그러면, 어떻게 잡을까? 바로 for(문 돌려서 있으면 그거 오 ! 그거 좋다)
// --------------------
// 4 6 8
// 4 1 3 3 8
// 1 3 5 2 9
// 2 4 8 4 1
// 4 5 0 1 4
// 3 3 1 2 7
// 1 5 8 4 3
// 3 6 2 1 2
// 2 2 2 3 5
// --------------------
// 출력
// 잡은 상어 크기의 합 !
// --------------------

import java.io.*;
import java.util.*;

public class Main{

    // 상어
    static class Shark{
        int x; // x 좌표
        int y; // y 좌표
        int s; // 속력 (칸/초)
        int d; // 방향  (1,2,3,4) : (위,아래,오른쪽,왼쪽) 
        int z; // 크기
        
        Shark(int x, int y, int s, int d, int z){
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    // 방향 (상하우좌)
    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,1,-1};

    // 격자판 크기 & 상어 수
    static int R, C, M, ans;
    static int[][] graph;
    static int[][] visited;

    // 상어
    static Map<Integer, Shark> map = new HashMap<>();

    // 죽은 상어 표기
    static boolean[] dead;

    public static void main(String[] args) throws IOException{
        // ========입력=========
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;

        // 지형 생성
        graph = new int[R+1][C+1];
        visited = new int[R+1][C+1];

        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(x,y,s,d,z);
            map.put(i, shark); // 상어 map에 넣어둠
            visited[x][y] = i; // visited는 상어 이름 넣어주기
        }

        // 낚시 시작
        fising();
        
        // ========출력=========
        System.out.print(ans);
    }

    static void fising(){
        for(int i=1; i<=C; i++){ // 낚시꾼 한칸 씩 이동 ( 우측으로 )

            // 죽은 상어
            dead = new boolean[M+1];
            
            // 1. 같은 열에 상어 있는지 확인
            for(int j=1; j<=R; j++){
                if(visited[j][i]!=0){ // 상어가 있다면,
                    int idx = visited[j][i];
                    ans += map.get(idx).z;
                    map.remove(idx);
                    dead[idx] = true;
                    visited[j][i] = 0;
                    break;
                }
            }

            // 2. 먼저 바다 비워주기 ( 새로 들어온 친구들이랑 겹칠 수 있음 )
            for( int key : map.keySet() ){
                Shark s = map.get(key);
                visited[ s.x ][ s.y ] = 0;
            }

            // 3. 상어 이동하기 + 위치 새롭게 저장
            List<Integer> keys = new ArrayList<>(map.keySet());
            for (int key : keys) {
                if (dead[key]) continue;
                Shark s = map.get(key);
                if (s == null) continue; // 낚시로 잡힌 경우
                moveShark(s, key);
            }
        }
    }

    static void moveShark(Shark s, int key){
        int curX = s.x; 
        int curY = s.y;
        int curS = s.s;
        int curD = s.d;
        int curZ = s.z;
        
        // 1. 이동 하기
        // 1-1. 속도 최적화
        if (curD == 1 || curD == 2){
            curS %= (2 * (R - 1));    
        }
        else{
            curS %= (2 * (C - 1));    
        }
    
        // 1-2. 실제 이동 (curS 만큼)
        for(int i=0; i<curS; i++){
            int nx = curX + dx[curD];
            int ny = curY + dy[curD];
    
            // 벽이면 방향 반전 후 다시 한 칸
            if(nx < 1 || nx > R || ny < 1 || ny > C){
                if(curD==1) curD=2;
                else if(curD==2) curD=1;
                else if(curD==3) curD=4;
                else curD=3;
    
                nx = curX + dx[curD];
                ny = curY + dy[curD];
            }
    
            curX = nx;
            curY = ny;
        }
        
        s.x = curX;
        s.y = curY;
        s.d = curD;

        // 2. 아무도 없다면 위치 저장
        if( visited[curX][curY] == 0 ){
            visited[curX][curY] = key;
            map.put(key,s); // 위치 바꾼 상어 업데이트
        }

        // 3. 이미 상어가 있다면 대결
        else{
            Shark alreadyShark = map.get( visited[curX][curY] );
            if( alreadyShark.z >= curZ ){ // 기존꺼가 있다면, 지금은 제거
                map.remove(key);
                dead[key] = true;
            } else {
                int other = visited[curX][curY]; // 원래꺼가 더 작다면
                map.remove(other);
                dead[other] = true;
                visited[curX][curY] = key;
            }
        }   
    }
}