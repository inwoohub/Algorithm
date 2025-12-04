import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        double[] A = new double[N];
        double[] B = new double[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            A[i] = x;
            B[i] = y;
        }

        double sumA = 0;
        double sumB = 0;
        for(int i=0; i<N-1; i++){
            sumA = sumA + (A[i]*B[i+1]);
            sumB = sumB + (B[i]*A[i+1]);
        }
        sumA += A[N - 1] * B[0];
        sumB += B[N - 1] * A[0];
        
        double result = Math.abs(sumA - sumB) / 2.0;
        System.out.printf("%.1f", result );
    }
}