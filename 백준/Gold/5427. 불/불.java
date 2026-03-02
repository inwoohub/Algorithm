// 알고리즘
// bfs

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int W, H; //가로 세로
    static char[][] graph; // 문자열 맵
    static int[][] fire; // 불이 지나간 거리 크기
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<int[]> fire_q; // 불 큐
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        // testCase 만큼 탐색 시작
        for(int tC=0; tC<testCase; tC++){

            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            int sanggeunX = 0;
            int sanggennY = 0;

            graph = new char[H][W];
            fire = new int[H][W];
            for(int i=0; i<H; i++){
                Arrays.fill(fire[i], -1); // -1: 불 닿지 않는 곳   
            }

            fire_q = new LinkedList<>(); // 불 초기 위치 큐

            // 맵 만들기
            for(int i=0; i<H; i++){
                String[] input = (br.readLine()).split("");
                for(int j=0; j<W; j++){
                    graph[i][j] = input[j].charAt(0);
                    if(graph[i][j] == '@'){ // 상근 위치 저장
                        sanggeunX = j;
                        sanggennY = i;
                    }
                    if(graph[i][j] == '*'){ // 불 위치 큐에 담기
                        fire_q.offer(new int[]{i,j,0});
                        fire[i][j] = 0;
                    }
                }
            }

            // 불 거리 계산
            fire_search();

            int ans = search(sanggeunX, sanggennY);

            if(ans == -1){
                sb.append("IMPOSSIBLE\n");
            } else {
                sb.append(ans+"\n");
            }

            // 디버깅
            // for(int i=0; i<H; i++){
            //     for(int j=0; j<W; j++){
            //         System.out.print(fire[i][j]+" ");
            //     }
            //     System.out.println(" ");
            // }
            
        } // End testCase

        // 데이터 출력
        System.out.print(sb);
        
    }

    // fire_search (불 지나간 거리 계산)
    static void fire_search(){
        
        while(!fire_q.isEmpty()){
            int[] cur = fire_q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int cDist = cur[2];
            if( fire[cy][cx] < cDist && fire[cy][cx]!=-1 ) continue; // 이전 값

            for(int i=0; i<4; i++){
                int ny = cy+dy[i];
                int nx = cx+dx[i];
                int nDist = cDist+1;

                // 범위 밖
                if( nx<0 || nx >= W || ny<0 || ny>=H ) continue;

                // 벽
                if ( graph[ny][nx] == '#' ) continue;

                // 첫 방문
                if( fire[ny][nx] == -1 ){
                    fire_q.offer(new int[]{ny,nx,nDist});
                    fire[ny][nx] = nDist;
                }

                // 두 번째 방문
                else if( fire[ny][nx] > nDist ){
                    fire_q.offer(new int[]{ny,nx,nDist});
                    fire[ny][nx] = nDist;
                }
                
            }
            
        } // End while
        
    } // End fire_search

    // search (불 탈출)
    // W: 가로, H: 세로, graph: 맵, sX: 상근X, sY: 상근Y
    static int search(int sX, int sY){

        // * 메모리 초과 발생! 상은 방문 확인 배열 추가
        boolean[][] visited = new boolean[H][W];
        
        Queue<int[]> q = new LinkedList<>(); // 상은 위치 큐
        q.offer(new int[]{sX, sY, 0});
        visited[sY][sX] = true;
            
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cDist = cur[2];

            for(int i=0; i<4; i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                int nDist = cDist+1;

                // 범위 밖이면 탈출 성공
                if( nx<0 || nx >= W || ny<0 || ny>=H ){
                    return nDist;
                }

                // 벽이면 이동 불가능
                if( graph[ny][nx] == '#' ) continue;

                if(visited[ny][nx]) continue;

                // 불 보다 빠르면 방문 가능
                if( fire[ny][nx] > nDist || fire[ny][nx] == -1 ){
                    q.offer(new int[]{nx, ny, nDist});
                    visited[ny][nx] = true;
                }
                
            } // End for

            
        } // End while

        // 방문 실패
        return -1;
        
    }
    
}