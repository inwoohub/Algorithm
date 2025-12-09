//투 포인터 사용
import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[size+1];
        for(int i=1; i<=size; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = Integer.parseInt(br.readLine());

        for(int c=0; c<count; c++){

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            boolean check = false;
            
            while( start <= end ){
                if(arr[start] != arr[end]){
                    check = true;
                    break;
                }

                start++;
                end--;
            }
            if(check){
                sb.append("0\n");    
            }else{
                sb.append("1\n");
            } 
        }
        System.out.print(sb);
    }
}