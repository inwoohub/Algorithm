import java.io.*;
import java.util.*;

public class Main{
    
    static StringBuilder sb = new StringBuilder();
    static int sizeA, sizeB;
    static int[] arrA;
    static int[] arrB;
    static ArrayList<Integer> result;
    
    static void start(int indexA, int indexB){
        if(indexA >= sizeA || indexB >= sizeB) return;
        int[] curIndex = findMax(indexA, indexB);
        if(curIndex[0]!= -1){
            start(curIndex[0]+1, curIndex[1]+1);
        }
    }

    //최대값 찾고 인덱스 반환
    static int[] findMax(int indexA, int indexB){
        int[] curArr = new int[2];
        curArr[0] = -1;
        curArr[1] = -1;
        int curMax = 0;
        
        for(int i=indexA; i<sizeA; i++){
            for(int k=indexB; k<sizeB; k++){
                if(arrA[i] == arrB[k]){
                    if(curMax<arrA[i]){
                        curMax = arrA[i];
                        curArr[0] = i;
                        curArr[1] = k;
                    }
                }
            }
        }
        if(curArr[0] != -1){
            result.add(curMax);
        }
        return curArr;
    }
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        result = new ArrayList<>();
        sizeA = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arrA = new int[sizeA];
        for(int i=0; i<sizeA; i++){
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        sizeB = Integer.parseInt(br.readLine());
        arrB = new int[sizeB];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<sizeB; i++){
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        start(0,0);
        sb.append(result.size()+"\n");
        for(int i=0; i<result.size(); i++){
            sb.append(result.get(i)+" ");
        }
        
        System.out.print(sb);
    }
}