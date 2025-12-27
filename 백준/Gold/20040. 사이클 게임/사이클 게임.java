import java.io.*;
import java.util.*;

public class Main{

    static boolean check = false;
    static int[] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        Arrays.fill(arr,-1);
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int x = find(A);
            int y = find(B);
            union(x,y);
            if(check){
                System.out.print(i+1);
                break;
            }
        }
        if(!check){
            System.out.print(0);
        }
    }


    static void union(int x, int y){
        if(x==y){
            check = true;
            return;
        }else{
            if( x > y){
                arr[x] = y;
            }else{
                arr[y] = x;
            }
        }
    }

    static int find(int x){
        if(arr[x] == -1){
            return x;    
        }else{
            return arr[x] = find(arr[x]);
        }
    }
}