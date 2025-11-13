import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int[][] dist;
    static int[][] graph;
    static int citySize;

    static void start(){
        boolean check=true;
        while(check){
            check = false;
            for(int i=1; i<=citySize; i++){
                for(int k=1; k<=citySize; k++){
                    if(graph[i][k]==0) continue;
                    int curDist = graph[i][k];
                    for(int j=1; j<=citySize; j++){
                        if( i==j || graph[k][j]==0 ) continue;
                        int nextDist = graph[k][j];
                        if(graph[i][j]==0){
                            graph[i][j] = curDist+nextDist;
                            check = true;
                            
                        }
                        else{
                            if(graph[i][j] > curDist+nextDist){
                                graph[i][j] = curDist+nextDist;
                                check = true;    
                            }
                        }
                    }
                }
            }    
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        citySize = Integer.parseInt(st.nextToken());
        dist = new int[citySize+1][citySize+1];
        graph = new int[citySize+1][citySize+1];
        
        st = new StringTokenizer(br.readLine());
        int busSize = Integer.parseInt(st.nextToken());

        for(int i=1; i<=busSize; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if(graph[A][B] == 0){
                graph[A][B] = C;
            }
            else{
                graph[A][B] = Math.min(graph[A][B],C);
            }
        }
        start();

        for(int i=1; i<=citySize; i++ ){
            for(int k=1; k<=citySize; k++){
                sb.append(graph[i][k]+" ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
}