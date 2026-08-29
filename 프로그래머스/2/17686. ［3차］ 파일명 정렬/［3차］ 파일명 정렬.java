import java.util.*;

class Solution {
    
    static ArrayList<File> list;
    
    public String[] solution(String[] files) {
        init(files);    
        filesSort();
        return getAnswer();
    }
    
    static String[] getAnswer() {
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i).origin;
        }
        return answer;
    }
    
    static void filesSort(){
        list.sort( (a,b) -> {
            if( !a.head.equals(b.head) ){
                return a.head.compareTo(b.head);
            }
            if( a.number != b.number ){
                return Integer.compare(a.number,b.number);
            }
            return Integer.compare(a.rank, b.rank);
        });
    }
    
    static void init(String[] files) {
        list = new ArrayList<>();
        for(int i=0; i<files.length; i++) {
            getHeadResponse res = getHead(files[i]);
            String head = res.head;
            int number = getNumber(files[i], res.index);
            list.add(new File(head, number, files[i], i));
        }
    }
    
    static int getNumber(String file, int index){
        boolean check = true;
        StringBuilder sb = new StringBuilder();
        for(int i=index; i<file.length(); i++){
            if(i >= index+5) break;
            if( file.charAt(i)-'0' >= 10 || file.charAt(i)-'0' < 0 ){
                break;
            }
            if( file.charAt(i)-'0' == 0 && check ){
                continue;
            }
            check = false;
            sb.append(file.charAt(i));
        }
        if(sb.length()==0) return 0;
        return Integer.parseInt(sb.toString());
    }
    
    static getHeadResponse getHead(String file){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i=0; i<file.length(); i++){
            if(file.charAt(i)-'0' >= 0 && file.charAt(i)-'0' <= 9){
                break;
            }
            sb.append(file.charAt(i));
            count++;
        }
        return new getHeadResponse(sb.toString().toUpperCase(), count);
    }
    
    static class getHeadResponse{
        String head;
        int index;
        getHeadResponse(String head, int index) {
            this.head = head;
            this.index = index;
        }
    }
    
    static class File{
        String head;
        int number;
        String origin;
        int rank;
        File(String head, int number, String origin, int rank) {
            this.head = head;
            this.number = number;
            this.origin = origin;
            this.rank = rank;
        }
    }
}