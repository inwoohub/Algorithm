import java.io.*;
import java.util.*;

public class Main{
    static int[] arr;
    static int[] DP1;
    static int[] DP2;
    static int size;

    static void LIS(){
        //DP1 LIS 증가순
        for(int i=1; i<size; i++){
            for(int k=0; k<i; k++){
                if(arr[i]>arr[k]){
                    DP1[i] = Math.max(DP1[i] , DP1[k]+1);
                }
            }
        }

        //DP2 LIS 감소순
        for(int i=size-2; i>=0; i--){
            for(int k=i+1; k<size; k++){
                if(arr[i] > arr[k]){
                    DP2[i] = Math.max(DP2[i], DP2[k]+1);
                }
            }
        }
        
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        arr = new int[size];
        DP1 = new int[size];
        DP2 = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<size; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(DP1,1);
        Arrays.fill(DP2,1);
        LIS();

        int result = 1;
        for(int i=0; i<size; i++){
            result = Math.max(result, DP1[i]+DP2[i]-1);   
        }
        System.out.print(result);
    }
}