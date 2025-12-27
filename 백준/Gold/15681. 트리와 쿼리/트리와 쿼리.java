import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int N, R, Q; // N : 정점의 수, R : 루트 , Q : 쿼리 수
    static ArrayList<Integer>[] graph;
    static int[] value;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            graph[B].add(A);
        }
        value = new int[N+1];
        visited = new boolean[N+1];
        visited[R] = true;
        search(R);
        for(int i=0; i<Q; i++){
            int query = Integer.parseInt(br.readLine());
            sb.append(value[query]+"\n");
        }
        System.out.print(sb);
    }

    static int search(int curNode){
        int count = 1;
        for(int nextNode : graph[curNode]){
            if(!visited[nextNode]){
                visited[nextNode] =true;
                count = count + search(nextNode);
            }
        }
        value[curNode] = count;
        return count;
    }

    
}