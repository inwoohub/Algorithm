import java.io.*;
import java.util.*;


public class Main{
    static StringBuilder sb = new StringBuilder();
    static int N, M, W;
    static int[] dist;
    static ArrayList<Integer>[] list;
    static int[][] nodeDist;
    
    
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        for(int i=0; i<testCase; i++){

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            nodeDist = new int[N+1][N+1];
            dist = new int[N+1];
            list = new ArrayList[N+1];
            for(int j=1; j<=N; j++){
                list[j] = new ArrayList<>();
                Arrays.fill(nodeDist[j],Integer.MAX_VALUE);
            }

            //양방향 도로 추가
            for(int j=0; j<M; j++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                list[A].add(B);
                list[B].add(A);

                nodeDist[A][B] = Math.min(nodeDist[A][B],C);
                nodeDist[B][A] = Math.min(nodeDist[B][A],C);
            }

            for(int j=0; j<W; j++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                list[A].add(B);
                nodeDist[A][B] = Math.min(nodeDist[A][B], (-C));
                
            }

            Arrays.fill(dist,0);
            Bellman_Ford();
            
        }
        System.out.print(sb);
    }

    static void Bellman_Ford(){
        boolean Cycle = false;

        for(int j=1; j<=N; j++){
            boolean updated = false;
            for(int i=1; i<=N; i++){
                for(int next : list[i]){
                    if(dist[next] > dist[i] + nodeDist[i][next]){
                        dist[next] = dist[i] + nodeDist[i][next];
                        updated = true;
                        if(j==N){
                            Cycle = true;
                        }
                    }
                } 
            }
            if(!updated) break;   
        }
        if(Cycle){
            sb.append("YES\n");
        } else {
            sb.append("NO\n");
        }
    }
}