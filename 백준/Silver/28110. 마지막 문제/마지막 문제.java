/**
Good Bye BOJ !

알고리즘 :
    그리디 + 정렬

문제 요약 :
    2 ~ 3000 사이인 N 개 문제 주어짐.
    추가할 마지막 문제의 난이도 출력하는데
    문제의 차이의 최솟값이 가장 큰 문제를 출력
    없다면 -1 출력

전략 :
    1. 난이도 오름 차순으로 정렬하기
    2. 두 점 사이의 간격 구하기
    3. 두 점 사이의 간격이 가장 큰값의 중간값이 해당 답
    -> 단 하나도 없는 경우는 모든 경우의 중간값이 0 이라는 뜻
*/

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        
        Collections.sort(list); // 오름차순으로 정렬하기

        ArrayList<Node> nodeList = new ArrayList<>();
        
        for(int i=0; i<N-1; i++){
            int A = list.get(i);
            int B = list.get(i+1);
            int mid = (A+B)/2; // 중간 지점
            nodeList.add(new Node ( mid, Math.min( (mid-A ), (B-mid) )));    
        }
        
        Collections.sort( nodeList, (a,b) -> {
            if(a.dist == b.dist){ // 거리 같으면
                return Integer.compare(a.point, b.point); // 오름 차순
            }
            return Integer.compare(b.dist, a.dist); // 거리 내림 차순
        });

        if(nodeList.get(0).dist != 0){
            System.out.print(nodeList.get(0).point);
        }
        else {
            System.out.print(-1);
        }
        
    }

    static class Node{
        int point;
        int dist;
        Node(int point, int dist){
            this.point = point;
            this.dist = dist;
        }
    }
}
