// 위상 정렬 ( 줄세우기 )

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        
        // LinkedList 생성 및 degree 생성
        LinkedList<Integer>[] list = new LinkedList[nodeSize+1];
        for(int i=1; i<=nodeSize; i++){
            list[i] = new LinkedList<>();
        }
        int[] degree = new int[nodeSize+1];
        

        // 간선 추가 & 차수 증가
        for(int i=0; i<V; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[A].add(B);
            degree[B]++;
        }

        // 큐를 통해 차수가 0인 것부터 넣어줌
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=nodeSize; i++){
            if(degree[i]==0){
                q.offer(i);    
            }
        }

        // 넣었던 0 인것부터 꺼내면서 연결된 차수 -1 해주고, 0이라면 큐에 넣어줌
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur+" ");
            for(int next : list[cur]){
                degree[next]--;
                if(degree[next] == 0){
                    q.offer(next);
                }
            }
        }
        System.out.print(sb);
    }
}