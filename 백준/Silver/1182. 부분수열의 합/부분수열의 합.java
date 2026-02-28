// 알고리즘
// dfs
import java.io.*;
import java.util.*;

public class Main{

    static int N, target; // N: 정수의 개수, target: 목표값
    static int[] arr;
    static int ans; // 출력값
    
    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        String[] inputArr = input.split(" ");
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(inputArr[i]);
        }

        // 출력값
        ans = 0;

        // dfs 탐색 시작
        for(int i=0; i<N; i++){
            dfs(i, arr[i]);
        }

        // 데이터 출력
        System.out.print(ans);
        
    }

    // dfs
    static void dfs(int x, int sum){
        // 1. 합 == target
        if(sum==target){
            ans++;
        }

        // 2. 다음 idx 탐색
        for(int i=x+1; i<N; i++){
            dfs(i, sum+arr[i]);
        }
    }
    
}