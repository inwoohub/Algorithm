/**
알고리즘 :
    bfs + 우선 순위 큐 (MST)
    
문제 요약 :
    1. paths   은 길이 서로 연결되어있음을 표기 n ~ 200,000
    2. gates   은 출발지
    3. summits 은 산봉우리
    4. 출발지 -> 산봉우리 -> 기존 출발지 찾기
    5. intensity의 최솟값인 경우 찾기
    6. 최솟값이 만약 같다면, 산봉우리의 번호가 가장 낮은 등산코스 선택

전략 :
    1. 출발지를 기준으로 찾기 vs 산봉우리를 기준으로 출발지를 찾기
    2. 해당 문제에서는, 다 필요없다. intensity가 가장 작은게 최고다.
    3. 근데 가장 작은게 같을수도 있다. 그러면? -> 산봉우리 작은게 최고다.
    4. 결국엔 그리디하게 풀어도 출발지가 다른것들은 다 비교해야봐야할거같은데
    5. 근데 뎔국 돌아오는거라면, 출발지 -> 산봉우리 찾기나 산봉우리 -> 출발지 찾기나
    6. 그리고 왔던길 다시 돌아가도 되니까 결국 하나의 경로만 찾아주면 됨.
    7. 근데 다른 애들도 그 경로 써야하니까 결국 dfs 가 맞다
    8. 우선순위 큐에 작은거 위주로 담기? 되돌아가지만 못하면 되잖아.
    9. 그럼 우선순위 큐 쓰는게 최고같은데
*/

import java.util.*;

class Solution {
    
    static ArrayList<Node>[] list; // 노드간 연결 리스트
    static int[] answer;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        // 정답 반환용 배열 생성
        answer = new int[2];
        answer[0] = Integer.MAX_VALUE; // 산봉우리
        answer[1] = Integer.MAX_VALUE; // intensity
        
        // summits , gates 모두 ArrayList 에 담아주기 -> contains 사용 위함
        ArrayList<Integer> gateList = new ArrayList<>();
        ArrayList<Integer> summitList = new ArrayList<>();
        for(int gate : gates){
            gateList.add(gate);
        }
        for(int summit : summits){
            summitList.add(summit);
        }
        
        
        // 공통 세팅 (연결리스트 매핑)
        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        } // 리스트 생성 및 초기화
        
        // 연결 해주기
        for(int i=0; i<paths.length; i++){
            int[] path = paths[i];
            int A = path[0]; // 노드 A
            int B = path[1]; // 노드 B
            int C = path[2]; // 가중치
            list[A].add(new Node(B,C));
            list[B].add(new Node(A,C)); // 양방향 연결
        }
        
        Arrays.sort(summits);
        
        // 산봉우리 의 개수만큼 탐색해주기 (최소 찾아주기)
        for(int g=0; g<summits.length; g++){
            int summit = summits[g];
            boolean[] visited = new boolean[n+1]; // 방문 표기 배열
            
            // 우선순위 정해주기 (가중치가 가장 적은 순으로)
            PriorityQueue<Node> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a.weight, b.weight) );
            pq.offer(new Node(summit, 0)); // 초기 세팅 (산봉우리 넣어주기)
            
            // 큐가 빌 때까지 반복
            while(!pq.isEmpty()){
                
                Node cur = pq.poll();
                if(visited[cur.node]) continue;   // 이미 방문했었더라면 버리기
                
                visited[cur.node] = true;         // 꺼낸 것만 방문 처리
                
                if(gateList.contains(cur.node)){ // 도착지 발견 했을 때    
                    // answer 와 비교 (이미 산봉우리는 정렬되어있음으로 작을때만 갱신가능)
                    if(answer[1] > cur.weight){
                        answer[0] = summit;     // 도착 노드 (산봉우리)
                        answer[1] = cur.weight; // intensity
                    }
                    break;
                }
                
                if(answer[1] < cur.weight) break; // 더 크다면 강제 종료 (불가능)
                    
                for(Node next : list[cur.node]){
                    if(visited[next.node]) continue; // 이미 방문했다면, 패스
                    
                    if(summitList.contains(next.node)) continue; // 만약 그 길이 산봉우리라면, 패스
                    
                    int nextWeight = Math.max(next.weight, cur.weight);
                
                    
                    pq.offer(new Node(next.node, nextWeight)); // 가중치 더 높은거 넣기
                    
                }
                
            }
            
        }
        
        return answer;
    }
    
    static class Node{
        int node;  // 연결 노드
        int weight;// 가중치
        Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }
    }
}