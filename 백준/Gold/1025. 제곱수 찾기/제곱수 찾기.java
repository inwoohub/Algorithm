// -----------------
// 알고리즘
// 브루트포스
// -----------------
// 6 7
// 3791178
// 1283252
// 4103617
// 8233494
// 8725572
// 2937261

import java.io.*;
import java.util.*;

public class Main{
    
    // 완전 제곱수 판별 및 최대값 비교
    static void isSquare(String result){
        int res = Integer.parseInt(result);
        if(res<0) return;
        int s = (int) Math.sqrt(res);
        if(s*s == res){
            ans = Math.max(ans, res);
        }
        return;
    }

    static int ans = -1;
    static int N, M;
    static int[][] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = input.charAt(j)-'0';
            }
        } // 데이터 매핑 끝

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                search(i,j);
            }
        }
        System.out.print(ans);
    }

    static void search(int x, int y){
        String result = "";
        int cx = x;
        int cy = y;

        // 0. 혼자
        result += Integer.toString(arr[x][y]);
        isSquare(result); 
        
        // 1. 같은 행 에서
        // 1-1. 우측으로
        for(int i=1; i<M; i++){
            result = "";
            result += Integer.toString(arr[x][y]);
            if(y+i<M){
                cx = x;
                cy = y+i;    
                while(cy<M){
                    result += Integer.toString(arr[cx][cy]);
                    isSquare(result);
                    cy = cy+i;
                }
            }
        }
        // 1-2. 좌측으로
        for(int i=1; i<M; i++){
            result = "";
            result += Integer.toString(arr[x][y]);
            if(y-i>=0){
                cx = x;
                cy = y-i;    
                while(cy>=0){
                    result += Integer.toString(arr[cx][cy]);
                    isSquare(result);
                    cy = cy-i;
                }
            }
        }
        

        // 2. 같은 열 에서
        // 2-1. 아래로
        for(int i=1; i<N; i++){
            result = "";
            result += Integer.toString(arr[x][y]);
            if(x+i<N){
                cx = x+i;
                cy = y;    
                while(cx<N){
                    result += Integer.toString(arr[cx][cy]);
                    isSquare(result);
                    cx = cx + i;
                }
            }
        }
        // 2-2. 위로
        for(int i=1; i<N; i++){
            result = "";
            result += Integer.toString(arr[x][y]);
            if(x-i>=0){
                cx = x-i;
                cy = y;    
                while(cx>=0){
                    result += Integer.toString(arr[cx][cy]);
                    isSquare(result);
                    cx = cx - i;
                }
            }
        }

        // 3. 우측 아래로
        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                result = "";
                result += Integer.toString(arr[x][y]);
                if( x+i<N && y+j<M ){
                    cx = x+i;
                    cy = y+j;    
                    while(cx<N && cy<M){
                        result += Integer.toString(arr[cx][cy]);
                        isSquare(result);
                        cx = cx + i;
                        cy = cy + j;
                    }
                }
            }
        }

        // 4. 우측 위로
        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                result = "";
                result += Integer.toString(arr[x][y]);
                if( x-i>=0 && y+j<M ){
                    cx = x-i;
                    cy = y+j;    
                    while(cx>=0 && cy<M){
                        result += Integer.toString(arr[cx][cy]);
                        isSquare(result);
                        cx = cx - i;
                        cy = cy + j;
                    }
                }
            }
        }
        
        // 5. 좌측 아래로
        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                result = "";
                result += Integer.toString(arr[x][y]);
                if( x+i<N && y-j>=0 ){
                    cx = x+i;
                    cy = y-j;    
                    while(cx<N && cy>=0){
                        result += Integer.toString(arr[cx][cy]);
                        isSquare(result);
                        cx = cx + i;
                        cy = cy - j;
                    }
                }
            }
        }

        // 6. 좌측 위로
        for(int i=1; i<N; i++){
            for(int j=1; j<M; j++){
                result = "";
                result += Integer.toString(arr[x][y]);
                if( x+i>=0 && y-j>=0 ){
                    cx = x-i;
                    cy = y-j;    
                    while(cx>=0 && cy>=0){
                        result += Integer.toString(arr[cx][cy]);
                        isSquare(result);
                        cx = cx - i;
                        cy = cy - j;
                    }
                }
            }
        }   
    }
}