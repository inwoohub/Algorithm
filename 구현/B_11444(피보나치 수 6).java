// F[n+2] = F[n+1]+F[n]
// F[n+1] = F[n+1]+ 0*F[n]

// [F[n+2]] = [ 1 1 ][ F[n+1] ]
// [F[n+1]] = [ 1 0 ][  F[n]  ]  < 처럼 행렬로 만듦
//  ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
// [F[n+2]] = [ 1 1 ]^n[ 1 ]
// [F[n+1]] = [ 1 0 ]  [ 0 ] 
//  ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ex) n=9 인 경우
// [F[8+1]] = [ 34 21 ]^n[ 1 ]
// [ F[8] ] = [ 21 13 ]  [ 0 ]
//          = [ 32 ]
//            [ 21 ]
// [ F[9] ] = 34
// [ F[8] ] = 21


import java.io.*;
import java.util.*;

public class Main{

    final static long mod = 1000000007;
    static long[][] origin = {{1,1}, {1,0}};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] A = {{1,1}, {1,0}};

        long N = Long.parseLong(br.readLine());

        System.out.println(pow(A, N-1)[0][0]);
        
    }

    static long[][] pow(long[][] A, long exp){
        if(exp==1 || exp ==0){
            return A;
        }    

        long[][] ret = pow(A,exp/2);

        ret = multiply(ret,ret);

        if(exp%2==1L){
            ret = multiply(ret, origin);
        }

        return ret;
    }

    public static long[][] multiply(long[][] o1, long[][] o2){
        long[][] ret = new long[2][2];
        
        ret[0][0] = ( (o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0]) ) % mod;
        ret[0][1] = ( (o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1]) ) % mod;
        ret[1][0] = ( (o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0]) ) % mod;
        ret[1][1] = ( (o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1]) ) % mod;

        return ret;
    }
    
}