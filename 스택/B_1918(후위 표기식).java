// stack 사용  - pust(), peek(), pop()

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<input.length(); i++){
            char A = input.charAt(i);
            // 문자가 들어온 경우
            if(A!= '+' && A!= '-' && A!= '*' && A!= '/' && A!= '(' && A!= ')'){
                sb.append(A);
            }
            else {
                if(A=='('){
                    stack.push(A);
                }
                else if( A==')' ){
                    while(!stack.isEmpty()){
                        char curChar = stack.pop();
                        if(curChar!='('){
                            sb.append(curChar);
                        }
                        if(curChar=='('){
                            break;
                        }
                    }
                }
                else {
                    while(!stack.isEmpty() && (precedence(stack.peek()) >= (precedence(A)))){
                        sb.append(stack.pop());
                    }
                    stack.push(A);
                }
                
            }
                        
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        
        System.out.print(sb);
    }

    static int precedence(char A){
        if(A=='*' || A=='/') return 2;
        else if(A=='+' || A=='-') return 1;
        else  return 0;
    }
}