// ----------------------------
// 알고리즘
// 1. 입구 리스트에 넣어두기
// 2. dfs 로 입구별로 탐색
// 3. 열쇠 습득 한 경우 (boolean) 일단 하던거 마저 끝내고, 다시 입구로
// 4. 없다면, 종료
// + 열쇠는 Map 으로 미리 T/F 로 구분
// ----------------------------
// 65 ~ 90 대문자
// 97 ~ 122 소문자
// ----------------------------

import java.io.*;
import java.util.*;

public class Main{

    static int N,M;
    static char[][] graph;
    static Map<Character,Boolean> map; // 키가 들어있는지 null 이라면 키 없음
    static ArrayList<int[]> list; // 시작점이 될 수 있는 지점
    static boolean[][] visited; // dfs 방문 위치 저장
    static boolean[][] file; // 찾은 문서 위치 (true : 이미 찾음 / false : 새로운 문서)
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean newKey = true;
    static int ans;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        // 테스트케이스
        for(int tC=0; tC<testCase; tC++){
            ans = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new HashMap<>();
            list = new ArrayList<>();
            graph = new char[N][M];
            file = new boolean[N][M];
            for(int i=0; i<N; i++){
                String input = br.readLine();
                for(int j=0; j<M; j++){
                    graph[i][j] = input.charAt(j);
                    if(graph[i][j] != '*' && (i==0 || j==0 || i==N-1 || j==M-1) ){ // 출입 가능 + 벽이 아닌 경우
                        list.add(new int[]{i,j});
                    }
                }
            }
            String input = br.readLine();
            if( input.charAt(0) != 0 ){
                for(int i=0; i<input.length(); i++){
                    map.put(input.charAt(i), true);
                }
            } // 데이터 매핑 완료

            start(); // 문서 찾기
            System.out.println(ans);
        }        
    }

    // 문서 찾기 시작
    static void start(){
        // 새로운 키 못 찾을 때 까지 반복
        newKey = true;
        while( newKey ){
            visited = new boolean[N][M]; // 방문 리셋
            newKey = false;
            for(int i=0; i<list.size(); i++){
                int[] cur = list.get(i);
                int x = cur[0];
                int y = cur[1];
                
                // 문서 찾기
                // 리턴은 bool 형태로 (새로운 키가 있다면 true, 없다면 false);

                // 1. 시작점이 땅인 경우
                if(graph[x][y] == '.'){
                    dfs(x,y);    
                }

                // 2. 시작점이 문서인 열쇠인 경우
                else if ((int)graph[x][y]>=97 && (int)graph[x][y]<=122){
                    map.put(graph[x][y],true); // 열쇠 저장
                    graph[x][y] = '.'; // 열쇠 지우기
                    newKey = true; // 열쇠 주움 (업데이트)
                    dfs(x,y);
                }

                // 3. 시작점이 문일 경우
                else if( (int)graph[x][y]>=65 && (int)graph[x][y]<= 90){
                    char mapping = (char) ((int)(graph[x][y]) + 32 );
                    if(map.get(mapping)!=null){
                        graph[x][y] = '.'; // 키가 있다면 문 개방해두기
                        dfs(x,y); 
                    }
                }

                // 4. 시작점이 문서일 경우
                else if(graph[x][y] == '$'){
                    if(!file[x][y]){
                        file[x][y] = true; // 문서 주움
                        graph[x][y] = '.'; // 쓴 문서 일반 땅으로 변환
                        ans++;
                    }
                    dfs(x,y);
                }
            }
        }
        
    }

    // dfs (이동 가능한 경로 dfs 알고리즘)
    static void dfs(int x, int y){
        
        visited[x][y] = true; 
        for(int i=0; i<4; i++){
            int cx = x + dx[i];
            int cy = y + dy[i];
            if( cx<0 || cx>=N || cy<0 || cy>=M ) continue; // 범위 밖
            if(!visited[cx][cy] && graph[cx][cy]!='*'){ // 접근 가능 한 경우

                // 1. '.' 점 : 빈 공간
                if(graph[cx][cy] == '.'){
                    visited[cx][cy] = true;
                    dfs(cx,cy);    
                }

                // 2. 'F' 대문자 : 문
                if( (int)graph[cx][cy]>=65 && (int)graph[cx][cy]<= 90){
                    
                    // 열쇠 있는지 조회
                    // 대문자 -> 소문자 변환
                    char mapping = (char) ((int)(graph[cx][cy]) + 32 );
                    if(map.get(mapping)!=null){
                        visited[cx][cy] = true;
                        graph[cx][cy] = '.'; // 키가 있다면 문 개방해두기
                        dfs(cx,cy); 
                    }
                }
                
                // 3. 'k' 소문자 : 열쇠
                if ((int)graph[cx][cy]>=97 && (int)graph[cx][cy]<=122){
                    visited[cx][cy] = true;
                    map.put(graph[cx][cy],true); // 열쇠 저장
                    graph[cx][cy] = '.'; // 열쇠 지우기
                    newKey = true; // 열쇠 주움 (업데이트)
                    dfs(cx,cy);
                }

                // 4. '$' 달러 : 문서
                if(graph[cx][cy] == '$'){
                    visited[cx][cy] = true;
                    if(!file[cx][cy]){
                        file[cx][cy] = true; // 문서 주움
                        graph[cx][cy] = '.'; // 쓴 문서 일반 땅으로 변환
                        ans++;
                    }
                    dfs(cx,cy);
                }
            }
        }
    }
}