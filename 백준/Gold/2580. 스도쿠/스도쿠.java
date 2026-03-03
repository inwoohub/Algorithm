// 알고리즘
// 스도쿠 (dfs+backtracking)

// 0 3 5 4 6 9 2 7 8
// 7 8 2 1 0 5 6 0 9
// 0 6 0 2 7 8 1 3 5
// 3 2 1 0 4 6 8 9 7
// 8 0 4 9 1 3 5 0 6
// 5 9 6 8 2 0 4 1 3
// 9 1 7 6 5 2 0 8 0
// 6 0 3 7 0 1 9 5 2
// 2 5 8 3 9 4 7 6 0

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int[][] sudoku; // 스도쿠 퍼즐
    static ArrayList<int[]> list; // 0 담긴 리스트 (스도쿠 맞춰야하는 곳)

    public static void main(String[] args)throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        sudoku = new int[9][9];
        for(int i=0; i<9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if(sudoku[i][j] == 0){
                    list.add(new int[]{i,j}); // 0인 곳 리스트에 추가
                }
            }
        }

        // 스도쿠 맞추기
        play(0);

        // 데이터 출력
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sb.append(sudoku[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        
    }

    // 스도쿠 맞추기
    // idx: 현재 list_idx
    static boolean play(int idx){
        
        if(idx == list.size()){
            return true;
        }
        
        // sb.append( "현재["+ idx +"]: "+list.get(idx)[0] + list.get(idx)[1]+"\n");
        int[] cur = list.get(idx);
        int cy = cur[0];
        int cx = cur[1];
        boolean check = true;
        
        for(int i=1; i<=9; i++){
            
            // 가로 검사
            check = xTest(cx, cy, i);
            if(!check) continue;

            // 세로 검사
            check = yTest(cx, cy, i);
            if(!check) continue;

            // 구역 검사
            check = divTest(cx, cy, i);
            if(!check) continue;

            // 전부 통과시 퍼즐에 i 넣어줌
            sudoku[cy][cx] = i;

            // 다음 퍼즐 맞추기
            // check = play(idx+1);
            check = play(idx+1);

            // 되돌리기
            if(check){
                break;
            } else {
                sudoku[cy][cx] = 0;    
            }
            
        }

        return check;
        
    } // End play


    // 세로 검사기
    // x: 새로 들어온 값
    // return bool (T/F)
    static boolean yTest(int cx, int cy, int x){
        for(int i=0; i<9; i++){
            if (cy == i) continue;
            if( sudoku[i][cx] == x ) return false;
        }
        return true;
    } // End yTest

    // 가로 검사기
    // x: 새로 들어온 값
    // return bool (T/F)
    static boolean xTest(int cx, int cy, int x){
        for(int i=0; i<9; i++){
            if(cx == i) continue; // 들어갈 자리는 검증 x
            if(sudoku[cy][i] == x) return false;
        }
        return true;
    } // End xTest

    // 구역 검사기
    // x: 새로 들어온 값
    // return bool (T/F)
    static boolean divTest(int cx, int cy, int x){

        // 1. 영역 찾기
        int div = searchDiv(cx, cy);

        if(div == 0){
            for(int i=0; i<=2; i++){
                for(int j=0; j<=2; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
            
        } else if (div == 1){
            for(int i=0; i<=2; i++){
                for(int j=3; j<=5; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
        } else if (div == 2){
            for(int i=0; i<=2; i++){
                for(int j=6; j<=8; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
            
        } else if (div == 3){
            for(int i=3; i<=5; i++){
                for(int j=0; j<=2; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
        } else if (div == 4){
            for(int i=3; i<=5; i++){
                for(int j=3; j<=5; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
        } else if (div == 5){
            for(int i=3; i<=5; i++){
                for(int j=6; j<=8; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
        } else if (div == 6){
            for(int i=6; i<=8; i++){
                for(int j=0; j<=2; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
        } else if (div == 7){
            for(int i=6; i<=8; i++){
                for(int j=3; j<=5; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
        } else if (div == 8){
            for(int i=6; i<=8; i++){
                for(int j=6; j<=8; j++){
                    if( cy == i && cx == j ) continue;
                    if( sudoku[i][j] == x ) return false;
                }
            }
        }
        return true;
    } // End divTest
    
    // 영역만 찾고 반환
    static int searchDiv(int cx, int cy){
        if( 0 <= cy && cy <=2 ){
            if( 0 <= cx && cx <=2 ){
                return 0;
            }
            else if( 3 <= cx && cx <= 5 ){
                return 1;
            }
            else {
                return 2;
            }
        }
        else if ( 3 <= cy && cy <= 5 ){
            if( 0 <= cx && cx <=2 ){
                return 3;
            }
            else if( 3 <= cx && cx <= 5 ){
                return 4;
            }
            else {
                return 5;
            }
        }
        else {
            if( 0 <= cx && cx <=2 ){
                return 6;
            }
            else if( 3 <= cx && cx <= 5 ){
                return 7;
            }
            else {
                return 8;
            }
        }
    } // End searchDiv

    
}