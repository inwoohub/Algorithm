// B_2623(음악 프로그램).java
// 위상 정렬, 차수 사용

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] list; // 연결 리스트
    static int[] dgree; // 차수
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); // 첫번째 줄
        int singer = Integer.parseInt(st.nextToken());
        dgree = new int[singer+1];
        int pd = Integer.parseInt(st.nextToken());
        list = new ArrayList[singer+1];
        
        for(int i=1; i<singer+1; i++){
            list[i] = new ArrayList<>();
        }
    
        for(int i=0; i<pd; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            for(int k=0; k<size-1; k++){
                int B = Integer.parseInt(st.nextToken());
                list[A].add(B);
                dgree[B]++;
                A = B;
            }
        }
        
        int count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=singer; i++){
            if(dgree[i] == 0){
                q.offer(i);
                count++;
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur+"\n");
            for(int next : list[cur]){
                dgree[next]--;
                if(dgree[next]==0){
                    q.offer(next);
                    count++;
                }
            }
        }
        if(count == singer){
            System.out.print(sb);    
        }else{
            System.out.print(0);
        }
    }
}