import java.io.*;
import java.util.*;

public class Main{

    static int[] graph;
    static boolean[] visited;
    static int[] dict;

    static void find(int N, int K){
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        visited[N] = true;
        while(!q.isEmpty()){
            int A = q.poll();
            int B = A-1;
            int C = A+1;
            int D = A*2;
            if(D<0 || D>100000){            }
            else{
                if(!visited[D]){
                    q.offer(D);
                    visited[D]=true;
                    dict[D] = dict[A];    
                }
            }
            if(B<0 || B>100000){            }
            else{
                if(!visited[B]){
                    q.offer(B);
                    visited[B]=true;
                    dict[B] = dict[A]+1;    
                }
            }
            if(C<0 || C>100000){            }
            else{
                if(!visited[C]){
                    q.offer(C);
                    visited[C]=true;
                    dict[C] = dict[A]+1;    
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Sobin = Integer.parseInt(st.nextToken());
        int Bro = Integer.parseInt(st.nextToken());
        graph = new int[100001];
        visited = new boolean[100001];
        dict = new int[100001];
        dict[Sobin] = 0;
        find(Sobin, Bro);
        System.out.print(dict[Bro]);
    }
}