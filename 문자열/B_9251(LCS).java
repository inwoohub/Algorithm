import java.io.*;
import java.util.*;

public class Main{
    static String str1,str2;
    static int[][] DP;

    static void LCS(){
        DP = new int[str1.length()+1][str2.length()+1];
        for(int i=1; i<=str1.length(); i++){
            for(int k=1; k<=str2.length(); k++){
                DP[i][k] = Math.max(DP[i-1][k],DP[i][k-1]);
                if(str1.charAt(i-1) == str2.charAt(k-1)){
                    DP[i][k] = Math.max(DP[i][k], DP[i-1][k-1]+1);
                }
            }
        }    
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        str1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        str2 = st.nextToken();
        LCS();
        System.out.print(DP[str1.length()][str2.length()]);
    }
}