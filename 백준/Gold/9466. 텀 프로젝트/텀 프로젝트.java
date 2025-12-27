import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int N, count;
    static boolean[] visited, finish;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        for(int tC=0; tC<testCase; tC++){
            count = 0;
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N+1];
            finish = new boolean[N+1];
            st = new StringTokenizer(br.readLine());
            arr = new int[N+1];
            for(int i=1; i<=N; i++){
                int A = Integer.parseInt(st.nextToken());
                arr[i] = A;
                
            }
            for(int i=1; i<=N; i++){
                dfs(i);
            }
            sb.append(N-count+"\n");
        }
        System.out.print(sb);
    }

    static void dfs(int x){

        if(visited[x]){
            count++;
            finish[x] = true;
        }else{
            visited[x] = true;
        }

        if(!finish[ arr[x] ]){
            dfs( arr[x] );
        }

        visited[x] = false;
        finish[x] = true;   
    }
}