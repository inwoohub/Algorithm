import java.io.*;
import java.util.*;

class Main{

    static int N;
    static int[] arr;
    static int[] preArr;
    static int[] viewArr;
    static ArrayList<Integer> list;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        // 1. [초기 세팅] N(수열의 크기), list(LIS), preArr(해당 수가 바라보는 인덱스), viewArr(해당 리스트의 최종 후보 값)
        init();

        // 2. LIS 만들기
        start();

        // 3. 값 구하기
        solution();

        // 4. 정답 출력
        System.out.println(sb);

    }

    static void solution() {
        // 1. 총 길이 출력
        int size = list.size();
        sb.append(size+"\n");

        // 2. LIS를 복구배열을 활용하여 출력 (반전을 위해 스택 사용)
        Stack<Integer> stack = new Stack<>();
        int lastIndex = size-1;
        lastIndex = viewArr[lastIndex];
        while(lastIndex != -1){
            stack.push(arr[lastIndex]);
            lastIndex = preArr[lastIndex];
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop()+" ");
        }
    }

    static void start() {
        for(int i=0; i<N; i++){
            int curValue = arr[i];
            // 1. list 사이즈가 0 이라면
            if(i == 0){
                insert(curValue, 0, null);
                continue;
            }

            // 2. 리스트에 들어갈 위치 탐색
            int index = Collections.binarySearch(list, curValue);
            // -> 탐색 성공시 양수, 실패 시 *(-1) 후 -1이 해당 들어갈 위치

            if(index >= 0) continue;        // [조건] 이미 존재 (대치 및 추가 x)
            index = Math.abs(index);       // 절대값 변경
            index = index-1;               // 들어갈 위치
            if(list.size() == index) {     // [조건] LIS의 수들 사이 가장 큰 경우
                insert(curValue, i, index);
            } else {                       // [조건] LIS의 수랑 사이에 바꿔야하는 경우
                update(curValue, i, index);
            }
        }
    }

    static void update(int curValue, int i, Integer index) {
        if(index == 0){                // [조건] 맨 앞인 경우
            preArr[i] = -1;
        } else {
            preArr[i] = viewArr[index-1]; // 바라 보는 인덱스 추가
        }
        viewArr[index] = i;               // 해당 리스트의 최종 후보 변경
        list.set(index, curValue);     // LIS 해당 인덱스 값 대치

    }

    static void insert(int curValue, int i, Integer index) {
        if(index == null){         // [조건] 리스트가 비어있는 경우 (맨 앞)
            list.add(curValue);    // LIS 리스트 추가
            preArr[0] = -1; // 바로 앞 연결된 것은 -1(맨 앞)
            viewArr[0] = 0; // i번째는 curValue 가 최종 후보 값
            return;
        }
        // 맨 뒤 추가되는 경우
        list.add(curValue);
        preArr[i] = viewArr[index-1];
        viewArr[index] = i;
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        list = new ArrayList<>();
        preArr = new int[N];
        viewArr = new int[N];
    }

}