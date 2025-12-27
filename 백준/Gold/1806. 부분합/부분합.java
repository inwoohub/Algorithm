// 투 포인터

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        int[] arr = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<size; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] lArr = new int[size+1];
        int sum = 0;
        for(int i=0; i<size; i++){
            sum = sum+arr[i];
            lArr[i+1] = sum;    
        }

        if(lArr[size]<min){
            System.out.print(0);
        }else{
            int result = Integer.MAX_VALUE;
            int start = 0;
            int end = 0;
            while(start <= end && end <= size){
                if(lArr[end]-lArr[start]<min){
                    end++;
                }else{
                    result = Math.min(result , end - start);
                    start++;
                }
            }
            System.out.print(result);
        }
    }
}