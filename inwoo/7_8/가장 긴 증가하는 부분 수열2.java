import java.io.*;
import java.util.*;

class Main{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        // 1. N & arr 배열 입력 받기
        init();

        // 2. 가장 긴 수열의 길이 탐색
        int answer = start();

        // 3. 정답 출력
        System.out.println(answer);
        
    }

    static int start(){
        ArrayList<Integer> list = new ArrayList<>(); // 1. 리스트 생성
        int lastValue = arr[0];                      // 첫 번째 수를 기준으로 시작
        list.add(lastValue);
        for(int i=1; i<N; i++){
            int cur = arr[i];                        // 현재 수
            if(cur < lastValue){                     // 2. Update 가능한 경우 (이전 덮어쓰기)
                update(list, cur);                   // Update
            } else if (cur > lastValue) {            // 3. 추가가 가능한 경우 (추가)
                list.add(cur);                       // Insert
            }
            lastValue = list.get(list.size()-1);     // 마지막 값 업데이트
        }
        return list.size();                          // 리스트 사이즈 반환 (증가하는 배열 가장 큰 길이)
    }

    static void update(ArrayList<Integer> list, int value){
        int index = binarySearch(list, value);       // 2진 탐색으로 들어갈 위치 탐색
        list.set(index, value);                      // 해당 인덱스 업데이트
    }

    static int binarySearch(ArrayList<Integer> list, int value){
        int start = 0;                               // 시작 위치
        int end = list.size()-1;                     // 마지막 위치
        int mid = 0;

        while(start<end){
            mid = (start+end) / 2;
            int midValue = list.get(mid);

            if(value > midValue){                    // 좌측 구간
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;                                // value 보다 큰 첫번 째 위치
    }

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
    
}