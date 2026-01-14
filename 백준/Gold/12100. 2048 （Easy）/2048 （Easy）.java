// =====================
// 알고리즘
// 5번 실행 중 가장 큰 값 찾기
// 5번의 경우는 dfs로 해보기 ( 4x4x4x4x4 = 1024 경우 )
// 가장 큰 값은 합쳐질 때 마다 갱신하기
// =====================

import java.io.*;
import java.util.*;

public class Main{
    
    static int ans = 0;
    static int N;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 데이터 매핑 끝

        if(N>1){
            dfs(graph, 0); // 프로그램 시작    
        } else {
            ans = graph[0][0];
        }

        System.out.println(ans);
    }
    
    // dfs ( Easy 보드게임 )
    static void dfs(int[][] arr , int count){
        if(count==5){ // 최대 5회 즉, 6회는 종료
            return;
        }
        
        // 시작 방향 정하기 (i: 0=상, 1=하, 2=좌, 3=우 )   
        for(int i=0; i<4; i++){
            dfs(move(arr, i) , count+1); // 이동 완료한 배열 dfs 탐색 (count 1 증가)
        }
        
    }

    // 받아온 방향대로 카드 이동
    // dir: 0=상, 1=하, 2=좌, 3=우
    static int[][] move(int[][] board, int dir) {
        int[][] out = new int[N][N];
    
        for (int line = 0; line < N; line++) {
            // 1) 한 줄에서 0 아닌 값만 뽑기 (압축)
            int[] tmp = new int[N];
            int idx = 0;
    
            for (int k = 0; k < N; k++) {
                int val;
                if (dir == 0) val = board[k][line];            // 상: (row=k, col=line) 위->아래 읽기
                else if (dir == 1) val = board[N - 1 - k][line]; // 하: 아래->위 읽기
                else if (dir == 2) val = board[line][k];       // 좌: (row=line, col=k) 좌->우 읽기
                else val = board[line][N - 1 - k];             // 우: 우->좌 읽기
    
                if (val != 0) tmp[idx++] = val;
            }
    
            // 2) 앞에서부터 한 번만 합치기
            int[] merged = new int[N];
            int w = 0;
            int p = 0;
            while (p < idx) {
                if (p + 1 < idx && tmp[p] == tmp[p + 1]) {
                    merged[w++] = tmp[p] * 2;
                    p += 2;
                } else {
                    merged[w++] = tmp[p];
                    p += 1;
                }
            }
    
            // 3) 방향에 맞춰 다시 채우기
            for (int k = 0; k < N; k++) {
                int val = merged[k];
    
                if (dir == 0) out[k][line] = val;                 // 상
                else if (dir == 1) out[N - 1 - k][line] = val;    // 하
                else if (dir == 2) out[line][k] = val;            // 좌
                else out[line][N - 1 - k] = val;                  // 우
    
                // 최대값 갱신(안전)
                if (val > ans) ans = val;
            }
        }
    
        return out;
    }

    // 배열 복제 함수
    static int[][] copyArr(int[][] arr){
        int[][] newArr = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }
}