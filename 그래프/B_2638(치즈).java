import java.io.*;
import java.util.*;

public class Main{
    static int height, width;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int hour;

    
    static void bfs(){
        while(true){
            ArrayList<int[]> list = new ArrayList<>();
            visited = new boolean[height+1][width+1];        
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{1,1});
            while(!q.isEmpty()){
                int[] cur = q.poll();
                for(int i=0; i<4; i++){
                    int cx = cur[0] + dx[i];
                    int cy = cur[1] + dy[i];
                    if(cx<1 || cx>height || cy <1 || cy>width) continue;
                    if(!visited[cx][cy]){
                        visited[cx][cy] = true;
                        if(graph[cx][cy]==0){
                            q.offer(new int[]{cx,cy});
                        }
                        if(graph[cx][cy]==1){
                            list.add(new int[]{cx,cy});
                        }
                    }
                    
                }
            }

            if(list.size()==0){
                return;
            }

            ArrayList<int[]> removeList = new ArrayList<>();
            for(int i=0; i<list.size(); i++){
                int[] next = list.get(i);
                int curX = next[0];
                int curY = next[1];
                int count=0;
                for(int k=0; k<4; k++){
                    int checkX = curX+dx[k];
                    int checkY = curY+dy[k];
                    if(visited[checkX][checkY] && graph[checkX][checkY]==0){
                        count++;
                    }
                }
                if(count>1){
                    removeList.add(new int[]{curX,curY});                    
                }
            }
            for(int i=0; i<removeList.size(); i++){
                int[] rL = removeList.get(i);
                graph[rL[0]][rL[1]] = 0;
            }
            
            hour++;    
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        graph = new int[height+1][width+1];
        for(int i=1; i<=height; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=1; k<=width; k++){
                int A =  Integer.parseInt(st.nextToken());
                graph[i][k]=A;
            }
        }

        hour=0;
        bfs();
        System.out.print(hour);
    }
}