// ===================
// 알고리즘
// LIS (최장 증가 부분 수열)
// ===================
// 입력
// 8
// 1 8
// 3 9
// 2 2
// 4 1
// 6 4
// 10 10
// 9 7
// 7 6
// ===================
// 출력
// 3
// 1
// 3
// 4
// ===================

import java.io.*;
import java.util.*;

public class Main {

    // 전기줄
    static class Wire {
        int a, b;
        Wire(int a, int b) {
            this.a = a; this.b = b;
        }
    }

    public static void main(String[] args) throws Exception {

        // 0) 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        Wire[] wires = new Wire[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wires[i] = new Wire(a, b);
        }

        // 1) A 기준 정렬
        Arrays.sort(wires, ( (a,b) -> Integer.compare( a.a, b.a ) ));

        // 2) LIS on B (복원 포함)
        int[] tails = new int[N];          // tails[len] = LIS 길이 len+1의 최소 끝값
        int[] tailsIdx = new int[N];       // 그 tails 값을 만든 "wires의 인덱스"
        int[] prev = new int[N];           // prev[i] = i가 LIS에 들어갈 때 이전 원소 인덱스
        Arrays.fill(prev, -1);

        int len = 0;
        for (int i = 0; i < N; i++) {
            int b = wires[i].b;

            // lower_bound: 첫 번째 >= b 위치
            int pos = lowerBound(tails, len, b);

            tails[pos] = b;
            tailsIdx[pos] = i;

            if (pos > 0) prev[i] = tailsIdx[pos - 1];
            if (pos == len) len++;
        }

        // 3) LIS에 포함된 전깃줄 표시
        boolean[] inLIS = new boolean[N];
        int cur = tailsIdx[len - 1];
        while (cur != -1) {
            inLIS[cur] = true;
            cur = prev[cur];
        }

        // 4) 제거 대상 출력 (A 오름차순으로 출력해야 하므로 현재 정렬 순서가 이미 A 오름차순)
        StringBuilder sb = new StringBuilder();
        sb.append(N - len).append('\n');
        for (int i = 0; i < N; i++) {
            if (!inLIS[i]) sb.append(wires[i].a).append('\n');
        }

        System.out.print(sb);
    }

    // target 이 들어갈 위치 찾기
    static int lowerBound(int[] arr, int len, int target) {
        int lo = 0;
        int hi = len;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] >= target){
                hi = mid;
            }
            else{
                lo = mid + 1;    
            }
        }
        return lo;
    }
}