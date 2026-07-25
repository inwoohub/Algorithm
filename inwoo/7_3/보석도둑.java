import java.util.*;
import java.lang.*;
import java.io.*;

class Main{

    static int N; // 보석의 개수
    static int K; // 가방의 개수
    static PriorityQueue<Jewel> j_pq;
    static PriorityQueue<Integer> b_pq;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        //1 . N & K 값 넣어주기
        init();

        // 2. 보석 우선 순위 큐 만들고 큐에 보석 넣기 (우선 순위 1. 가벼운 순, 2. 비싼순)
        Jewel_make();

        // 3. 가방 큐 만들기 만들고 가방 큐에 넣기 (우선 순위 1. 가벼운 순)
        Bag_make();

        // 4. 탐색 후 정답 출력
        System.out.println(start());
        
    }

    // 탐색
    static long start(){
        // 담을 수 있는 큐 만들기
        PriorityQueue<Integer> available_pq = new PriorityQueue<>( (a,b) -> Integer.compare(b,a) );
        long sum = 0; // 전부 더할 값
        
        while(!b_pq.isEmpty()){                 
            // 1. 현재 가방에서 담을 수 있는 보석 전부 담는 과정
            int curBag = b_pq.poll();           // 현재 가방의 크기
            while(!j_pq.isEmpty()){
                Jewel curJewel = j_pq.poll();   // 보석 꺼내기
                if( curJewel.w > curBag ){      // 만약, 현재 가방보다 무게가 더 크다면 복구 후 종료
                    j_pq.offer(curJewel);
                    break;
                }
                available_pq.offer(curJewel.v); // 보석 담기
            }

            // 2. 담을 수 있는 가방에서 가장 큰 것 하나 꺼내서 담고 다시 반복
            if(!available_pq.isEmpty()){
                sum += available_pq.poll();    
            }
        }
        return sum;
    }

    // 가방 큐 만들고 큐에 데이터 넣기
    static void Bag_make() throws IOException {
        b_pq = new PriorityQueue<>( (a,b) -> Integer.compare(a,b) ); // 작은 순으로
        for(int i=0; i<K; i++){
            b_pq.offer(Integer.parseInt(br.readLine())); // 가방 큐 쌓기
        }
    }

    // Jewel 큐 만들고 큐에 데이터 넣기
    static void Jewel_make() throws IOException {
        StringTokenizer st = new StringTokenizer("");
        j_pq = new PriorityQueue<>(
            (a,b) -> {
                if(a.w != b.w){
                    return Integer.compare(a.w, b.w); // 1. 가벼운 것이 1순위
                }
                return Integer.compare(b.v, a.v); // 2. 비싼 것이 2순위
            }
        );
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken()); // 보석의 무게
            int V = Integer.parseInt(st.nextToken()); // 보석의 가격
            j_pq.offer(new Jewel(W,V)); // 보석 큐 쌓기
        }
    }
    
    // 초기 N & K 값 넣기
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
    
    // 보석 객체 정의
    static class Jewel{
        int w; // 무게
        int v; // 가격
        Jewel(int w, int v){
            this.w = w;
            this.v = v;
        }
    }
    
}