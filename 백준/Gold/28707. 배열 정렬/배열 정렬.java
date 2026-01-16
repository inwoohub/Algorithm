// ---------------------
// 알고리즘
// bfs ( 우선순위 큐 사용 )
// ---------------------
// 4
// 1 4 3 2
// 4
// 1 2 4
// 2 3 3
// 3 4 2
// 1 4 10
    
import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static String result; // 찾아야하는 수
    static int ans = Integer.MAX_VALUE;
    static Map<String,Integer> map;
    
    // 조작 객체
    static class controller{
        int A;
        int B;
        int value;
        
        controller(int A, int B, int value){
            this.A = A;
            this.B = B;
            this.value = value;
        }
    }

    // 우선순위큐 객체
    static class pqObject{
        String arr;
        int value;

        pqObject(String arr, int value){
            this.arr = arr;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 원소 개수
        int[] arr = new int[N+1]; 
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine()); // 조작 개수
        controller[] ctrArr = new controller[M];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int cA = Integer.parseInt(st.nextToken());
            int cB = Integer.parseInt(st.nextToken());
            int cValue = Integer.parseInt(st.nextToken());
            controller c = new controller(cA, cB, cValue);
            ctrArr[i] = c;
        } // 배열 넣어두기

        map = new HashMap<>();
        result = findResult(arr); // 찾아야 하는 값
        
        // 우선순위 큐 탐색 시작
        PQ(arr, ctrArr);
        
        System.out.print(sb);        
    }

    // 우선순위큐 설계
    // pqObject( String arr, int value )
    static void PQ(int[] arr , controller[] ctr){
        
        PriorityQueue<pqObject> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a.value,b.value));
        String curInt = gender(arr); // 문자열로 반환
        map.put(curInt, 0);
        pq.offer(new pqObject(curInt, 0));
        while(!pq.isEmpty()){
            pqObject cur = pq.poll();
            int best = map.getOrDefault(cur.arr, Integer.MAX_VALUE);
            if (cur.value != best) continue; // outdated 스킵
            if( (cur.arr).equals(result)){ // 정답 찾음
                sb.append(cur.value);
                return;
            }
            for(int i=0; i<M; i++){
                controller curCtr = ctr[i];
                String nextStr = swap(cur.arr, curCtr.A, curCtr.B);
                int nextCost = cur.value + curCtr.value;
                int old = map.getOrDefault(nextStr, Integer.MAX_VALUE);
                if (nextCost < old) {
                    map.put(nextStr, nextCost);
                    pq.offer(new pqObject(nextStr, nextCost));
                }
            }
        }
        sb.append("-1");
    }

    // 스왑하기
    static String swap(String s, int x, int y){
        // x,y는 1~N (원소 인덱스)
        int i = (x - 1) * 2; // 0-based, 2글자 블록 시작
        int j = (y - 1) * 2;
    
        char[] ch = s.toCharArray();
    
        // i블록(2글자) <-> j블록(2글자) 스왑
        char t0 = ch[i], t1 = ch[i+1];
        ch[i]   = ch[j];
        ch[i+1] = ch[j+1];
        ch[j]   = t0;
        ch[j+1] = t1;
    
        return new String(ch);
    }

    // 배열 -> 정수로 반환
    static String gender(int[] arr){
        StringBuilder gen = new StringBuilder();
        for(int i=1; i<=N; i++){
            if(arr[i] < 10) gen.append('0'); // 1~9는 0 붙여 2자리
            gen.append(arr[i]);              // 10은 "10"
        }
        return gen.toString(); // 길이 = 2*N
    }

    // 최종 결과 찾기
    static String findResult(int[] arr){
        int[] newArr = arr.clone();
        Arrays.sort(newArr, 1, N+1);
    
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            if(newArr[i] < 10) sb.append('0');
            sb.append(newArr[i]);
        }
        return sb.toString();
    }
}