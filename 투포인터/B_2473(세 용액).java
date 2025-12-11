import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int[] arr = new int[size];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<size; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }        
        Arrays.sort(arr);

        int resultLeft = 0;
        int resultMiddle = 0;
        int resultRight = 0;
        long best = 3000000000L;
        
        for(int i=0; i<size; i++){
            int left = 0;
            int right = size-1;
            
            while(left < right){
                if(left == i || right == i){
                    if(left==i){
                        left++;
                        continue;
                    }else{
                        right--;
                        continue;
                    }
                }
                long sum = (long) arr[left]+arr[right]+arr[i];
                long abs = Math.abs(sum);

                if(abs<best){
                    best = abs;
                    resultLeft = left;
                    resultMiddle = i;
                    resultRight = right;
                }
                if(sum>0){
                    right--;
                }else{
                    left++;
                } 
            }
        }
        int[] result = new int[3];
        result[0] = arr[resultLeft];
        result[1] = arr[resultMiddle];
        result[2] = arr[resultRight];
        Arrays.sort(result);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++){
            sb.append(result[i]+" ");
        }
        System.out.print(sb);
    }
}