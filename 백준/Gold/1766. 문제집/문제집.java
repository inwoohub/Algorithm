// --------------------------------------
// 알고리즘
// 조건 1. N 개의 문제는 모두 풀어야함
// 조건 2. 먼저 푸는 것이 좋은 문제가 있다면, 먼저 푸는 것이 좋은 문제를 먼저 풀어야 함.
// 조건 3. 가능하면 쉬운 문제부터 풀어야 함

// 위상 정렬 + 
// 1. 진입 차수가 == 0 부터 우선순위 큐에 넣기 (위상 정렬+우선순위 큐)
// 2. 다 꺼낸 후 다음 노드 --
// 1~2 반복

// --------------------------------------
// 변수 사용
// N : 문제의 수
// M : 좋은 문제에 대한 정보의 개수
// degree : 진입 차수 (0 일시 수행 가능)
// list : 노드가 가르키는 곳
// pq : 우선순위 큐
// --------------------------------------

import java.io.*;
import java.util.*;

public class Main{    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] degree = new int[N+1];
        ArrayList<Integer>[] list = new ArrayList[N+1];
        
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>( (a,b)
                                            -> Integer.compare(a,b));
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[A].add(B);
            degree[B]++;
        }

        while(true){
            boolean check = false;
            for(int i=1; i<=N; i++){
                if(degree[i] == 0){
                    pq.offer(i);
                    degree[i] = -1;
                    check = true;
                }
            }

            while(!pq.isEmpty()){
                int A = pq.poll();
                sb.append(A+" ");
                for(int next : list[A]){
                    degree[next]--;
                    if(degree[next]==0){
                        pq.offer(next);
                        degree[next] = -1;
                    }
                }
                
            }

            if(!check){
                break;
            }
        }

        System.out.print(sb);
    }
    
}