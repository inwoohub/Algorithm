import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //  N : 수열의 크기
        int N = Integer.parseInt(br.readLine());
        
        // arr 배열 생성
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // d : 길이가 len+1인 LIS의 "마지막 값"의 최대 값
        int[] d = new int[N];        

        // len : 현재 LIS 길이
        int len = 0;

        for(int i=0; i<N; i++){
            int x = arr[i];
            int s = 0;
            int e = len;
            while(s<e){ //이분 탐색
                int m = (s+e) / 2;
                if( d[m] >= x ){
                    e = m;
                }
                else{
                    s = m+1;
                }
            }
            d[s] = x;
            
            if(s==len){
                len++;
            }
        }
        System.out.println(len);
    }
}