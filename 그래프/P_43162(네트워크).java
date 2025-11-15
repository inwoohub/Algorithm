import java.util.*;


class Solution {
    
    static int count;
    static boolean[] visited;
    
    static void dfs(int n, int curNode, int[][] computers){
        for(int i=0; i<n; i++){
            if(computers[curNode][i]==1){
                if(!visited[i]){
                    visited[i] = true;
                    dfs(n,i,computers);
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        count = 0;
        visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(n, i, computers);
                count++;
            }
        }
        
        int answer = count;
        return answer;
    }
}