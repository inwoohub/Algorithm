import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int[] arr = new int[target+1];
        int[] sqrtArr = new int[target+1];
        Arrays.fill(sqrtArr,1);

        // 에라토스테네스의 채 (소수 구하기)
        for(int i=2; i<=Math.sqrt(target); i++){
            for(int k=i*2; k<=target; k=k+i){
                sqrtArr[k] = 0;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=2; i<=target; i++){
            if(sqrtArr[i]==1){
                q.offer(i);
            }
        }
        
        // 소수만 있는 배열 새로 생성
        int[] decimal = new int[q.size()];
        int queueSize = q.size();
        for(int i=0; i<queueSize; i++){
            decimal[i] = q.poll();    
        }
        //투 포인터를 활용해서 탐색
        System.out.println(twoPointer(target, decimal));
    }
    
    static int twoPointer(int target, int[] arr){
        int start = 0;
        int end = 0;
        int curValue = 0;
        int count = 0;
        if(curValue == target){
            count++;
        }
        
        while(true){
            if(curValue >= target){
                if(curValue == target){
                    count++;
                }
                curValue = curValue - arr[start];
                start++;
            }else{
                if(end == arr.length)break;
                curValue = curValue+arr[end];
                end++;
            }
        }
        return count;
    }
}