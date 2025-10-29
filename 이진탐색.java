// 이진탐색

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //arr 배열에 넣음
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] parts = (br.readLine()).split(" ");
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(parts[i]);
        }
        Arrays.sort(arr);


        // brr 배열에 넣음
        int M = Integer.parseInt(br.readLine());
        int[] brr = new int[M];
        String[] parts2 = (br.readLine()).split(" ");
        for(int i=0; i<M; i++){
            brr[i] = Integer.parseInt(parts2[i]);
            //이진탐색 구현
            sb.append(binarySearch(arr, brr[i]) ? "1\n" : "0\n");
        }
        
        System.out.print(sb);
    }

    static boolean binarySearch(int[] a, int x){
        int low = 0;
        int high = a.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            int v = a[mid];
            if(x==v) return true;
            if(x<v){
                high = mid-1;
            }
            if(x>v){
                low = mid+1;
            }
        }
        return false;
    }
}