
package kd_tree_app;

import java.util.ArrayList;
import java.util.List;


public class KDTree2D {

   Point2D root;

    public KDTree2D() {
        this.root = null;
    }

    public boolean insert(Point2D point) //Log(n)
    {
        boolean isAdded = false;
        if(point ==null)
        {
            return false;
        }
        if (this.root == null) {
            this.root = point;
            this.root.isY = false;
            this.root.level = 0;
            isAdded = true;
        } else {
            Point2D temp = this.root;
            while (!isAdded) {
                if (temp.isY == false) {
                    if (point.x < temp.x) {
                        if (temp.left == null) {
                            temp.left = point;
                            temp.left.isY = !temp.isY;
                            temp.left.level = temp.level + 1;
                            temp.left.parent = temp;
                            isAdded = true;
                            continue;
                        } else {
                            temp = temp.left;
                            continue;
                        }
                    } else if (point.x > temp.x) {
                        if (temp.right == null) {
                            temp.right =point;
                            temp.right.isY = !temp.isY;
                            temp.right.level = temp.level + 1;
                            temp.right.parent = temp;
                            isAdded = true;
                            continue;
                        } else {
                            temp = temp.right;
                            continue;
                        }
                    } else {
                        System.out.println("Same Value Error");
                        isAdded = false;
                        break;
                    }
                } else if (temp.isY == true) {
                    if (point.y < temp.y) {
                        if (temp.left == null) {
                            temp.left = point;
                            temp.left.isY = !temp.isY;
                            temp.left.level = temp.level + 1;
                            temp.left.parent = temp;
                            isAdded = true;
                            continue;
                        } else {
                            temp = temp.left;
                            continue;
                        }
                    } else if (point.y > temp.y) {
                        if (temp.right == null) {
                            temp.right = point;
                            temp.right.isY = !temp.isY;
                            temp.right.level = temp.level + 1;
                            temp.right.parent = temp;
                            isAdded = true;
                            continue;
                        } else {
                            temp = temp.right;
                            continue;
                        }
                    } else {
                        System.out.println("Same Value Error");
                        isAdded = false;
                        break;
                    }
                }

            }
        }
        this.increaseLevelReculsive(point.left);
        this.increaseLevelReculsive(point.right);
        return isAdded;
    }

    public Point2D search(Point2D point)//Log(n)
    {
        Point2D foundPoint2D = null;
        if (this.root == null) {
            return foundPoint2D;
        } else {
            Point2D temp =this.root;
            while (foundPoint2D == null) {
                if (temp == null) {
                    foundPoint2D = null;
                    break;
                } else if (temp.x == point.x && temp.y == point.y) {
                    foundPoint2D = temp;
                    break;
                } else if (temp.isY == false) {
                    if (point.x < temp.x) {
                        temp = temp.left;
                        continue;
                    } else if (point.x > temp.x) {
                        temp = temp.right;
                        continue;
                    }
                } else if (temp.isY == true) {
                    if (point.y < temp.y) {
                        temp = temp.left;
                        continue;
                    } else if (point.y > temp.y) {
                        temp = temp.right;
                        continue;
                    }
                }
            }
        }

        return foundPoint2D;
    }

    public String toString()//n
    {
        String preorderString = this.preOrderStringReculsive(root);

        return preorderString;

    }

    private String preOrderStringReculsive(Point2D point)//n
    {
        String preorder = "";
        if (point == null) {
            return "";
        } else {
            for (int i = 0; i < point.level; i++) {
                preorder += ".";
            }
            preorder += point.x + " | " + point.y + "\n";
            preorder += preOrderStringReculsive(point.left);

            preorder += preOrderStringReculsive(point.right);
        }
        return preorder;

    }

