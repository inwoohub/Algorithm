// https://school.programmers.co.kr/learn/courses/30/lessons/159993

import java.util.*;

class Solution {
    
    static int xSize, ySize;
    static char[][] graph;
    static int[][] dist;
    static boolean[][] visited;
    static int[] startPoint;
    static int[] endPoint;
    static int[] leverPoint;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    static void leverbfs(){
        visited = new boolean[xSize+1][ySize+1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startPoint[0], startPoint[1], 0});
        visited[startPoint[0]][startPoint[1]] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            for(int i=0; i<4; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                int nextDist = curDist+1;
                if(nextX<1 || nextX > xSize || nextY<1 || nextY>ySize) continue;
                if(!visited[nextX][nextY]){
                    visited[nextX][nextY]=true;
                    
                    if(graph[nextX][nextY]=='X') continue;
                    
                    if(graph[nextX][nextY]=='O' || graph[nextX][nextY]=='E' ){
                        q.offer(new int[]{nextX,nextY,nextDist});
                        dist[nextX][nextY] = nextDist;
                    }    
                    
                    if(graph[nextX][nextY]=='L'){
                        dist[nextX][nextY] = nextDist;
                        return;
                    }    
                    
                    
                }
            }
        }
    }
    
    static void endbfs(){
        visited = new boolean[xSize+1][ySize+1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{leverPoint[0], leverPoint[1], dist[leverPoint[0]][leverPoint[1]]});
        visited[ leverPoint[0] ][ leverPoint[1] ]=true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            for(int i=0; i<4; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                int nextDist = curDist+1;
                if(nextX<1 || nextX > xSize || nextY<1 || nextY>ySize) continue;
                if(!visited[nextX][nextY]){
                    visited[nextX][nextY] = true;
                    
                    if(graph[nextX][nextY] =='X'){
                        continue;
                    }

                    if(graph[nextX][nextY]=='O' || graph[nextX][nextY]=='S'){
                        q.offer(new int[]{nextX,nextY,nextDist});
                        dist[nextX][nextY] = nextDist;    
                    }
                    
                    else if(graph[nextX][nextY]=='E'){
                        dist[nextX][nextY] = nextDist;
                        return;
                    }
                    
                }
                
            }
        }
        
    }
    
    
    
    public int solution(String[] maps) {
        
        xSize = maps.length;
        ySize = maps[0].length();
        graph = new char[xSize+1][ySize+1];
        dist = new int[xSize+1][ySize+1];
        startPoint = new int[2];
        leverPoint = new int[2];
        endPoint = new int[2];
        for(int x=1; x<=xSize; x++){
            for(int y=1; y<=ySize; y++){
                graph[x][y] = maps[x-1].charAt(y-1);
                if(graph[x][y]=='S'){
                    startPoint=new int[]{x,y};     
                }
                if(graph[x][y]=='L'){
                    leverPoint=new int[]{x,y};     
                }
                if(graph[x][y]=='E'){
                    endPoint=new int[]{x,y};     
                }
                
            }
        }
        
        leverbfs();
        if(!visited[ leverPoint[0] ][ leverPoint[1] ] ){ return -1;}
        endbfs();
        if(!visited[ endPoint[0] ][ endPoint[1] ]){ return -1;} 
        
        return dist[ endPoint[0] ][ endPoint[1] ];
        
    }
}