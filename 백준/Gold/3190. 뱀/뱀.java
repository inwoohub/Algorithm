// 알고리즘
// 그래프 구현

import java.io.*;
import java.util.*;

public class Main{

    // 뱀 방향 변환 객체
    static class turn{
        int sec;
        char dir;
        turn(int sec, char dir){
            this.sec = sec;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException{

        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N: 그래프 크기
        int K = Integer.parseInt(br.readLine());  // K: 사과의 개수
        int[][] graph = new int[N][N];
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A-1][B-1] = 2; // 2: 사과 
        }
        int L = Integer.parseInt(br.readLine()); // L: 뱀의 방향 변환 횟수
        PriorityQueue<turn> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a.sec, b.sec) ); // 빠른순
        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 초
            String input = st.nextToken();
            char B = input.charAt(0); // 방향
            pq.offer( new turn(A,B) );
        }

        int ans = 0; // 출력 값
        
        // 1. 뱀 초기 위치 (0,0); , dir: 0-오른쪽, 1-아래, 2-왼쪽, 3-위 (세팅)
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, int[]> map = new HashMap<>(); // Key: idx, Value: 위치
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        int idx = 0; // 뱀의 머리 인덱스
        list.add(0);
        map.put(0, new int[]{0,0});
        int cdir = 0; // 현재 방향

        // 2. 첫 방향
        turn target = pq.poll();

        // 3. 게임 시작
        while(true){

            ans++; // 1초 증가

            // 4. 이동 가능한지 확인
            int[] cur = map.get(idx);
            int cy = cur[0];
            int cx = cur[1];
            int ny = cy + dy[cdir];
            int nx = cx + dx[cdir];

            // 이동 불가
            if(nx<0 || nx>N-1 || ny<0 || ny>N-1) break;
            if(graph[ny][nx]==1) break;


            // 이동 가능
            if(graph[ny][nx]==2){ // 사과 발견
                idx++; // 뱀 머리 값 변경
                list.add(idx);
                map.put(idx, new int[]{ny,nx});
                graph[ny][nx] = 1;
            } else { // 사과 x
                // 뱀 이동시키기
                for(int i=idx; i>=0; i--){
                    int[] pre = map.get(i); // 현재 값 저장
                    map.put(i, new int[]{ny, nx});
                    graph[ny][nx] = 1;
                    ny = pre[0];
                    nx = pre[1]; // next 값 업데이트
                    graph[ny][nx] = 0;
                }
            }

            // 5. 시간 검증 (x초가 끝난 뒤에 방향 틀기)
            if(ans == target.sec){
                if( target.dir == 'L' ){ // 좌측 회전
                    if(cdir == 0){
                        cdir = 3;
                    } else if (cdir == 1){
                        cdir = 0;
                    } else if (cdir == 2){
                        cdir = 1;
                    } else {
                        cdir = 2;
                    }
                }

                else{ // 우측 회전
                    if(cdir == 0){
                        cdir = 1;
                    } else if(cdir == 1){
                        cdir = 2;
                    } else if(cdir == 2){
                        cdir = 3;
                    } else {
                        cdir = 0;
                    }
                }
                // 큐에 더 있을 때 갱신
                if(!pq.isEmpty()){
                    target = pq.poll();
                }
            } // End of 4
            
        } // End of while

        // 데이터 출력
        System.out.print(ans);

        // 디버깅
        // for(int i=0; i<N; i++){
        //     for(int j=0; j<N; j++){
        //         System.out.print(graph[i][j]+" ");
        //     }
        //     System.out.println(" ");
        // }
        
    }
}