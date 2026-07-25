import java.io.*;
import java.util.*;

class Main{

    static int N;

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{

        // 1. 초기 N 입력받기
        init();

        // 2. N번 반복하면서 값 넣기 -> 가운데 값 찾기
        search();

        // 3. 정답 출력
        System.out.println(sb);

    }

    static void search() throws IOException{
        // 최소 힙 (큰 수가 들어가야함)  -> 홀수일 때 여기서 뽑기
        PriorityQueue<Integer> minHeap = new PriorityQueue<>( (a,b) -> Integer.compare(a,b) );

        // 최대 힙 (작은 수가 들어가야함) -> 짝수일 때 여기서 뽑기
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a,b) -> Integer.compare(b,a) );

        for(int i=0; i<N; i++) {
            int A = Integer.parseInt(br.readLine());
            int minSize = minHeap.size();
            int maxSize = maxHeap.size();

            // 완전 처음인 경우
            if(minHeap.size()==0){
                minHeap.offer(A);
                sb.append(A+"\n");
                continue;
            }

            // 1. 크기가 같은 경우
            if(minSize == maxSize) {
                // 최대힙과 비교
                int rootValue = maxHeap.poll();

                if(A >= rootValue ) {
                    maxHeap.offer(rootValue);
                    minHeap.offer(A);
                } else {
                    maxHeap.offer(A);
                    minHeap.offer(rootValue);
                }
                sb.append(minHeap.peek()+"\n");
            }

            // 2. 크기다 다른 경우 (최소 힙이 더 큼)
            else {
                int rootValue = minHeap.poll();

                if(A >= rootValue){
                    minHeap.offer(A);
                    maxHeap.offer(rootValue);
                } else {
                    minHeap.offer(rootValue);
                    maxHeap.offer(A);
                }
                sb.append(maxHeap.peek()+"\n");
            }
        }
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

}