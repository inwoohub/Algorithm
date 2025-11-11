import java.io.*;
import java.util.*;

public class Main{
    static int R, C, T;
    static int[][] graph;
    static int robot;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static void bfs(){
        //T초 반복
        for(int i=0; i<T; i++){
            // 다음 그래프 생성
            int[][] nextGraph = new int[R+1][C+1];

            //미세먼지 확산
            for(int x=1; x<=R; x++){
                for(int y=1; y<=C; y++){
                    if(graph[x][y] == -1){
                        nextGraph[x][y] = -1;
                    }
                    //미세먼지가 있는 경우
                    if(graph[x][y]>0){
                        int count = 0;
                        int A = graph[x][y] / 5;
                        for(int k=0; k<4; k++){
                            int nextX = x+dx[k];
                            int nextY = y+dy[k];
                            if(nextX<1 || nextX>R || nextY<1 || nextY>C) continue;
                            if(graph[nextX][nextY] == -1) continue;
                            count = count+A;
                            nextGraph[nextX][nextY] = nextGraph[nextX][nextY]+A;
                        }
                        nextGraph[x][y] = nextGraph[x][y]+(graph[x][y] - count);
                    }
                }
            }            

            //공기 순환

            //위쪽 공기
            // direction 1 우측, 2 상단, 3좌측, 4하단 방향
            int curX = robot-1;
            int curY = 2;
            int cur = nextGraph[curX][curY];
            nextGraph[curX][curY] = 0;
            int direction = 1;
            while(true){
                
                //우측 이동
                if(direction == 1){
                    if(curY+1>C){
                        if(curX-1<1) break;
                        curX--;
                        int next = nextGraph[curX][curY];
                        nextGraph[curX][curY] = cur;
                        cur = next;
                        direction = 2;
                        continue;
                    }
                    curY++;
                    int next = nextGraph[curX][curY];
                    nextGraph[curX][curY] = cur;
                    cur = next;
                    continue;
                }

                //상단 이동
                if(direction == 2){
                    
                    if(curX-1 < 1){
                        direction = 3;
                        curY--;
                        int next = nextGraph[curX][curY];
                        nextGraph[curX][curY] = cur;
                        cur = next;
                        continue;
                    }
                    curX--;
                    int next = nextGraph[curX][curY];
                    nextGraph[curX][curY] = cur;
                    cur = next;
                    continue;
                }
                
                if(direction == 3){
                    if(curY-1 < 1){
                        if(curY-1 ==robot-1){
                            break;
                        }   
                        direction = 4;
                        curX++;
                        int next = nextGraph[curX][curY];
                        nextGraph[curX][curY] = cur;
                        cur = next;
                        continue;
                    }
                    curY--;
                    int next = nextGraph[curX][curY];
                    nextGraph[curX][curY] = cur;
                    cur = next;
                    continue;
                    
                }

                if(direction == 4){
                    if(curX+1 == robot-1){
                        break;
                    }
                    curX++;
                    int next = nextGraph[curX][curY];
                    nextGraph[curX][curY] = cur;
                    cur = next;
                    continue;
                }
                
            }
            //아래쪽 공기
            //위쪽 공기
            // direction 1 우측, 2 하단, 3좌측, 4상단 방향
            curX = robot;
            curY = 2;
            cur = nextGraph[curX][curY];
            nextGraph[curX][curY] = 0;
            direction = 1;
            while(true){
                //우측 이동
                if(direction == 1){
                    if(curY+1 > C){
                        if(curX+1>R) break;
                        curX++;
                        int next = nextGraph[curX][curY];
                        nextGraph[curX][curY] = cur;
                        cur = next;
                        direction = 2;
                        continue;
                    }
                    curY++;
                    int next = nextGraph[curX][curY];
                    nextGraph[curX][curY] = cur;
                    cur = next;
                    continue;
                }


                //하단이동
                if(direction == 2){
                    if(curX+1 > R){
                        direction = 3;
                        curY--;
                        int next = nextGraph[curX][curY];
                        nextGraph[curX][curY] = cur;
                        cur = next;
                        continue;
                    }
                    curX++;
                    int next = nextGraph[curX][curY];
                    nextGraph[curX][curY] = cur;
                    cur = next;
                    continue;
                }

                //좌측 이동
                if(direction == 3){
                    if(curY-1 < 1){
                        direction = 4;
                        curX--;
                        int next = nextGraph[curX][curY];
                        nextGraph[curX][curY] = cur;
                        cur = next;
                        continue;
                    }
                    curY--;
                    int next = nextGraph[curX][curY];
                    nextGraph[curX][curY] = cur;
                    cur = next;
                    continue;
                }

                //상단 이동
                if(direction == 4){
                    if(curX-1 == robot){
                        break;
                    }
                    curX--;
                    int next = nextGraph[curX][curY];
                    nextGraph[curX][curY] = cur;
                    cur = next;
                    continue;
                }
                
            }            

            for(int x=1; x<=R; x++){
                for(int y=1; y<=C; y++){
                    graph[x][y] = nextGraph[x][y];
                }
            }
        }
    }

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R =Integer.parseInt(st.nextToken());
        C =Integer.parseInt(st.nextToken());
        T =Integer.parseInt(st.nextToken());
        graph = new int[R+1][C+1];
        robot = 0;
        for(int i=1; i<=R; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=1; k<=C; k++){
                int A = Integer.parseInt(st.nextToken());
                //공기 청정기 위치 저장
                if(A==-1){
                    robot = i;
                }
                graph[i][k] = A;
            }
        }

        bfs();
        int count = 0;
        for(int i=1; i<=R; i++){
            for(int k=1; k<=C; k++){
                if(graph[i][k]>0){
                    count = count+graph[i][k];
                }
            }
        }

        System.out.print(count);
    }
}