    public boolean remove(Point2D point) //Log(n)
    {
        boolean isfound = false;
        if (this.root == null) {
            return isfound;
        } else {
            Point2D temp = this.root;
            while (isfound == false) {
                if (temp == null) {
                    isfound = false;
                    break;
                } else if (temp.x == point.x && temp.y == point.y) {
                    if(temp.parent==null)
                    {
                        if(temp.left!=null)
                        {
                            this.root= temp.left;
                            this.root.level=0;
                            this.root.isY=!this.root.isY;
                            this.root.parent=null;
                            Point2D tl=this.root.left;
                            Point2D tr=this.root.right;
                            this.root.left=null;
                            this.root.right=null;
                            
                            
                            this.insert(tl);
                            this.insert(tr);
                            this.insert(temp.right);
                            //this.root.right=temp.right;
                            temp=temp.left;
                        }
                        else if(temp.right!=null)
                        {
                            this.root=temp.right;
                            this.root.level=0;
                            this.root.isY=!this.root.isY;
                            this.root.parent=null;
                            Point2D tl=this.root.left;
                            Point2D tr=this.root.right;
                            this.root.left=null;
                            this.root.right=null;
                            
                            
                            this.insert(tl);
                            this.insert(tr);
                            this.insert(temp.left);
                           // this.root.right=temp.right;
                            temp=temp.right;
                        }
                        else
                        {
                            this.root=null;
                            temp=null;
                        }
                      
                    }
                    else if(temp.parent.left!= null && temp.parent.left.x==temp.x && temp.parent.left.y==temp.y)
                    {
                        if(temp.left!=null)
                        {
                            temp.parent.left=temp.left;
                             temp=temp.left;
                        }
                        else if(temp.right!=null)
                        {
                            temp.parent.left=temp.right;
                            temp=temp.right;
                        }
                        else
                        {
                            temp.parent.left=null;
                            temp=null;
                        }
                        this.decreaseLevelReculsive(temp);
                    }else if(temp.parent.right!= null && temp.parent.right.x==temp.x && temp.parent.right.y==temp.y)
                    {
                        if(temp.left!=null)
                        {
                            temp.parent.right=temp.left;
                            temp=temp.left;
                        }
                        else if(temp.right!=null)
                        {
                            temp.parent.right=temp.right;
                             temp=temp.right;
                        }
                        else
                        {
                            temp.parent.right=null;
                            temp=null;
                        }
                        this.decreaseLevelReculsive(temp);
                    }
                      //this.decreaseLevelReculsive(temp);
                    isfound = true;
                    break;
                } else if (temp.isY == false) {
                    if (point.x < temp.x) {
                        temp = temp.left;
                        continue;
                    } else if (point.x > temp.x) {
                        temp = temp.right;
                        continue;
                    }
                } else if (temp.isY == true) {
                    if (point.y < temp.y) {
                        temp = temp.left;
                        continue;
                    } else if (point.y > temp.y) {
                        temp = temp.right;
                        continue;
                    }
                }
            }
        }

        return isfound;
    }
    
    public void increaseLevelReculsive(Point2D point)//Log(n)
    {
        if(point==null)
        {
            return;
        }
        point.isY=!point.isY;
        point.level++;
        decreaseLevelReculsive(point.left);
        decreaseLevelReculsive(point.right);
    }
    public void decreaseLevelReculsive(Point2D point)//Log(n)
    {
        if(point==null)
        {
            return;
        }
        point.isY=!point.isY;
        point.level--;
        decreaseLevelReculsive(point.left);
        decreaseLevelReculsive(point.right);
        
    }
            
    public Point2D findMin(int d)//Log(n)
    {
        if(d==0)
        {
             return this.minPoind2D(this.root,null,false);
        }
        else
        {
          
           return this.minPoind2D(this.root,null,true);
        }
    }
    private Point2D minPoind2D(Point2D point,Point2D min, boolean isy)// Log(n)
    {
        if(point ==null)
            return min;
        
        if(isy==false)
        {
            if(point.isY==false)
            {
                if(min==null || point.x<min.x)
                    min=point;    
                return minPoind2D(point.left,min,isy);
            }
            else
            {
                Point2D min3= min3Point(point,point.left,point.right,true);
                if(min3.x<min.x)
                    min=min3;
                if(point.left!=null && point.right!=null&& point.left.x<point.right.x )
                {
                     return minPoind2D(point.left,min,isy);
                }
                else if(point.left!=null && point.right!=null&& point.left.x>point.right.x)
                {
                    return minPoind2D(point.right,min,isy);
                }
                else if(point.left!=null)
                {
                    return minPoind2D(point.left,min,isy);
                }
                else
                {
                    return minPoind2D(point.right,min,isy);
                }
                    
            }
        }
        else
        {
             if(point.isY==true)
            {
                if(min==null || point.y<min.y)
                    min=point;    
                return minPoind2D(point.left,min,isy);
            }
            else
            {
                Point2D min3= min3Point(point,point.left,point.right,false);
                if(min==null || min3.y<min.y)
                    min=min3;
                if(point.left!=null && point.right!=null&& point.left.y<point.right.y )
                {
                     return minPoind2D(point.left,min,isy);
                }
                else if(point.left!=null && point.right!=null&& point.left.y>point.right.y)
                {
                    return minPoind2D(point.right,min,isy);
                }
                else if(point.left!=null)
                {
                    return minPoind2D(point.left,min,isy);
                }
                else
                {
                    return minPoind2D(point.right,min,isy);
                }
                    
            }
            
        }

     
    }
    
