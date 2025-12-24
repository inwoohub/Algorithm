// PreOrder, InOrder, PostOrder
// 주어진 입력 : InOrder, PostOrder 
// 출력 : PreOrder

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int size;
    static int[] InOrder;
    static int[] PostOrder;
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        InOrder = new int[size];
        PostOrder = new int[size];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<size; i++){
            InOrder[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<size; i++){
            PostOrder[i] = Integer.parseInt(st.nextToken());
        }

        getPreOrder(0, size-1, 0, size-1);
        
        System.out.print(sb);
        
    }

    static void getPreOrder(int is, int ie, int ps, int pe){
        // is : InOrder start, ie : InOrder end
        // ps : PostOrder start, ps : PostOrder end

        if(is <= ie && ps <= pe){
            sb.append( PostOrder[pe] +" " ); // PostOrder[ps] == root

            int pos = is;
            for(int i = is; i <= ie; i++){
                if(InOrder[i] == PostOrder[pe]){
                    pos = i;
                    break;
                }
            }

            getPreOrder( is, pos-1, ps, ps+pos-is-1 );

            getPreOrder( pos+1, ie, ps+pos-is, pe-1 );

            
        }
    }
}
