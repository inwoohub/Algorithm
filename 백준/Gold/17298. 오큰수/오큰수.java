// 알고리즘
// 스택 (단조 스택) | NGE

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1) 스택 생성 && 배열 마지막 미리 넣어두기
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[N-1]);

        // 2) 결과 배열 생성
        int[] answer = new int[N];
        answer[N-1] = -1; // 우측 끝은 탐색 못함

        // 3) 뒤에서 부터 탐색 ()
        for(int i=N-2; i>=0; i--){
            
            // 4) 스택이 비어있을 때 까지 반복
            while(!stack.isEmpty()){
                int cur = stack.peek();
                // 5) 스택이 더 큰 경우
                if(arr[i] < cur){
                    answer[i] = cur; // 탐색 성공
                    stack.push(arr[i]); // 현재 값 넣어주기
                    break; // while문 탈출
                } else {
                    stack.pop();
                }
            }

            // 6) 탐색 실패 시 -> -1 넣어주고, 새롭게 스택에 넣어줌
            if(stack.isEmpty()){
                answer[i] = -1;
                stack.push(arr[i]);
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(answer[i]+" ");
        }
        System.out.print(sb);
    }
}