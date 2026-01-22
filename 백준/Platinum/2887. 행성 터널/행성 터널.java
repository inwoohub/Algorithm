// =================
// 알고리즘
// MST (최소신장트리) + Union-Find
// =================
// 5
// 11 -15 -15
// 14 -5 -15
// -1 -1 -5
// 10 -4 -1
// 19 -4 19
// =================
// 4
// =================

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();

    static class Node {
        int idx;
        int point;
        Node(int idx, int point){
            this.idx = idx;
            this.point = point;
        }
    }
    
    static class Edge {
        int start;
        int end;
        int weight;
        Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static ArrayList<Edge> edgeTree;
    static int[] parents;

    public static void main(String[] args) throws IOException{
        // ===== 데이터 입력 ======
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<Node> edgeX = new ArrayList<>();
        ArrayList<Node> edgeY = new ArrayList<>();
        ArrayList<Node> edgeZ = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            edgeX.add(new Node(i,x));
            edgeY.add(new Node(i,y));
            edgeZ.add(new Node(i,z));
        }

        // 1) edge (X,Y,Z) 정렬하기
        Collections.sort(edgeX,  (a,b) -> Integer.compare(a.point, b.point) );
        Collections.sort(edgeY,  (a,b) -> Integer.compare(a.point, b.point) );
        Collections.sort(edgeZ,  (a,b) -> Integer.compare(a.point, b.point) );

        // 2) 가까운 노드끼리 붙여서 트리 만들기
        edgeTree = new ArrayList<>();
        edgePlus(edgeX);
        edgePlus(edgeY);
        edgePlus(edgeZ);

        // 3) edgeTree 가중치대로 정렬하기
        Collections.sort(edgeTree, (a,b)->Integer.compare(a.weight, b.weight));

        
        // 4) MST + Union - Find : 최소 신장 트리 만들기

        // 4-1) Union - Find 초기화
        parents = new int[N];
        for(int i=0; i<N; i++){
            parents[i] = i;
        }

        // 4-2) MST 탐색 - 순차적으로 하나씩 꺼내가면서 싸이클 발생 안한다면, 연결?
        int ans = 0;
        for(int i=0; i<edgeTree.size(); i++){
            Edge curEdge = edgeTree.get(i);
            int curStart = curEdge.start;
            int curEnd = curEdge.end;
            int curWeight = curEdge.weight;

            int startParent = find(curStart);
            int endParent = find(curEnd);

            if( startParent == endParent ){ // 싸이클 발생
                continue;
            }

            union(startParent, endParent);
            
            ans += curWeight;
        }
        
        // ===== 테스트 =====

        // ===== 출력 =====
        System.out.print(ans);       
    }

    // 부모 합치기
    static void union(int x, int y){
        int xP = find(x);
        int yP = find(y);
        if(xP == yP) return;
        parents[xP] = yP;   
    }

    // 최상위 부모 찾기
    static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void edgePlus( ArrayList<Node> edge ){

        for(int i=0; i<edge.size()-1; i++){
            // 1) i, i+1 번째 노드 가져오기
            Node curNode = edge.get(i);
            Node nextNode = edge.get(i+1);

            // 2) 가중치 계산해서 edgeTree 에 추가
            int weight = Math.abs( curNode.point - nextNode.point );
            edgeTree.add(new Edge(curNode.idx, nextNode.idx, weight));
        }
    }
}