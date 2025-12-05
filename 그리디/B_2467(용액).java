import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        long[] arr = new long[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int left = 0;
        int right = N-1;

        long best = Long.MAX_VALUE;
        int ansL = 0;
        int ansR = N-1;

        while(left < right){
            long sum = arr[left]+arr[right];
            long abs = Math.abs(sum);

            if(abs<best){
                best = abs;
                ansL = left;
                ansR = right;
            }
            if(sum>0){
                right--;
            }
            else{
                left++;
            }
            
        }
        System.out.println(arr[ansL]+" "+arr[ansR]);
    }
}