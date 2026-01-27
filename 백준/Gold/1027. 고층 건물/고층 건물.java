// ===================
// 알고리즘
// 기울기 = (y2-y1) / (x1-x2)
// ===================
// 15
// 1 5 3 2 6 3 2 6 4 2 5 7 3 1 5
// ===================
// 7
// ===================

import java.io.*;
import java.util.*;

public class Main{

    static int N;

    public static void main(String[] args) throws IOException{

        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ArrayList<int[]> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            list.add(new int[] { i , Integer.parseInt(st.nextToken())});
        }

        if(N==1){
            System.out.print(0);
            return;
        }

        // 탐색 시작
        int MAX = 0;
        for(int i=0; i<N; i++){
            int count = 0;
            
            // 1) 1이라면 우측만 검사
            if( i==0 ){
                count = rightSearch(i, list);
            }

            // 2) 마지막 이라면 좌측만 검사
            else if(i==N-1){
                count = leftSearch(i, list);
            }

            // 3) 1, N 둘 다 아닌 경우 양옆 검사
            else{
                count = leftSearch(i, list );
                count += rightSearch(i, list);
            }

            // 5) 최대값 비교
            MAX = Math.max(MAX, count);
        }
        System.out.print(MAX);
    }

    // 좌측 검사
    static int leftSearch(int x, ArrayList<int[]> list){
        int count = 1;
        int idx = x-1;
        int[] cur = list.get(x);
        int[] next = list.get(idx);

        long bestDy = (long) next[1] - cur[1];
        long bestDx = (long) cur[0] - next[0];
        
        for(int i=idx-1; i>-1; i--){
            next = list.get(i);
            long dy = (long) next[1] - cur[1];
            long dx = (long) cur[0] - next[0];
            if( dy * bestDx > bestDy * dx){
                count++;
                bestDx = dx;
                bestDy = dy;
            }
        }
        return count;
    }


    // 우측 검사
    static int rightSearch(int x, ArrayList<int[]> list){
        int count = 1;
        int idx = x+1;
        int[] cur = list.get(x);
        int[] next = list.get(idx);
        long bestDy = (long) next[1] - cur[1];
        long bestDx = (long) next[0] - cur[0];
        for(int i=idx+1; i<N; i++){
            next = list.get(i);
            long dy = (long) next[1] - cur[1];
            long dx = (long) next[0] - cur[0];
            if( dy * bestDx > bestDy * dx){
                count++;
                bestDx = dx;
                bestDy = dy;
            }
        }
        return count;
    }
    
}