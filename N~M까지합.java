//누적합 M-N임
import java.io.*;
import java.util.*;

public class Main{
    static int[] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        StringTokenizer intst = new StringTokenizer(br.readLine());
        
        for(int i=1; i<=N; i++){
            arr[i] = arr[i]+arr[i-1]+Integer.parseInt(intst.nextToken());
    
        }
        
        for(int i=0; i<M; i++){
            StringTokenizer Stoken = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(Stoken.nextToken());
            int B = Integer.parseInt(Stoken.nextToken());
            sb.append(arr[B]-arr[A-1]+"\n");
        }
        System.out.print(sb);
    }

    static int find(int A, int B){
        return arr[B] - arr[A];
    }
}