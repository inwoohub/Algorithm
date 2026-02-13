// ==============
// 알고리즘
// bfs
// ==============
// 4 4
// ####
// #JF#
// #..#
// #..#
// ==============
// 3
// ==============

import java.io.*;
import java.util.*;

public class Main{

    static int R, C; // R:행 , C:열
    static char[][] graph;
    static int[][] fire;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int jx = 0; int jy = 0; // 지훈 초기 위치
        ArrayList<int[]> list = new ArrayList<>(); // 불은 여러개 가능
        
        graph = new char[R][C];
        fire = new int[R][C]; // 불 길
        // 불길 -1 세팅
        for(int i=0; i<R; i++){
            Arrays.fill(fire[i], -1);
        }
        
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                graph[i][j] = input.charAt(j);
                if(graph[i][j] == 'J'){
                    jy = i; jx = j;
                } else if(graph[i][j] == 'F'){
                    list.add(new int[]{i,j});
                }
            }
        }

        // 1) 불길 탐색
        visited = new boolean[R][C];
        fire_bfs(list);

        // 2) 탈출 탐색
        visited = new boolean[R][C];
        int ans = bfs(jx, jy);

        // 결과 출력
        if(ans == 0){
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(ans);
        }
        
    } // End of main


    // 탈출 탐색
    static int bfs(int jx, int jy){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{jy, jx, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curY = cur[0];
            int curX = cur[1];
            int curD = cur[2]; // 현재 거리

            for(int i=0; i<4; i++){
                int nextY = curY+dy[i];
                int nextX = curX+dx[i];
                int nextD = curD + 1;
                if( nextX<0 || nextY<0 || nextY>=R || nextX>=C ){ // 탈출 성공
                    return nextD;
                }
                if( graph[nextY][nextX] == '#' ) continue;
                if(fire[nextY][nextX] <= nextD && fire[nextY][nextX]>=0 ) continue;
                if(visited[nextY][nextX]) continue;
                visited[nextY][nextX] = true;
                q.offer(new int[]{nextY, nextX, nextD});
            }
        }
        return 0; // 탈출 실패
    } // End of bfs

    // 불길 탐색
    static void fire_bfs(ArrayList<int[]> list){
        
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<list.size(); i++){
            int[] cur = list.get(i);
            int curY = cur[0];
            int curX = cur[1];
            q.offer(new int[]{curY, curX, 0});
            visited[curY][curX] = true;
            fire[curY][curX] = 0;
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curY = cur[0];
            int curX = cur[1];
            int curD = cur[2]; // 현재 거리

            for(int i=0; i<4; i++){
                int nextY = curY+dy[i];
                int nextX = curX+dx[i];
                int nextD = curD + 1;
                if( nextX<0 || nextY<0 || nextY>=R || nextX>=C ) continue;
                if( graph[nextY][nextX] == '#' ) continue;
                if(visited[nextY][nextX]) continue;
                fire[nextY][nextX] = nextD;
                visited[nextY][nextX] = true;
                q.offer(new int[]{nextY, nextX, nextD});
            }
        }
    } // End of fire_bfs
    
}