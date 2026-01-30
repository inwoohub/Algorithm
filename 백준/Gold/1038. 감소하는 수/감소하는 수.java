// ================
// 알고리즘
// 감소하는 수 -> for 문 쭉 돌리기?
// 그렇다면, 해야해? 뭐를? 해당 숫자 감소하는지 증가하는지 검증을? -> 시간 초과
// 해당 문제는 dfs 로 접근 해야함 (최대 구하고 골라내는 방법)
// 
// ================
// 18
// ================
// 42
// ================

import java.io.*;
import java.util.*;

public class Main{

    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        // 1) 10 이하는 그대로 출력 가능
        if(target <= 10){
            System.out.print(target);
            return;
        } else if (target >= 1023 ){ // 2) 1023 이상은 해당조건에서 탐색 불가
            System.out.print(-1);
            return;
        }

        // 3) 9876543210 = 1022 번째 수가 감소하는 수 최대 맥시멈
        for(int i=0; i<10; i++){
            dfs(i);
        }

        Collections.sort(list);
        System.out.print(list.get(target));
    }

    static void dfs(long num){
        list.add(num); 
        long modValue = num % 10; // 0으로 끝나는 수면 더 이상 감소 불가능
        if(modValue == 0){
            return;
        }

        for(long i=modValue-1; i>=0; i--){
            long newValue = num*10+i; // 10곱하고 뒤에 숫자 붙이기
            dfs(newValue);
        }
        
    } // End of dfs
} // End of Main class