// 아기 상어 크기 : 2
// 상하좌우 시간 : 1초
// 자기보다 작은 칸만 이동 가능
// 크기가 같으면 이동만 가능
// 자신의 크기와 같은 수의 물고기 먹어야 크기 +1 증가

// 먹을게 많다면 가장가까운거 -> 가장 위 > 가장 왼쪽 순으로 (우선순위 큐 사용)
// 1개 먹었으면 다시 먹이 탐색

import java.io.*;
import java.util.*;

public class Main{

    static int[][] graph;
    static int sharkSize;
    static int sharkX;
    static int sharkY;
    static int count;
    static int size;
    static int[][] dist;
    static int second;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        graph = new int[size+1][size+1];
        for(int i=1; i<=size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=size; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 9){
                    sharkX = i;
                    sharkY = j;
                    graph[i][j] = 0;
                }
                
            }
        }
        
        sharkSize = 2;
        second = 0;
        count = 0;
        start();
        System.out.print(second);
    }

    static void start(){
        while(true){
            //우선순위 큐 생성 (상어 먹이)
            PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) ->
            {
                if(a[0] != b[0]) return a[0]-b[0];
                else if (a[1]!=b[1]) return a[1]-b[1];
                else return a[2]-b[2];
            });

            // 다익스트라 사용 (먹이 찾기)
            dist = new int[size+1][size+1];
            for(int i=1; i<=size; i++){
                Arrays.fill(dist[i], Integer.MAX_VALUE);    
            }
            dist[sharkX][sharkY] = 0;
            PriorityQueue<int[]> q = new PriorityQueue<>( (a,b) -> a[0]-b[0]);
            q.offer(new int[]{0,sharkX, sharkY});
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int curDist = cur[0];
                int curX = cur[1];
                int curY = cur[2];
                if( dist[curX][curY] < curDist ) continue;
                for(int i=0; i<4; i++){
                    int nextDist = curDist+1;
                    int nextX = curX+dx[i];
                    int nextY = curY+dy[i];
                    // 0. 그래프 밖
                    if(nextX<1 || nextX>size || nextY<1 || nextY>size) continue;
                    // 1. 아예 못가는 경우
                    if(graph[nextX][nextY] > sharkSize) continue;
                    // 2. 거리 갱신 유리할 때만
                    if(dist[nextX][nextY] <= nextDist) continue;
                    dist[nextX][nextY] = nextDist;
                    // 3. 먹을 수 있는 물고기 담기
                    if(graph[nextX][nextY]<sharkSize && graph[nextX][nextY]!=0){
                        pq.offer(new int[]{nextDist, nextX, nextY});
                    }
                    // 4. 이동
                    q.offer(new int[]{nextDist, nextX, nextY});
                }
            }
            if(!pq.isEmpty()){
                int[] cur = pq.poll();
                int curDist = cur[0];
                int curX = cur[1];
                int curY = cur[2];
                count++;
                if(sharkSize == count){ // 상어 레벨업
                    sharkSize++;
                    count=0;
                }
                second = second+curDist;
                sharkX = curX;
                sharkY = curY;
                graph[curX][curY] = 0;
                continue;
            }
            break;
        }
    }
}