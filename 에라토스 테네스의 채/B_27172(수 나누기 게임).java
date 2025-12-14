import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int N, max; // N : 플레이어 수, max : 최대값 (배열 생성 시 사용)
    static int[] arr; // 입력받은 배열
    static int[] pos; // 배열을 뒤집어서 인덱스로 만듦
    static int[] result; // 결과 (증가 / 감소)

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = new int[N];
        max = 0;
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }
        pos = new int[max+1];
        for(int i=0; i<N; i++){
            pos[ arr[i] ] = i+1;
        }

        for(int i=0; i<N; i++){
            find(arr[i]);
        }

        for(int i=0; i<N; i++){
            sb.append(result[i]+" ");
        }
        System.out.print(sb);
    }

    static void find(int cur){
        for(int i=cur*2; i<=max; i=i+cur ){
            if(pos[i]!=0){
                result[ pos[cur]-1 ]++;
                result[ pos[i]-1 ]--;
            }
        }
    }
}