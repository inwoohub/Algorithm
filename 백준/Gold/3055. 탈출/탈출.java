// 알고리즘
// bfs (그래프)

// 'D' : 비버의 굴
// 'S' : 고슴도치의 위치
// '.' : 비어있는 곳
// '*' : 물이 차있는 지역
// 'X' : 돌

// 매 분마다 고슴도치는 인접한 4칸 이동 가능 (상,하,좌,우)
// 물도 매 분마다 비어있는 칸으로 확장 (상,하,좌,우)

// 조건1. 고슴 도치는 물로 차있는 구역 이동x , 물도 비버의 소굴 이동 x
// 조건2. 물이 찰 예정인 칸으로 이동 불가능


import java.io.*;
import java.util.*;

public class Main{

    static int h, w; // h:세로 , w: 가로
    static char[][] map;
    static int[][] waterMap;
    static boolean[][] visited; // 필요하나? 일단 냅둠
    static Queue<int[]> water_q;
    static int DX, DY; // 소굴 위치
    static int SX, SY; // 고슴도치 위치
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException{

        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        water_q = new LinkedList<>();
        waterMap = new int[h][w];

        // 1. waterMap 초기화
        for(int i=0; i<h; i++){
            Arrays.fill(waterMap[i], -1);
        }
        
        map = new char[h][w];
        for(int i=0; i<h; i++){
            String input = br.readLine();
            for(int j=0; j<w; j++){
                map[i][j] = input.charAt(j);

                // 소굴
                if(map[i][j] == 'D'){
                    DY = i;
                    DX = j;
                }

                // 고슴도치 시작 위치
                if(map[i][j] == 'S'){
                    SY = i;
                    SX = j;
                }

                // 물의 위치 (큐로 관리)
                if(map[i][j] == '*'){
                    water_q.offer(new int[]{i,j,0});
                    waterMap[i][j] = 0;
                }
            }
        } // End for



        // 2. waterMap 탐색
        search_W();

        // 3. 고슴도치 탐색
        search_S();
        
        
    }

    static void search_S(){

        int MIN = Integer.MAX_VALUE;
        visited = new boolean[h][w];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{SY, SX, 0});
        visited[SY][SX] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int count = cur[2];

            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                
                // 경로 검사
                if(nx<0 || ny<0 || nx>=w || ny>=h) continue;

                // 재방문
                if(visited[ny][nx]) continue;

                // 소굴 찾기 완료
                if(map[ny][nx] == 'D'){
                    System.out.println(count+1);
                    return;
                }
                
                // 돌임
                if(map[ny][nx] == 'X') continue; 

                // 물있음
                if(map[ny][nx] == '*') continue;

                // 이동 가능
                if(map[ny][nx] == '.'){
                    if(waterMap[ny][nx] == -1 || waterMap[ny][nx] > count+1){
                        q.offer(new int[]{ny,nx,count+1});
                        visited[ny][nx] = true;    
                    }
                }
            }
        }

        System.out.print("KAKTUS");
    }

    

    static void search_W(){
        while(!water_q.isEmpty()){
            int[] cur = water_q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int count = cur[2];

            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                
                // 경로 검사
                if(nx<0 || ny<0 || nx>=w || ny>=h) continue;

                // 최적화
                if(waterMap[ny][nx] <= count+1 && waterMap[ny][nx] != -1) continue;

                // 방문 가능한지 검사
                if(map[ny][nx] != 'X' && map[ny][nx] != 'D'){
                    // 방문 안했으면 무조건 추가
                    water_q.offer(new int[]{ny, nx, count+1});
                    waterMap[ny][nx] = count+1;
                }
            }
            
        } // End while
    } //End search

    
}