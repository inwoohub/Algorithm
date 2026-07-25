import java.io.*;
import java.util.*;

class Main{

    static String strA, strB;
    static int A, B;
    static int[][] DP;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        // 1. 초기 데이터 입력 받기
        init();

        // 2. 최대길이 찾기
        int answer = search();

        // 3. 정답 출력
        System.out.println(answer);
    }

    static int search() {
        int MAX = 0;
        for(int i=1; i<=A; i++){
            char charA = strA.charAt(i-1);
            for(int j=1; j<=B; j++){
                char charB = strB.charAt(j-1);

                // 1. 같지 않다면 [-1][] or [][-1] 중 더 큰 것 고르기 (기본값 가능)
                DP[i][j] = Math.max( DP[i-1][j], DP[i][j-1] );

                // 2. 같다면 [-1][-1] + 1 보다 큰지 고르기
                if(charA == charB) {
                    DP[i][j] = Math.max(DP[i][j], DP[i-1][j-1]+1);
                }
            }
        }
        return DP[A][B]; // 최대값
    }

    static void init() throws IOException {
        strA = br.readLine();
        strB= br.readLine();
        A = strA.length();
        B = strB.length();
        DP = new int[A+1][B+1];
    }

}