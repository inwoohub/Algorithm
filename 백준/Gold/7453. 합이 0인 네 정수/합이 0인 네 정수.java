// ---------------------------------
// 알고리즘
// 정수로 이루어진 크기가 같은 배열 A, B, C, D 가 있음
// A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하는 프로그램
// 이분 탐색 사용
// 1. A+B 모든 합 구한 후 정렬 - AC 
// 2. C+D 모든 합 구한 후 정렬 - CD
// 3. AC+CD 가 0이 되는 개수 세어보기 (이분탐색)
// ---------------------------------
// 변수
// N : 배열의 크기
// arr[][] : 입력받은 배열 값
// AB[] : A+B 모든 값
// CD[] : C+D 모든 값
// ---------------------------------

import java.io.*;
import java.util.*;

public class Main{

    static long count = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][4];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<4; k++){
                arr[i][k] = Integer.parseInt(st.nextToken());
            }
        }
        int[] AB = new int[ N * N ];
        int[] CD = new int[ N * N ];

        int index = 0;
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                AB[index] = arr[i][0] + arr[k][1]; // A와 B의 모든 합
                index++;
            }
        }

        index = 0;
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                CD[index] = arr[i][2] + arr[k][3]; // C와 D의 모든 합
                index++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);
        
        for(int i=0; i<(N*N); i++){
            int curAB = AB[i];
            binary_search(curAB, CD,index);
        }

        System.out.print(count);
    }

    static void binary_search(int curAB, int[] CD, int size){
        int left = 0;
        int right = size-1;
        while( left <= right ){
            int cur = (left + right)/2;
            int curCD = CD[cur];
            if( curAB+curCD > 0 ){
                right = cur - 1;
            }
            else if( curAB + curCD < 0){
                left = cur +1;
            }
            else{
                count += (upperBound(CD, curCD) - lowerBound(CD, curCD));
                return;
            }
        }
    }

    static int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length; // [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] >= target) r = m;
            else l = m + 1;
            }
            return l;
        }

    static int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length; // [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] > target) r = m;
            else l = m + 1;
        }
        return l;
    }
}
