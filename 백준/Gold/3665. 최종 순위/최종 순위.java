/**
알고리즘 : 
    위상정렬 (Queue)

문제 정의 :
    1. '작년 순위에서 상대적인 순위가 바뀐 팀' 이라는 데이터 제공
    2. '올해 순위 팀 예측하기'
    3. '?' : 확실한 순위를 찾을 수 없는 경우
        -> 진행 과정에서 큐에 동시에 2개 이상의 원소가 들어가는 경우
    4. 'IMPOSSIBLE' : 데이터 일관성이 없어 순위를 정할 수 없는 경우
        -> 사이클이 발생하는 곳에서 차수가 0인 노드가 없는 경우

전략 : 
    1. 랭킹 배열 생성 | idx: 등수, value: 노드
    2. HashMap 생성 | Key: Node, value: HashMap<Integer,boolean> - 해당 Integer는 연결되어있는 노드, boolean는 연결 상태
        ex) map.get(5).get(1) -> 5 -> 1 서로 연결 되어있는지 아닌지 확인 (2차원 배열보다 조금 더 빠를거라 판단함)
    3. 완료 상태 배열 생성 | idx: 노드, value: T/F 이미 썼는지 안 썼는지
    4. 차수 배열 생성 | idx: 노드 , value: 차수
    5. q 사이즈 검사
        size > 1 이라면 :
            -> "?" 출력
        size == 0 이라면 :
            -> "IMPOSSIBLE" : 하나라도 미 완료가 있다면,
            -> "성공" : 전부 완료
        size == 1 이라면 :
            5-1. 해당 큐 빼고 인접한 리스트들 차수 -1 씩 제거
            5-2. 만약 차수가 0인것을 발견한다면 q에 넣어주기
*/

import java.util.*;
import java.io.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int[] rankIdx; // idx: 등수, value: 노드
    static int[] rank;    // idx: 노드, value: 등수
    static int[] degree;  // idx: 노드, value: 차수
    static boolean[] visited; // idx: 노드 , value : 사용 처리 (T/F)
    static HashMap<Integer, HashMap<Integer,Boolean> > map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken()); // 테스트 케이스 수        
        
        for(int tC=0; tC<testCase; tC++){
            sb = new StringBuilder(); // 스트링 빌더 생성
            
            int N = Integer.parseInt(br.readLine()); // 노드의 개수
            rankIdx = new int[N+1];
            rank = new int[N+1];
            degree = new int[N+1];
            visited = new boolean[N+1];
            map = new HashMap<>();
            
            st = new StringTokenizer(br.readLine()); // 노드 차례대로
            for(int i=1; i<=N; i++){
                int A = Integer.parseInt(st.nextToken());
                rankIdx[i] = A;
                rank[A] = i;
                degree[A] = i-1; // 초기 차수 세팅
                HashMap<Integer, Boolean> map2 = new HashMap<>();
                map.put(i, map2); // map 초기 세팅
            }
            
            for(int i=1; i<=N; i++){
                int curNode = rankIdx[i];
                for(int j=i+1; j<=N; j++){
                    int nextNode = rankIdx[j];
                    map.get(curNode).put(nextNode, true); // curNode의 맵에서 nextNode 연결하기
                }
            } // 연결 상태 매핑
            
            int M = Integer.parseInt(br.readLine()); // 순위 변동 개수
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                // A가 순위가 더 빠른 경우 (B가 앞으로)
                if(rank[A] < rank[B]){
                    // A -> B 연결 상태 끊어주기 & 차수 변동
                    map.get(A).put(B,false);
                    degree[B]--;
                    // B -> A 연결 상태 추가하기 & 차수 변동
                    map.get(B).put(A,true);
                    degree[A]++;
                }
                // B가 순위가 더 빠른 경우 (A가 앞으로)
                else {
                    // B -> A 연결 상태 끊어주기 & 차수 변동
                    map.get(B).put(A,false);
                    degree[A]--;
                    // A -> B 연결 상태 추가하기 & 차수 변동
                    map.get(A).put(B,true);
                    degree[B]++;
                }
            } // 데이터 매핑 완료

            // 초기 큐 생성 및 차수 0 인 것들 큐에 넣어주기
            Deque<Integer> q = new ArrayDeque<>();
            for(int i=1; i<=N; i++){
                if(degree[i]==0){
                    q.offer(i);
                    visited[i] = true; // 사용처리
                    sb.append(i+" ");
                }
            }

            while(true){
                // 5. 큐 사이즈 검사
                if(q.size() > 1){
                    System.out.println("?");
                    break;
                }
                else if(q.size() == 0){
                    boolean check = true;
                    // 상태 검사
                    for(int i=1; i<=N; i++){
                        if(!visited[i]){
                            check = false;
                            break;
                        }                        
                    }
                    if(!check) System.out.println("IMPOSSIBLE");
                    else System.out.println(sb);
                    break;
                }
                else{
                    search(q);
                }
            } //End while            
        } // End testCase
    } // End main

    // search 함수 : 5-1, 5-2 수행
    static void search(Deque<Integer> q){
        int curNode = q.poll();
        HashMap<Integer, Boolean > searchMap = map.get(curNode);
        for( int nextNode : searchMap.keySet() ){
            if(searchMap.get(nextNode)){ // curNode 와 연결되어있는 nextNode
                if(visited[nextNode]) continue; // 이미 사용한 것은 넘어가기
                degree[nextNode]--; // 5-1. 차수 감소
                if(degree[nextNode] == 0){
                    q.offer(nextNode); // 5-2. 만약 차수가 0 이라면, 큐에 넣어주기
                    visited[nextNode] = true; // 사용 완료 처리
                    sb.append(nextNode+" ");
                }
            }
        }
    } // End search   
}