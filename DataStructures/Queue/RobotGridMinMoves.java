import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class Point{
    int x;
    int y;
    Point(){}
    Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}
public class Solution {
    ArrayList<ArrayList<Character>> board;
    Point source, destination;
    int dim;
    public Solution(){
        board=new ArrayList<>();
        source=new Point();
        destination=new Point();
    }
    public void readBoard(){
        Scanner sc=new Scanner(System.in);
        dim=sc.nextInt();
        sc.nextLine();
        for(int row=0; row < dim; ++row){
            ArrayList<Character> rows=new ArrayList<>();
            String line=sc.nextLine();
            for(int col=0 ; col < dim; ++col){
                rows.add(line.charAt(col));
            }
            board.add(new ArrayList<>(rows));
        }
        int x=sc.nextInt();
        int y=sc.nextInt();
        source.x=x;
        source.y=y;
        x=sc.nextInt();
        y=sc.nextInt();
        destination.x=x;
        destination.y=y;
    }
    private boolean destination_reached=false;
    private int moves=0;
    
    public int makeMove(){
        char mark='X';
        ArrayDeque<Integer> dist=new ArrayDeque<>();
        ArrayDeque<Point> q=new ArrayDeque<>();
        q.addFirst(new Point(source.x, source.y));
        board.get(source.x).set(source.y,mark);
        dist.addFirst(0);
        Point [] mv ={new Point(-1,0),new Point(0,1),new Point(1,0),new Point(0,-1)};
        while(!q.isEmpty()){
            Point cur=q.peekLast();
            if(cur.x==destination.x && cur.y==destination.y){
                return dist.peekLast();                
            }
            for(Point p: mv){
                int nx = cur.x + p.x;
                int ny = cur.y+p.y;
                while(nx>=0 && ny>=0 && nx<dim && ny<dim && 
                      board.get(nx).get(ny)!=mark){
                    q.addFirst(new Point(nx, ny));
                    dist.addFirst(dist.peekLast()+1);
                    board.get(nx).set(ny,mark);
                    
                    nx+=p.x;
                    ny+=p.y;
                    
                }
            }
            q.removeLast();
            dist.removeLast();
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Solution sln=new Solution();
        sln.readBoard();
//        sln.printBoard();
        System.out.println(sln.makeMove());
    }
}
