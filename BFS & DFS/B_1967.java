import java.io.*;
import java.util.*;

public class Main{
    static int size;
    static ArrayList<int[]>[] list;
    static boolean[] visited;
    static int max;
    static int maxNode;
    
    static void dfs(int i, int dist){
        for(int[] next : list[i]){
            if(!visited[next[0]]){
                if(max < next[1]+dist){
                    max = next[1] + dist;
                    maxNode = next[0];
                }
                visited[next[0]] = true;
                dfs( next[0], next[1]+dist );
                visited[next[0]]=false;
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        list = new ArrayList[size+1];
        for(int i=1; i<=size; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=1; i<size; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[A].add(new int[]{B,C});
            list[B].add(new int[]{A,C});
        }
        maxNode = 1;
        for(int i=0; i<2; i++){
            max = -1;
            visited = new boolean[size+1];
            visited[maxNode] = true;
            dfs(maxNode,0);
        }
        System.out.print(max);        
    }
}