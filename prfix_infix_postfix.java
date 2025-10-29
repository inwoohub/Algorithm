import java.io.*;
import java.util.*;

public class Main{
    
    static class Node{
        char left;
        char right;
        public Node(char left, char right){
            this.left = left;
            this.right = right;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static int N;
    static ArrayList<Node>[] list;

    //전위
    static void prefix(int cur){
        Node curNode = list[cur].get(0);
        sb.append((char)(cur+64));
        if(curNode.left!='.'){
            prefix(curNode.left-64);
        }
        if(curNode.right!='.'){
            prefix(curNode.right-64);
        }
    }
    // //중위
    static void infix(int cur){
        Node curNode = list[cur].get(0);
        if(curNode.left!='.'){
            infix(curNode.left-64);
        }
        sb.append((char)(cur+64));
        if(curNode.right!='.'){
            infix(curNode.right-64);
        }
    }
    //후위
    static void postfix(int cur){
        Node curNode = list[cur].get(0);
        if(curNode.left!='.'){
            postfix(curNode.left-64);
        }
        if(curNode.right!='.'){
            postfix(curNode.right-64);
        }
        sb.append((char)(cur+64));
    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            char A = st.nextToken().charAt(0);
            char B = st.nextToken().charAt(0);
            char C = st.nextToken().charAt(0);
            int intA = A-64;
            list[intA].add(new Node(B,C));
        }
        prefix(1);
        sb.append("\n");
        infix(1);
        sb.append("\n");
        postfix(1);
        System.out.print(sb);
    }
}