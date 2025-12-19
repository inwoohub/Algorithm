// 부 배열 모두 구해서 리스트에 넣기
// 투포인터를 통해 쌍 찾기?

import java.io.*;
import java.util.*;

public class Main{
    static long target;
    static int sizeA, sizeB;
    static int[] arrA, arrB;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        target = (long)Integer.parseInt(st.nextToken()); //목표값

        // 첫 번째 배열
        st = new StringTokenizer(br.readLine()); // sizeA 크기
        sizeA = Integer.parseInt(st.nextToken());
        arrA = new int[sizeA+1]; // arrA 배열 생성
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=sizeA; i++){
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        // 두 번째 배열
        st = new StringTokenizer(br.readLine()); // sizeB 크기
        sizeB = Integer.parseInt(st.nextToken());
        arrB = new int[sizeB+1]; // arrB 배열 생성
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=sizeB; i++){
            arrB[i] = Integer.parseInt(st.nextToken());
        }
        
        // 모든 부배열구하기
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();
        listA = search(arrA);
        Collections.sort(listA);
        listB = search(arrB);
        Collections.sort(listB);

        //투포인터로 짝 찾기
        long result = search2(listA, listB);
        System.out.print(result);
        
    }

    //모든 부배열 구하고 리스트에 추가
    static ArrayList search(int[] arr){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1; i<arr.length; i++){
            int sum = 0;
            for(int k=i; k<arr.length; k++){
                sum = sum+arr[k];
                list.add(sum);
            }
        }
        return list;
    }

    // 짝 찾기
    static long search2(ArrayList<Integer> listA, ArrayList<Integer> listB){
        long count = 0;
        int leftPointer = 0;
        int rightPointer = listB.size()-1;

        while(leftPointer < listA.size() && rightPointer>=0){
            long sum = (long)listA.get(leftPointer)+ (long)listB.get(rightPointer);
            if( sum == target){
                long leftCount = 0;
                while( leftPointer<listA.size()-1 && listA.get(leftPointer).equals(listA.get(leftPointer+1))){
                    leftPointer++;
                    leftCount++;
                }

                long rightCount = 0;
                while( rightPointer>0 && listB.get(rightPointer).equals(listB.get(rightPointer-1)) ){
                    rightPointer--;
                    rightCount++;
                }
                count = count + (rightCount+1) * (leftCount+1);
                leftPointer++;
                rightPointer--;
            }
            else if(sum<target){
                leftPointer++;
            }else{
                rightPointer--;
            }
        }        
        return count;
    }
}