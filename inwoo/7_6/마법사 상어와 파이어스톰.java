import java.io.*;
import java.util.*;

class Main{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};
    static int N, Q, SIZE;
    static int[][] arr;
    static int[][] moveArr;
    static int[] fireStorm;

    public static void main(String[] args) throws IOException{
        // 1. 초기 N, Q 그리고 얼음 배열 입력 받고 만들기
        init();

        // 2. Q만큼 fireStorm 시전
        for(int i=0; i<Q; i++){
            play(i); // fireStorm
            melt();  // 녹이기
        }

        // 3. 남아있는 얼음 A[r][c] 합 구하기
        int sum = getAllCount();

        // 4. 가장 큰 덩어리가 차지하는 칸 구하기
        int objetSize = getMaxSize();

        // 5. 정답 출력
        System.out.print(sum+"\n"+objetSize);

    }

    static int getMaxSize(){
        int count = 0;
        // bfs 으로 방문 탐색
        boolean[][] visited = new boolean[SIZE][SIZE]; // 방문 표기 배열
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                if(arr[i][j]>0){ // 얼음이 있다면
                    if(!visited[i][j]){ // 방문을 안했더라면
                        int curCount = 1;
                        q.offer(new int[]{i,j}); // 큐에 넣기
                        visited[i][j] = true; // 방문처리

                        // bfs 탐색으로 덩어리 개수 탐색
                        while(!q.isEmpty()){
                            int[] cur = q.poll();
                            int y = cur[0];
                            int x = cur[1];

                            for(int k=0; k<4; k++){
                                int ny = y+dy[k];
                                int nx = x+dx[k];
                                if(ny<0 || nx<0 || ny>=SIZE || nx>=SIZE){
                                    continue; // 범위 밖
                                }
                                // 방문 안한 덩어리
                                if( arr[ny][nx] >0 && !visited[ny][nx] ){
                                    visited[ny][nx] = true;
                                    curCount++;
                                    q.offer(new int[]{ny,nx});
                                }
                            }
                            count = Math.max(count, curCount); // 최대 갱신 시도
                        }
                    }
                }
            }
        }
        return count;
    }

    static int getAllCount(){
        int sum = 0;
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                sum += arr[i][j];
            }
        }
        return sum;
    }

    static int[][] snapshot(){
        int[][] copyArr = new int[SIZE][SIZE];
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                copyArr[i][j] = arr[i][j];
            }
        }
        return copyArr;
    }

    static int counting(int a, int b, int[][] copyArr){
        int count = 0;
        for(int i=0; i<4; i++){
            int x = a+dx[i];
            int y = b+dy[i];
            if(x<0 || x>=SIZE || y<0 || y>=SIZE) continue; // 맵이탈
            if(copyArr[x][y]>0){
                count++;
            }
        }
        return count;
    }

    static void melt(){
        int[][] copyArr = snapshot(); // 배열 스냅샷 뜨기

        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                if(arr[i][j] <= 0) continue; // 얼음 없으면 무시
                int count = counting(i, j, copyArr);
                if(count < 3 && arr[i][j] > 0){
                    arr[i][j] -= 1;
                }
            }
        }
    }

    static void rotation(int a, int b, int L, int curSize){

        // 원본 배열에서 90도 회전 시킨값 채워 넣기
        for(int i=0; i<curSize; i++){
            for(int j=0; j<curSize; j++){
                moveArr[a+j][b+curSize-i-1] = arr[a+i][b+j];
            }
        }
    }

    static void play(int i){
        int L = fireStorm[i]; // 부분 격자 크기 2^L
        int curSize = (int) Math.pow(2,L); // 부분 격자 사이즈

        // curSize 만큼 증가 (세로 구분)
        for(int j=0; j<SIZE; j=j+curSize){
            // (가로 구분)
            for(int k=0; k<SIZE; k=k+curSize){
                rotation(j, k, L, curSize); // (i,k) 시계방향으로 회전
            }
        }

        // 전부 바꿨으면 moveArr -> arr 덮어쓰기
        for(int j=0; j<SIZE; j++){
            // (가로 구분)
            for(int k=0; k<SIZE; k++){
                arr[j][k] = moveArr[j][k];
            }
        }

    }


    static void init() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        SIZE = (int) Math.pow(2,N);
        arr = new int[SIZE][SIZE];
        moveArr = new int[SIZE][SIZE];

        // 배열에 얼음 채워넣기
        for(int i=0; i<SIZE; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<SIZE; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // fireStorm 배열 미리 채워넣기
        st = new StringTokenizer(br.readLine());
        fireStorm = new int[Q];
        for(int i=0; i<Q; i++){
            fireStorm[i] = Integer.parseInt(st.nextToken());
        }
    }

}