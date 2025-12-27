import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        // d[len] : 길이가 len+1 인 LIS의 "마지막 값"의 최소값
        int[] d = new int[N];

        //pos[i] : a[i]가 들어간 LIS 길이 -1 (인덱스)
        int[] pos = new int[N];

        int len = 0; //현재 LIS 길이

        for(int i=0; i<N; i++){
            int x = a[i];

            int s = 0;
            int e = len;
            while(s<e){
                int m = (s+e) / 2;
                if( d[m] >= x){
                    e=m;
                }else{
                    s = m+1;    
                }
            }
            d[s] = x;
            pos[i] = s;

            if(s==len){
                len++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");

        int[] lisAns = new int[len];
        int cur = len-1;

        for(int i=N-1; i>=0; i--){
            if(pos[i]==cur){
                lisAns[cur] = a[i];
                cur--;
                if(cur<0)break;
            }
        }
        for(int i=0; i<len; i++){
            sb.append(lisAns[i]+" ");
        }
        System.out.print(sb);
    }
}