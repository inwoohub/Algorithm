import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> q = new PriorityQueue<>( (a,b) -> {
                if( a[1]!=b[1] ){
                    return a[1]-b[1];    
                }
                return a[0]-b[0];
                }
            );
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            q.offer(new int[]{A,B});        
        }

        
        int count = 0;
        int curStart = 0;
        int curEnd = 0;
        while(!q.isEmpty()){
            int[] next = q.poll();
            int nextStart = next[0];
            int nextEnd = next[1];
            if(curEnd <= nextStart){
                count++;
                curEnd = nextEnd;
            }
        }
        System.out.print(count);
    }
}