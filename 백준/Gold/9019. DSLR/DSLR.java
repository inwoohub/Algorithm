import java.io.*;
import java.util.*;

public class Main{
    static int[] arr;
    static boolean[] visited;

    static void find(int A, int B){
        Queue<Object[]> q = new LinkedList<>();
        q.offer(new Object[]{A,""});
        visited[A] = true;
        while(!q.isEmpty()){
            Object[] Objarr = new Object[2];
            Objarr = q.poll();
            int curA = (int) Objarr[0];
            String result = (String) Objarr[1];

            if(curA == B){
                System.out.println(result);
                break;
             }
            int S = Integer.MAX_VALUE;
            int D = (curA*2)%10000;
            if(curA == 0){
                S = 9999;
            }
            else{
                S = curA-1;
            }
            int L = (curA%1000)*10 + (curA/1000);
            int R = (curA%10)*1000 + (curA/10);

            if(!visited[D]){
                visited[D] = true;
                q.offer(new Object[]{D,result+"D"});
            }
            if(!visited[S]){
                visited[S] = true;
                q.offer(new Object[]{S,result+"S"});
            }
            if(!visited[L]){
                visited[L] = true;
                q.offer(new Object[]{L,result+"L"});
            }
            if(!visited[R]){
                visited[R] = true;
                q.offer(new Object[]{R,result+"R"});
            }   
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TestCase = Integer.parseInt(st.nextToken());

        for(int TC=0; TC<TestCase; TC++){
            arr = new int[10000];
            visited = new boolean[10000];
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            find(A,B);
        }
    }
}