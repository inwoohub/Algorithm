// 알고리즘
// 빡구현

import java.io.*;
import java.util.*;

public class Main{

    static int target;
    static int[] count;
    static boolean[] arr; // 고장난 리모컨
    
    public static void main(String[] args) throws IOException{

        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine()); // 고장난 버튼 수
        arr = new boolean[10];
        if(N > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                int A = Integer.parseInt(st.nextToken());
                arr[A] = true; // 고장난 리모컨 처리
            }    
        }
        

        // 1. 해당 리모컨으로 접근할 수 있는 채널 다 찾기
        count = new int[1000011];
        Arrays.fill(count, -1);

        if( !arr[0] ){
            count[0] = 1;    
        }

        
        for(int i=1; i<10; i++){
            if(arr[i]) continue; // 고장난 번호는 넘기기
            search("", i, 1);    
        }

        int near = -1; // 가장 가까운 수
        int value = 1000010;
            
        // 2. target 이랑 가장 가까운 수 찾기
        for(int i=0; i<1000011; i++){
            // 100은 제외
            if(i==100) continue;
            if(count[i] == -1) continue; // 접근 불가능
            
            if( Math.abs(target-i) < value ){
                near = i;
                value = Math.abs(target-i);
            }
        }

        // 3. 데이터 출력
        if( target == 100 ){
            System.out.print(0);
        }
            
        else {
            // 버튼 다 고장난 경우
            if(near == -1){
                System.out.println( Math.abs(target-100) );
            } else {
                // near + '+'or'-' vs 100에서 +, -
                if( Math.abs(target-near) + count[near] > Math.abs(target - 100) ){
                    System.out.println( Math.abs(target-100) ); // 바로 +,-
                    // System.out.print("near: "+near);
                } else {
                    System.out.println( Math.abs(target-near) + count[near] ); // 가장 가까운 값 접근후 target 만큼 +, -
                    // System.out.print("near: "+near);
                }    
            }
            
        }
        // 디버깅
        // for(int i=0; i<=500000; i++ ){
        //     System.out.println(i+": "+count[i]);
        // }
                 
    }

    // x: 현재 들어온 번호, c: 횟수
    static void search(String str, int x, int c){

        // 문자 + 숫자 (붙이기)
        String next = str+x;

        int cur = Integer.parseInt(next);
        
        // 초과 되면 패스
        if( cur > 1000010){
            return;
        }

        // 처음 방문이 아닌 경우도 패스
        if( count[cur] != -1 ){
            return;
        }

        // 처음 방문했으니 카운터 저장
        count[cur] = c;
        
        for(int i=0; i<10; i++){
            if(arr[i]) continue; // 고장난 번호는 넘기기
            search(next, i ,c+1);
        }
        
    }
    
}