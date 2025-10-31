import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int NodeSize, Max, MaxNode;
    static ArrayList<int[]>[] list;
    static boolean[] visited;

    static void dfs(int curNode, int dist){
        for(int[] next:list[curNode]){
            if(!visited[next[0]]){
                visited[next[0]] = true;
                if(Max<next[1]+dist){
                    Max = next[1]+dist;
                    MaxNode = next[0];
                }
                dfs(next[0], next[1]+dist);
            }
        }
    }
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        NodeSize = Integer.parseInt(st.nextToken());
        list = new ArrayList[NodeSize+1];
        for(int i=1; i<=NodeSize; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<NodeSize; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            while(true){
                int B = Integer.parseInt(st.nextToken());
                if(B==-1){
                    break;
                }
                int C = Integer.parseInt(st.nextToken());
                list[A].add(new int[]{B,C});
                list[B].add(new int[]{A,C});
            }
        }
        MaxNode=1;
        for(int i=0; i<2; i++){
            visited = new boolean[NodeSize+1];
            Max = 0;
            visited[MaxNode] = true;
            dfs(MaxNode,0);
        }
        System.out.print(Max);
    }
}