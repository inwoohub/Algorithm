// ==============
// 알고리즘
// 그래프
// ==============

import java.io.*;
import java.util.*;

public class Main{

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int N, L, R;   // N: 그래프 크기, L: 이상 , R: 이하
    static int[][] graph; // 인구 그래프
    static boolean[][] visited; // 방문 처리

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 탐색 시작 & 데이터 출력
        System.out.println( search() );
    } // End of main

    
    static int search(){
        int ans = 0;
        boolean check = true;
        while(check){
            check = false;
            visited = new boolean[N][N];
            ArrayList<int[]> list = new ArrayList<>();
            
            // 1) 첫번째칸 ~ 마지막칸 까지 탐색
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){

                    // 2) 주변 인구 탐색
                    for(int k=0; k<4; k++){
                        int nY = i + dy[k];
                        int nX = j + dx[k];
                        if( nY<0 || nX<0 || nY>=N || nX>=N ) continue;
                        int sub = Math.abs( graph[i][j] - graph[nY][nX] );
                        
                        // 3) L ~ R 인 경우 (국경 열리는 나라)
                        if( sub >= L && sub <= R  ){
                            check = true;
                            // 4) ArrayList에 해당 나라 담기
                            list.add(new int[]{i,j});
                        }
                        
                    }
                    
                }
            } // End of for(1)

            // 4) 국경이 열린 경우
            if(check){
                ans++;;
                // 5) 해당 국경 bfs 탐색
                for(int[] cur : list){
                    int curY = cur[0];
                    int curX = cur[1];
                    
                    if(visited[curY][curX]) continue;

                    ArrayList<int[]> bfs_list = new ArrayList<>(); // 갱신용 리스트
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{curY, curX});
                    visited[curY][curX] = true;
                    bfs_list.add(new int[]{curY,curX});
                    int sum = graph[curY][curX]; // 초기 sum 값 -> graph[][] 시작점
                    
                    while(!q.isEmpty()){
                        int[] qcur = q.poll();
                        int qcurY = qcur[0];
                        int qcurX = qcur[1];

                        for(int i=0; i<4; i++){
                            int nextY = qcurY + dy[i];
                            int nextX = qcurX + dx[i];
                            if( nextY<0 || nextX<0 || nextY>=N || nextX>=N ) continue;
                            if(visited[nextY][nextX]) continue;
                            int sub = Math.abs( graph[qcurY][qcurX] - graph[nextY][nextX] );
                            if( sub >= L && sub <= R  ){
                                sum += graph[nextY][nextX];
                                bfs_list.add(new int[]{nextY,nextX});
                                q.offer(new int[]{nextY, nextX});
                                visited[nextY][nextX] = true;
                            }
                        }
                    }
                    
                    for(int[] next : bfs_list){
                        graph[next[0]][next[1]] = sum / bfs_list.size();
                    }
                    
                }                
            }

            // 디버깅
            // System.out.print("\n");
            // for(int i=0; i<N; i++){
            //     for(int j=0; j<N; j++){
            //         System.out.print(graph[i][j]+" ");
            //     }
            //     System.out.print("\n");
            // }
            
        } // End of while(check)
        
        return ans;
        
    } // End of search

}