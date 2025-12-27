// 최소 행렬 곱 DP 사용
// 시간복잡도 O(n^3)
// DP[i][j] =  DP[i][k] + DP[k+1][j] + ( arr[i-1] * arr[k] * arr[j] )

import java.io.*;
import java.util.*;

public class Main{

    static int[] arr;
    static int arrSize;
    static long[][] DP;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        arrSize = size+1;
        arr = new int[arrSize];
        DP = new long[arrSize][arrSize];
        for(int i=1; i<arrSize; i++){
            Arrays.fill(DP[i], Long.MAX_VALUE);
        }
        for(int i=1; i<arrSize; i++){
            DP[i][i] = 0;    
        }
    
        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(i == 0){
                arr[0] = A;
                arr[1] = B;
            }else{
                arr[i+1] = B;    
            }
        }

        for(int len=2; len<=size; len++){
            for(int i=1; i+len-1<=size; i++){
                int j = i+len-1;
                for(int k=i; k<j; k++){
                    long cost = DP[i][k] + DP[k+1][j] + 1L * arr[i-1] * arr[k] * arr[j];
                    DP[i][j] = Math.min( DP[i][j] , cost);
                }
            }
        }
    
        System.out.print(DP[1][size]);
    
    }    
}
