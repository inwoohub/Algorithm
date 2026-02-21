// ==============
// 알고리즘
// dfs
// ==============

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int L, C;   // L: 암호 길이 / C: 주어진 문자 개서
    static char[] arr; // 문자 배열
    static char[] curArr; // 현재 담긴 배열

    public static void main(String[] args) throws IOException{

        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new char[C];
        curArr = new char[L];
        for(int i=0; i<C; i++){
            arr[i] = st.nextToken().charAt(0);
        }

        // 1) 사전순 정렬
        Arrays.sort(arr);

        // 2) for문으로 4개까지 가능할 때 까지
        for(int i=0; i<C-L+1; i++){
            curArr[0] = arr[i];
            search(i,1);
        }

        // 데이터 출력
        System.out.print(sb);
    }

    // 탐색 메서드
    static void search(int x, int count){
        // 3) 총 길이가 L 만큼 된다면,
        if(count == L){
            // 4) 모음1개, 자음 2개 검증
            int a = 0; // 모음
            int b = 0; // 자음
            for(int i=0; i<L; i++){
                char c = curArr[i];
                if( c=='a' || c=='e' || c=='i' || c=='o' || c=='u' ){
                    a++;
                } else {
                    b++;
                }
            }
            // 5) 검증 성공시
            if( a>=1 && b>=2 ){
                for(int i=0; i<L; i++){
                    sb.append(curArr[i]);
                }
                sb.append("\n");
            }
            return;
        }

        // 6) 다음 붙일 글자 탐색
        for(int i=x+1; i<C; i++){
            curArr[count] = arr[i];
            search(i, count+1);
        }
    }
    
}