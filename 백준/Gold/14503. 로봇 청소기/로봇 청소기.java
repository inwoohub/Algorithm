// ===================
// 알고리즘
// bfs + 후진(?)
// ===================
// 3 3
// 1 1 0
// 1 1 1
// 1 0 1
// 1 1 1
// ===================
// 1
// ===================

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();

    static int N, M; // N:세로, M:가로
    static int[][] graph;
    static boolean[][] visited; // 방문

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken()); // 0:북, 1:동, 2:남, 3:서
        graph = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 데이터 입력 끝

        // 탐색
        search(startX, startY, direction);

        // 데이터 출력
        System.out.println(sb);
    }

    static void search(int startX, int startY, int direction){        

        int count = 0;
        int curX = startX;
        int curY = startY;
        int curDirection = direction;
        
        while(true){

            // 1) 현재 칸 청소
            if ( graph[curY][curX] == 0 && !visited[curY][curX] ){
                visited[curY][curX] = true; // 청소 처리
                count++;
            }

            // 2) 주변 4칸 탐색
            boolean check = false; // 청소 가능 여부 T/F
            for(int i=0; i<4; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1) continue;
                if(graph[nextY][nextX]==0 && !visited[nextY][nextX]){ // 방문 가능한 경우 존재
                    check = true; // 청소 가능
                }
            }

            // 3) 방문 경로 없을 때
            if(!check){
                // 3-1) 후진 가능 확인
                if(curDirection == 0 ){
                    int nextX = curX;
                    int nextY = curY+1;
                    if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1){
                        sb.append(count);
                        return; // 종료
                    }
                    if(graph[nextY][nextX] == 1){
                        sb.append(count);
                        return; // 종료
                    }
                    curX = nextX; 
                    curY = nextY;
                    continue; // 1로 되돌아가기
                    
                } else if(curDirection == 1 ){
                    int nextX = curX-1;
                    int nextY = curY;
                    if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1){
                        sb.append(count);
                        return;
                    }
                    if(graph[nextY][nextX] == 1){
                        sb.append(count);
                        return;
                    }
                    curX = nextX;
                    curY = nextY;
                    continue;
                    
                } else if(curDirection == 2 ){
                    int nextX = curX;
                    int nextY = curY-1;
                    if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1){
                        sb.append(count);
                        return;
                    }
                    if(graph[nextY][nextX] == 1){
                        sb.append(count);
                        return;
                    }
                    curX = nextX;
                    curY = nextY;
                    continue;
                    
                } else if(curDirection == 3 ){
                    int nextX = curX+1;
                    int nextY = curY;
                    if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1){
                        sb.append(count);
                        return;
                    }
                    if(graph[nextY][nextX] == 1){
                        sb.append(count);
                        return;
                    }
                    curX = nextX;
                    curY = nextY;
                    continue;
                } 
            } // End of 3)

            // 4) 청소 가능한 경우
            for(int i=0; i<4; i++){
                if(curDirection == 0){
                    curDirection =3;
                    int nextX = curX-1; int nextY = curY;
                    if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1) continue;
                    if(graph[nextY][nextX] == 1) continue;
                    if(!visited[nextY][nextX]){
                        curX = nextX;
                        curY = nextY;
                        break;
                    }
                    
                } else if(curDirection == 1){
                    curDirection =0;
                    int nextX = curX; int nextY = curY-1;
                    if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1) continue;
                    if(graph[nextY][nextX] == 1) continue;
                    if(!visited[nextY][nextX]){
                        curX = nextX;
                        curY = nextY;
                        break;
                    }
                    
                } else if(curDirection == 2){
                    curDirection =1;
                    int nextX = curX+1; int nextY = curY;
                    if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1) continue;
                    if(graph[nextY][nextX] == 1) continue;
                    if(!visited[nextY][nextX]){
                        curX = nextX;
                        curY = nextY;
                        break;
                    }
                    
                } else if(curDirection == 3){
                    curDirection =2;
                    int nextX = curX; int nextY = curY+1;
                    if(nextX < 0 || nextY < 0 || nextY > N-1 || nextX > M-1) continue;
                    if(graph[nextY][nextX] == 1) continue;
                    if(!visited[nextY][nextX]){
                        curX = nextX;
                        curY = nextY;
                        break;
                    }
                }    
            } // End of 4)
            
        } // End of while

        
    } // End of search
    
}