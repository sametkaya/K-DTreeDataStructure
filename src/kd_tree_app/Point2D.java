
package kd_tree_app;


public class Point2D {
    public double x;
    public double y;
    public boolean isY;//x,y,n
    public int level;
    public Point2D parent;
    public Point2D left;
    public Point2D right;
 
    public Point2D(double x, double y) 
    {
        this.x = x;
        this.y = y;
        left = null;
        right = null;
        parent=null;
        isY=false;
        level=0;
    }
    public void Print()
    {
        System.out.println(this.x+" "+ this.y);
         
    }
    
    public String toString()
    {
        return this.x+" "+ this.y;
    
    }
}
