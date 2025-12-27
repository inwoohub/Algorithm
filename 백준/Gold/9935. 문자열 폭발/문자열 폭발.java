import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();
        int m = bomb.length;
        
        StringBuilder stack = new StringBuilder(s.length);

        for(char ch : s){
            stack.append(ch);
            if(stack.length()>=m && stack.charAt(stack.length()-1) == bomb[m-1]){
                boolean result = true;
                for(int i=0; i<m; i++){
                    if(stack.charAt(stack.length()-m+i) != bomb[i]){
                        result = false;
                        break;
                    }
                }    

                if(result){
                   stack.setLength(stack.length()-m); 
                }
            }
        }
        if(stack.length() == 0){
            System.out.println("FRULA");
        } else {
            System.out.println(stack.toString());
        }
    }
}