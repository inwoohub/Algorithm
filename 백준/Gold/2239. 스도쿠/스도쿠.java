// 백트래킹 사용
import java.io.*;
import java.util.*;

public class Main{
    static int[][] graph;
    static StringBuilder sb = new StringBuilder();
    static boolean check = false;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new int[9][9];

        for(int i=0 ; i<9; i++){
            String input = br.readLine();
            for(int k=0; k<9; k++){
                graph[i][k] = input.charAt(k) - '0';    
            }
        }
        sudoku(0, 0);
        System.out.print(sb);
    }

    static void sudoku(int r, int c){
        if (check) return;

        if (c == 9){
            sudoku(r+1, 0);
            return;
        }

        if (r == 9){
            check = true;
            printGraph();
            return;
        }

        if( graph[r][c] != 0){
            sudoku(r,c+1);
        }else{
            for(int num=1; num<=9; num++){
                if(isValid(r,c,num)){
                    graph[r][c] = num;
                    sudoku(r,c+1);
                    graph[r][c] = 0;
                    if(check) return;
                }
            }
        }
    }

    static boolean isValid(int r, int c, int num){
        for(int j=0; j<9; j++){
            if(graph[r][j] == num) return false;
        }

        for(int i=0; i<9; i++){
            if(graph[i][c] == num) return false;
        }

        int sr = (r/3) * 3;
        int sc = (c/3) * 3;
        for(int i=sr; i<sr+3; i++){
            for(int j=sc; j<sc+3; j++){
                if(graph[i][j] == num) return false;
            }
        }
        return true;
    }

    static void printGraph() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(graph[i][j]);
            }
            sb.append('\n');
        }
    }
    
}