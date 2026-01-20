// =================
// 알고리즘
// Union - Find + CCW 같다.
// CCW 로 먼저 겹치는거 찾고, 있다면 바로 Union-find 처럼 합치기
// 1. for문을 통해 겹치는거 없으면 패스 <-> 근데 나중가면 또 for문 돌릴필요가없지않나.. 왜냐면 이미 앞에있는애가 했잖아
// 2. 그럼 아니즤.. 아 그럼 2중 for문을 쓰되.. 첫번째는 for(i=0~) 로 했다면, 두번째 for문은  for(j=i~) 로 하면 앞에꺼 안봐도 되잖압
// 3. 그거다.. 그걸로 해야한다. 근데 그럼 어차피 0~N 으로 넣어놨으니 for(Node next : list) 직접 꺼내지말고 idx 로 관리하자.
// =================
// 3
// 1 1 2 3
// 2 1 0 0
// 1 0 1 1
// =================
// 출력
// 1 : 그룹의 수
// 3 : 크기가 가장 큰 그룹의 속한 선분의 수
// =================

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    
    // p : 부모노드, x1, y1, x2, y2
    static class Node{
        int p, x1, y1, x2, y2;
        Node(int p, int x1, int y1, int x2, int y2){
            this.p = p;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static int N; // 선분 개수
    static ArrayList<Node> list;
    static int[] parent; // 같은 그룹 표기
    static int[] ans;
    

    public static void main(String[] args) throws IOException{

        // ==== 데이터 입력 ====
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        parent = new int[N];
        ans = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            parent[i] = i;
            int p = i;
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            list.add(new Node(p,x1,y1,x2,y2));
        }

        start(); // 그룹 만들기
        unionFind(); // 부모 찾기

        // ==== 답 도출 ====
        int count = 0;
        int max = 0;
        int[] linkCount = new int[N];
        for(int i=0; i<N; i++){
            if( linkCount[ parent[i] ] == 0 ){ // 처음 방문 시
                count++;
            }
            linkCount[ parent[i] ] ++;
            max = Math.max( max, linkCount[ parent[i] ] );
        }

        // ===== 테스트 =====
        // for(int i=0; i<N; i++){
        //     sb.append(i+" : "+parent[i]+"\n");
        // }
        
        // ==== 출력 ====
        sb.append(count+"\n"+max);
        System.out.print(sb);   
    }

    static void start(){

        // 1. 노드 인덱스로 접근
        for(int i=0; i<N-1; i++){
            Node cN = list.get(i);

            // 2. 겹쳐지는 노드가 있는지 확인
            for(int j=i+1; j<N; j++){
                Node nN = list.get(j);

                // 두 선분이 겹치는지 확인  -1 / 0 / 1 로 서로의 위치 확인
                long res1 = CCW( cN.x1, cN.y1, cN.x2, cN.y2, nN.x1, nN.y1 ) * CCW( cN.x1, cN.y1, cN.x2, cN.y2, nN.x2, nN.y2 );
                long res2 = CCW( nN.x1, nN.y1, nN.x2, nN.y2, cN.x1, cN.y1 ) * CCW( nN.x1, nN.y1, nN.x2, nN.y2, cN.x2, cN.y2 );
                if( res1 == 0 && res2 == 0 ){ // 겹쳐짐 확인 (둘중하나 0 이거나, 둘다 0 이거나)
                    if( Math.min(cN.x1, cN.x2)<=Math.max( nN.x1, nN.x2 ) && Math.min( nN.x1, nN.x2 )<=Math.max( cN.x1, cN.x2 ) && 
                        Math.min( cN.y1, cN.y2 )<=Math.max( nN.y1, nN.y2 ) && Math.min( nN.y1, nN.y2 )<=Math.max( cN.y1, cN.y2 ) ){ // 교차 성공
                            int findA = parents(i);
                            int findB = parents(j);
                            if(findA != findB){ // 둘 이 다르다면 !
                                parent[findA] = findB;
                            }
                        }
                    else{ // 교차 안함
                        continue;
                    }
                }
                else if( res1<=0 && res2<=0 ){ // 교차 성공
                    int findA = parents(i);
                    int findB = parents(j);
                    if(findA != findB){ // 둘 이 다르다면 !
                        parent[findA] = findB;
                    }
                }
                else{ // 같은 방향 (교차 x)
                    continue;
                }   
            }   
        }
    }

    static void unionFind(){
        for(int i=0; i<N; i++){
            parent[i] = parents(i);
        }
    }

    // CCW : 평면상의 3개의 점의 위치 관계를 판별하는 알고리즘
    static long CCW(int x1, int y1, int x2, int y2, int x3, int y3){
        long res = ( x1*y2 + x2*y3 + x3*y1 ) - ( x1*y3 + x3*y2 + x2*y1 );
        if(res==0) return 0;
        if(res>0) return 1;
        else return -1;
    }

    // Union (부모 합치기)
    static int parents(int i){
        if(parent[i] == i){
            return i;
        }
        return parent[i] = parents(parent[i]);
    }
    
}
