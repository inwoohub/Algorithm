import java.io.*;
import java.util.*;

public class Main{
    static boolean[] dvisited;
    static boolean[] bvisited;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] bgraph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //정점의 개수 N, 간선의 개수 M , 시작정점의 번호 V
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        //dfs visit, bfs visit, graph 생성
        dvisited = new boolean[N+1];
        bvisited = new boolean[N+1];
        graph = new ArrayList[N+1];
        bgraph = new ArrayList[N+1];
        
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
            bgraph[i] = new ArrayList<>();
        }
        
        // 간선 입력 받기
        for(int i=0; i<M; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st2.nextToken());
            int B = Integer.parseInt(st2.nextToken());
            graph[A].add(B);
            graph[B].add(A);
            bgraph[A].add(B);
            bgraph[B].add(A);
        }

        dfs(V);
        System.out.println("");
        bfs(V);
    }

    static void dfs(int n){
        dvisited[n] = true;
        System.out.print(n+" ");
        Collections.sort(graph[n]);
        for(int next : graph[n]){
            if(!dvisited[next]){
                dfs(next);
            }
        }
    }

    static void bfs(int n){
        Queue<Integer> q = new LinkedList<>();
        bvisited[n] = true;
        q.offer(n);
        while(!q.isEmpty()){
            int node = q.poll();
            System.out.print(node+" ");
            Collections.sort(bgraph[node]);
            for(int next : bgraph[node]){
                if(!bvisited[next]){
                    bvisited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}