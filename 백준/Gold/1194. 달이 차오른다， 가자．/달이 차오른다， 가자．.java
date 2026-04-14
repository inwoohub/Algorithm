/**
알고리즘 :
    bfs + 비트마스킹

문제요약 :
    미로 탈출하기
    '.' : 이동 가능
    '#' : 벽 이동 x
    'a,b,c' : 소문자는 열쇠
    'A,B,C' : 문 (열쇠 있어야 이동 가능)
    '0' : 현재 민식이가 서있는 곳
    '1' : 출구 

    * 불가능 하다면 '-1' 출력

전략 :
    1. 
    a -> 0     d->3
    b -> 1     e->4
    c -> 2     f->5 로 치환해서 매핑 : [1<<6] 으로 비트로 어떤 열쇠가 있는지 표기
    
    1. 갈 수 있는 길이라면 이동
    2. 벽이라면 소멸
    3. 열쇠라면 비트로 추가 후 이동
    4. 문이라면 열쇠 확인 
        있다면 -> 이동
        없다면 -> 소멸
    5. 출구라면 return 이동 거리 만큼
    6. 출구 못찾으면 return -1
    
*/

import java.util.*;
import java.io.*;

public class Main{

    static HashMap<Character, Integer> compression; // 열쇠: 문자 -> Integer로 변환기
    static int h, w;
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,-1,1};
    static int[][][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken()); // 세로
        w = Integer.parseInt(st.nextToken()); // 가로

        char[][] map = new char[h][w]; // 맵
        visited = new int[h][w][1<<6]; // 방문 처리
        compression = new HashMap<>();
        setCompression(); // 좌표 압축

        ArrayDeque<int[]> q = new ArrayDeque<>();

        for(int i=0; i<h; i++){
            String input = br.readLine();
            for(int j=0; j<w; j++){
                Arrays.fill(visited[i][j], -1); // visited 배열 전부 -1 -> 방문 안한 경우
                map[i][j] = input.charAt(j);
                if(map[i][j]=='0'){
                    q.offer(new int[]{i,j,0,0}); // 0 이라면 시작점으로 지정
                    visited[i][j][0] = 0; // 시작점 열쇠 없이 거리 0으로 두기
                }
            }
        }

        // 큐가 비어있을 때 까지 반복
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];      // 현재 y
            int cx = cur[1];      // 현재 x
            int curDist = cur[2]; // 현재 거리
            int curMask = cur[3]; // 현재 가지고 있는 키
            
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if(ny<0 || nx<0 || ny>=h || nx>= w) continue; // 맵 밖으로 가면 버리기

                if(map[ny][nx] == '.' || map[ny][nx] == '0'){ // 이동가능한 빈칸
                    if(visited[ny][nx][curMask] == -1 ){
                        visited[ny][nx][curMask] = curDist+1;
                        q.offer(new int[]{ny,nx,curDist+1, curMask});
                    }
                }

                else if(map[ny][nx] == '#'){ // 벽
                    continue;
                }

                else if( (int) map[ny][nx] >= 97 && (int) map[ny][nx] <= 122 ){ // 열쇠
                    int nextMask = ( curMask | ( 1<<compression.get(map[ny][nx]) )); // 열쇠 담기
                    if(visited[ny][nx][nextMask] == -1){
                        visited[ny][nx][nextMask] = curDist+1;
                        q.offer(new int[]{ny,nx,curDist+1, nextMask});
                    }
                }

                else if( (int) map[ny][nx] >= 65 && (int) map[ny][nx] <= 90 ){ // 문
                    if( (curMask &  (1<<compression.get(map[ny][nx]) ) ) != 0 ){ // 열쇠가 있는 경우
                        if(visited[ny][nx][curMask] == -1){
                            visited[ny][nx][curMask] = curDist+1;
                            q.offer(new int[]{ny,nx,curDist+1, curMask});
                        }
                    }
                }

                else if(map[ny][nx] == '1'){ // 출구
                    System.out.println(curDist+1);
                    return;
                }   
            }
        }
        System.out.println("-1");
    }

    // 문자 -> Integer 로 변환 (문제에서 a~f 열쇠만 주어짐)
    static void setCompression(){
        compression.put('a',0);
        compression.put('b',1);
        compression.put('c',2);
        compression.put('d',3);
        compression.put('e',4);
        compression.put('f',5);
        compression.put('A',0);
        compression.put('B',1);
        compression.put('C',2);
        compression.put('D',3);
        compression.put('E',4);
        compression.put('F',5);
    }    
}