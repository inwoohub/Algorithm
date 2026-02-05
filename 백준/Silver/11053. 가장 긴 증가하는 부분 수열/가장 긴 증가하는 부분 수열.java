import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] DP = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(DP,1);
        for(int i=1; i<N; i++){
            int max = 1;
            boolean check = false;
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j]){
                    max = Math.max(max, DP[j]);
                    check = true;
                }
            }
            if(check){
                DP[i] = max + 1;
            }
        }
        int ans = 1;
        for(int i=0; i<N; i++){
            ans = Math.max(ans, DP[i]);
        }
        System.out.print(ans);
    }
}