import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] arr = new int[N][N];
        int[][] result = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<N; k++){
                arr[i][k] = Integer.parseInt(st.nextToken())%1000;
                result[i][k] = arr[i][k];
            }
        }

        B--;

        while(B>0){
    
            if(B%2==1){
                int[][] cur =  new int[N][N];
                // cur 배열에 현재 배열 넣음
                for(int i=0; i<N; i++){
                    for(int k=0; k<N; k++){
                        cur[i][k] = result[i][k];
                    }
                }
                
                for(int i=0; i<N; i++){
                    for(int k=0; k<N; k++){
                        long sum = 0;
                        for(int x=0; x<N; x++){
                            long gop = ((long) cur[i][x]*arr[x][k]);
                            sum = sum+gop;
                        }
                        result[i][k] =(int) (sum % 1000);
                    }
                }
                B--;
            }
            int[][] cur =  new int[N][N];
            // cur 배열에 현재 배열 넣음
            for(int i=0; i<N; i++){
                for(int k=0; k<N; k++){
                    cur[i][k] = arr[i][k];
                }
            }

            int[][] nextA = new int[N][N];
            for(int i=0; i<N; i++){
                for(int k=0; k<N; k++){
                    long sum = 0;
                    for(int x=0; x<N; x++){
                        long gop = ((long)cur[i][x]*arr[x][k]);
                        sum = sum+gop;
                    }
                    nextA[i][k] = (int) (sum%1000);
                }
            }
            arr = nextA;
            B=B/2;
        }
        
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                sb.append(result[i][k]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}