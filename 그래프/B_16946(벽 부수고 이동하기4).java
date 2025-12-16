import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] graph;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] indexGraph;
    static boolean[][] visited;
    static int[] compSize;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N+1][M+1];
        compSize = new int[N*M+1];

        for(int x=1; x<=N; x++){
            String input = br.readLine();
            for(int y=1; y<=M; y++){
                graph[x][y] = input.charAt(y-1) - '0';
            }
        }

        visited = new boolean[N+1][M+1];
        indexGraph = new int[N+1][M+1];
        int index = 0;
        for(int x=1; x<=N; x++){
            for(int y=1; y<=M; y++){
                if(graph[x][y]==0 && !visited[x][y]){
                    compSize[index] = indexBfs(x, y, index);
                    index++;
                }
            }
        }

        for(int x=1; x<=N; x++){
            for(int y=1; y<=M; y++){
                int result = 0;
                if(graph[x][y] == 1){
                    result = 1;
                    int[] seen = new int[4];
                    int sc = 0;
                    for(int i=0; i<4; i++){
                        int nextX = x + dx[i];
                        int nextY = y + dy[i];
                        if( nextX < 1 || nextX > N || nextY < 1 || nextY > M ) continue;
                        if( graph[nextX][nextY]==1) continue;
                        int curIndex = indexGraph[nextX][nextY];

                        boolean check = false;
                        for(int k=0; k<sc; k++){
                            if(seen[k] == curIndex){
                               check = true;
                                break;
                            }
                        }
                        if(!check){
                            seen[sc] = curIndex;
                            sc++;
                            result = result + compSize[curIndex];
                        }
                    }
                }
                sb.append(result%10);
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }

    static int indexBfs(int x,  int y, int index){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        visited[x][y] = true;
        indexGraph[x][y] = index;
        int count = 1;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int i=0; i<4; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if( nextX < 1 || nextX > N || nextY < 1 || nextY > M ) continue;
                if( !visited[nextX][nextY] && graph[nextX][nextY] == 0 ){
                    q.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    indexGraph[nextX][nextY] = index;
                    count++;
                }
            }
        }
        return count;
    }
}