    private Point2D min3Point(Point2D parent, Point2D left, Point2D right,boolean x )//0
    {
        if(x==true)
        {
            if(left!=null&&right!=null  )
            {
                if(parent.x<left.x && parent.x<right.x)
                    return parent;
                else if(left.x<parent.x && left.x<right.x)
                    return left;
                else
                    return right;
            }
            else if(left!=null&&right==null )
            {
                 if(parent.x<left.x)
                    return parent;
                else 
                    return left;
            }
            else if(left==null && right!=null )
            {
                 if(parent.x<right.x)
                    return parent;
                else 
                    return right;
            }
            else
            {
                return parent;
            }
        }
        else
        {
             if(left!=null&&right!=null  )
            {
                if(parent.y<left.y && parent.y<right.y)
                    return parent;
                else if(left.y<parent.y && left.y<right.y)
                    return left;
                else
                    return right;
            }
            else if(left!=null &&right==null )
            {
                 if(parent.y<left.y)
                    return parent;
                else 
                    return left;
            }
            else if(left==null && right!=null )
            {
                 if(parent.y<right.y)
                    return parent;
                else 
                    return right;
            }
               else
            {
                return parent;
            }
            
        }
      
    }
    
     public Point2D findMax(int d)//Log(n)
    {
        if(d==0)
        {
             return this.maxPoind2D(this.root,null,false);
        }
        else
        {
          
           return this.maxPoind2D(this.root,null,true);
        }
    }
     private Point2D maxPoind2D(Point2D point,Point2D max, boolean isy)//log(n)
    {
        if(point ==null)
            return max;
        
        if(isy==false)
        {
            if(point.isY==false)
            {
                if(max==null || (point).x>max.x)
                    max=point;    
                return maxPoind2D(point.right,max,isy);
            }
            else
            {
                Point2D max3= max3Point(point,point.left,point.right,true);
                if(max3.x>max.x)
                    max=max3;
                if(point.left!=null && point.right!=null&& point.left.x>point.right.x )
                {
                     return maxPoind2D(point.left,max,isy);
                }
                else if(point.left!=null && point.right!=null&& point.left.x<point.right.x)
                {
                    return maxPoind2D(point.right,max,isy);
                }
                else if(point.left!=null)
                {
                    return maxPoind2D(point.left,max,isy);
                }
                else
                {
                    return maxPoind2D(point.right,max,isy);
                }
                    
            }
        }
        else
        {
             if(point.isY==true)
            {
                if(max==null || point.y<max.y)
                    max=point;    
                return maxPoind2D(point.right,max,isy);
            }
            else
            {
                Point2D max3= max3Point(point,point.left,point.right,false);
                if(max==null || max3.y>max.y)
                    max=max3;
                if(point.left!=null && point.right!=null&& point.left.y>point.right.y )
                {
                     return maxPoind2D(point.left,max,isy);
                }
                else if(point.left!=null && point.right!=null&& point.left.y<point.right.y)
                {
                    return maxPoind2D(point.right,max,isy);
                }
                else if(point.left!=null)
                {
                    return maxPoind2D(point.left,max,isy);
                }
                else
                {
                    return maxPoind2D(point.right,max,isy);
                }
                    
            }
            
        }

     
    }
     
