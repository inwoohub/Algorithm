// 알고리즘 
// 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성
// 그럼 처음꺼 q에 담고, 한바퀴 탐색한다면?
// 그 다음 
// 소요시간 21:20 ~

import java.io.*;
import java.util.*;

public class Main{

    static int N, M;
    static int[][] arrA;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        arrA = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arrA[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int year = 0;
        boolean check = true;
        while(check){
            check = search(year);
            year++;
        }
        
    }

    static boolean search(int year){
        // 1. 빙산 칸 전체 돌면서 덩어리 확인하기 (bfs로 하자.)
        Queue<int[]> searchQ = new LinkedList<>();
        visited = new boolean[N][M];
        int count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j] && arrA[i][j] > 0){
                    // 방문하지 않은 빙산이라면,
                    count++;
                    // 해당 빙산에서 bfs 탐색하기
                    bfs(i,j);
                }                
                
            }
        }
        
        // 2. 덩어리 개수가 2 이상이면 해당 년도 출력하기
        if(count >= 2){
            System.out.print(year);
            return false;
        }

        // 3. 덩어리 개수가 0 이면, 0 출력하기
        else if(count == 0){
            System.out.print(0);
            return false;
        }

        // 4. 위 2,3 이 둘 다 아니라면 빙산 녹이기 -> 빙산이 한덩어리라는 뜻!
        else{
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(arrA[i][j]>0){
                        melt(i,j); // 빙산 녹이기 작업
                        return true;
                    }
                }
            }
        }
        return true;
    }

    // bfs 탐색하며 빙산 녹이기
    static void melt(int y, int x){
        int[][] newArr = new int[N][M];
        visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y,x});
        visited[y][x] = true; // 방문 처리

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            newArr[cy][cx] = arrA[cy][cx];
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if(nx<0||ny<0||ny>=N||nx>=M) continue; // 범위 초과
                if(arrA[ny][nx] == 0 ){
                    newArr[cy][cx]--;
                }
                if(!visited[ny][nx] && arrA[ny][nx]>0){
                    q.offer(new int[]{ny,nx});
                    visited[ny][nx] = true;
                }   
            }
            if(newArr[cy][cx] < 0){
                newArr[cy][cx] = 0; // 음수 방지
            }
        }
        updateArr(newArr); // 기존 배열 업데이트하기
    }

    // 배열 업데이트
    static void updateArr(int[][] newArr){
         for(int i=0; i<N; i++){
             for(int j=0; j<M; j++){
                 arrA[i][j] = newArr[i][j];
             }
         }   
    }

    // bfs 탐색하며 빙산 방문하기
    static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y,x});
        visited[y][x] = true; // 방문 처리
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if(nx<0||ny<0||ny>=N||nx>=M) continue; // 범위 초과
                if(!visited[ny][nx] && arrA[ny][nx]>0){ // 빙산인 경우
                    q.offer(new int[]{ny,nx});
                    visited[ny][nx] = true;
                }else{
                    visited[ny][nx] = true;
                }
            }
        }
    }
    
}
