// ================
// 알고리즘
// 규칙 찾아야서 조건 맞추기
// 1. 아무리 알고리즘이 안 떠올라 블로그 참조 https://st-lab.tistory.com/79
// 2. 표를 보니 규칙 발견
// 3. 규칙에 맞게 if - else 로 조건 걸어주니 해결
// ================
// 3
// 0 3
// 1 5
// 45 50
// ================
// 3
// 3
// 4
// ================

import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        for(int tC=0; tC<testCase; tC++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 0) 거리 계산
            int distance = end - start;

            // 1) max 의 값은 거리의 루트 값에서 소수점을 버린 정수값
            int max = (int) Math.sqrt(distance);

            // 2) max 가 변하는 지점과 다음 지점 사이에는 항상 count 가 두번 씩 변함
            if( max == Math.sqrt(distance)){
                System.out.println( max * 2 - 1 );
            }

            // 3) max 가 변한 다음에는 반드시 count가 변함
            // -> max가 변하는 곳은 Count가 갈 수 있는 최대 거리이기 때문임
            else if(distance <= max * max + max){
                System.out.println( max * 2 );
            }
            
            // 4) max 값이 변할 때의 count = 2 * max - 1 라는 수식이 따른다.
            else {
                System.out.println( max * 2 + 1 );
            }

        }   
    }
}