    private Point2D max3Point(Point2D parent, Point2D left, Point2D right,boolean x )//=0
    {
        if(x==true)
        {
            if(left!=null&&right!=null  )
            {
                if(parent.x>left.x && parent.x>right.x)
                    return parent;
                else if(left.x>parent.x && left.x>right.x)
                    return left;
                else
                    return right;
            }
            else if(left!=null&&right==null )
            {
                 if(parent.x>left.x)
                    return parent;
                else 
                    return left;
            }
            else if(left==null && right!=null )
            {
                 if(parent.x>right.x)
                    return parent;
                else 
                    return right;
            }
            else
            {
                return parent;
            }
        }
        else
        {
             if(left!=null&&right!=null  )
            {
                if(parent.y>left.y && parent.y>right.y)
                    return parent;
                else if(left.y>parent.y && left.y>right.y)
                    return left;
                else
                    return right;
            }
            else if(left!=null &&right==null )
            {
                 if(parent.y>left.y)
                    return parent;
                else 
                    return left;
            }
            else if(left==null && right!=null )
            {
                 if(parent.y>right.y)
                    return parent;
                else 
                    return right;
            }
               else
            {
                return parent;
            }
            
        }
      
    }  
    
    public ArrayList<Point2D> inRectangle(Point2D ll, Point2D ur)//Log(n)
    {
        ArrayList<Point2D> list= new ArrayList<Point2D>();
        inRectangleSearch(root,ll,ur,list);
        
        return list;
    }
    private void inRectangleSearch(Point2D point,Point2D ll, Point2D ur,List <Point2D> list)//Log(n)
    {
        if(point==null)
            return;
        if((ll.x<=point.x)&&(point.x<=ur.x) && (ll.y<=point.y&&point.y<=ur.y))
        {
            list.add(point);
        }
        if(point.isY==false)
        {
            if(ll.x<=point.x&&point.x<=ur.x)
            {
                 inRectangleSearch(point.right,ll,ur,list);
                 inRectangleSearch(point.left,ll,ur,list);
            }
            else if(point.x<ll.x)
            {
                inRectangleSearch(point.right,ll,ur,list);
            }
            else if(point.x>ur.x)
            {
                inRectangleSearch(point.left,ll,ur,list);
            }
        }
        else
        {
            if(ll.y<=point.y&&point.y<=ur.y)
            {
                 inRectangleSearch(point.right,ll,ur,list);
                 inRectangleSearch(point.left,ll,ur,list);
            }
            else if(point.y<ll.y)
            {
                inRectangleSearch(point.right,ll,ur,list);
            }
            else if(point.y>ur.y)
            {
                inRectangleSearch(point.left,ll,ur,list);
            }
        
        }
        
    
    }
    Point2D nearest;
    public Point2D nearestNeighbor(Point2D point)
    {
      
        nearestNeighborSearch(root,point);
        return nearest;
    }
    
    private void nearestNeighborSearch(Point2D point,Point2D qpoint)//Log(n)
    {
        if(point ==null)
            return;
        if(nearest ==null)
        {
            nearest=point;
           
        }
        if(distance(qpoint, nearest)>distance(qpoint, point))
        {
            nearest=point;
        }
        
        if(point.isY==false)
        {
            if(qpoint.x<=point.x)
            {
                nearestNeighborSearch(point.left,qpoint);
                if(point.right!=null && (distance(qpoint, nearest)> distancex(qpoint, point.right)))
                    nearestNeighborSearch(point.right,qpoint);
            }
            else
            {
                nearestNeighborSearch(point.right,qpoint);
                if(point.left!=null && (distance(qpoint, nearest)> distancex(qpoint, point.left)))
                    nearestNeighborSearch(point.left,qpoint);
            }
        }
         else
        {
            if(qpoint.y<=point.y)
            {
                nearestNeighborSearch(point.left,qpoint);
                if(point.right!=null && (distance(qpoint, nearest)> distancey(qpoint, point.right)))
                    nearestNeighborSearch(point.right,qpoint);
            }
            else
            {
                nearestNeighborSearch(point.right,qpoint);
                if(point.left!=null && (distance(qpoint, nearest)> distancey(qpoint, point.left)))
                    nearestNeighborSearch(point.left,qpoint);
            }
        }
        
       
    }
    
    private double distance(Point2D p1, Point2D p2) {
       
        return Math.sqrt((p2.y - p1.y) * (p2.y - p2.y) + (p2.x - p1.x) * (p2.x - p1.x));
    }
    private double distancex(Point2D p1, Point2D p2) {
       
        return Math.abs(p1.x-p2.x);
    }
        private double distancey(Point2D p1, Point2D p2) {
       
        return Math.abs(p1.y-p2.y);
    }
    
}
