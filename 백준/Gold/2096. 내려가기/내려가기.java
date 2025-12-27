import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N][3];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<3; k++){
                graph[i][k] = Integer.parseInt(st.nextToken());
            }
        }
        if(N>1){
            int pre0 = graph[0][0];
            int pre1 = graph[0][1];
            int pre2 = graph[0][2];
            int cur0 = graph[1][0];
            int cur1 = graph[1][1];
            int cur2 = graph[1][2];
            for(int i=1; i<N; i++){
                cur0 = Math.max(pre0,pre1) + graph[i][0];
                cur1 = Math.max(pre2, Math.max(pre0,pre1)) + graph[i][1];
                cur2 = Math.max(pre1,pre2) + graph[i][2];
                pre0 = cur0;
                pre1 = cur1;
                pre2 = cur2;
            }
            sb.append(Math.max(cur0, Math.max(cur1,cur2))+" ");
            pre0 = graph[0][0];
            pre1 = graph[0][1];
            pre2 = graph[0][2];
            cur0 = graph[1][0];
            cur1 = graph[1][1];
            cur2 = graph[1][2];
            for(int i=1; i<N; i++){
                cur0 = Math.min(pre0,pre1) + graph[i][0];
                cur1 = Math.min(pre2, Math.min(pre0,pre1)) + graph[i][1];
                cur2 = Math.min(pre1,pre2) + graph[i][2];
                pre0 = cur0;
                pre1 = cur1;
                pre2 = cur2;
            }
            sb.append(Math.min(cur0, Math.min(cur1,cur2)));    
        }
        else{
            sb.append(Math.max(graph[0][0], Math.max(graph[0][1],graph[0][2]))+" "+Math.min(graph[0][0], Math.min(graph[0][1],graph[0][2])));
        }
        System.out.print(sb);
    }
}