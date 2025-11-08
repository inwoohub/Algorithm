import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int height, width;
    static int[][] graph;
    static ArrayList<int[]> list;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int count;
    static int result;
    static int abc;

    static void start(){
        int[][] newGraph = graph;
        makeGraph(newGraph,0);
    }

    static void makeGraph(int[][] newGraph,int wall){
        //벽이 3개면 개수 세어보기
        if(wall == 3){
            count = 0;
            visited = new boolean[height+1][width+1];
            for(int i=0; i<list.size(); i++){
                int[] birus = list.get(i);
                int h = birus[0];
                int w = birus[1];
                search(h,w,newGraph);
            }
            
            for(int x=1; x<=height; x++){
                for(int y=1; y<=width; y++){
                    if(newGraph[x][y]==0 && !visited[x][y]){
                        count++;
                    }
                }
            }
            result = Math.max(result, count);
            return;
        }

        // 모든 경우 벽 만들기
        for(int i=1; i<=height; i++){
              for(int k=1; k<=width; k++){
                  if(newGraph[i][k]==0){
                      newGraph[i][k]=1;
                      makeGraph(newGraph,wall+1);
                      newGraph[i][k]=0;
                  }    
              }
        }
    }

    static void search(int w, int h,int[][] newGraph){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{w,h});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0; i<4; i++){
                int cx = cur[0]+dx[i];
                int cy = cur[1]+dy[i];
                if(cx<1 || cx>height || cy <1 || cy>width) continue;
                if(!visited[cx][cy] && newGraph[cx][cy]==0 ){
                    visited[cx][cy]=true;
                    q.offer(new int[]{cx,cy});    
                }
            }    
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        graph = new int[height+1][width+1];
        list = new ArrayList<>();
        for(int i=1; i<=height; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=1; k<=width; k++){
                int A = Integer.parseInt(st.nextToken());
                graph[i][k] = A;
                if(A==2){
                    list.add(new int[]{i,k});
                }
            }
        }
        
        result = 0;
        start();
        System.out.print(result);
    }
}