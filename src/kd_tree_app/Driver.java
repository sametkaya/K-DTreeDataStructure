package kd_tree_app;


import java.util.Iterator;
import kd_tree_app.KDTree2D;
import kd_tree_app.Point2D;


public class Driver {
    public static void main(String[] args) {
        //Tree instance
        KDTree2D tree = new KDTree2D();

        //Creating points
        Point2D point1 = new Point2D(10, 20);
        Point2D point2 = new Point2D(30, 40);
        Point2D point3 = new Point2D(5, 5);
        Point2D point4 = new Point2D(20, 15);
        Point2D point5 = new Point2D(40, 50);
        Point2D point6 = new Point2D(7, 10);
        Point2D point7 = new Point2D(8, 8);

        //Insertion
        tree.insert(point1); // root
        tree.insert(point2); // root's right child
        tree.insert(point3); // root's left child
        tree.insert(point4); // point2's left child
        tree.insert(point5); // point2's right child
        tree.insert(point6); // point1's right child
        tree.insert(point7); // point6's right child

        //Search
        System.out.println(tree.search(point1)); // returns point1
        System.out.println(tree.search(point3)); // returns point3

        Point2D point8 = new Point2D(80, 80);
        System.out.println(tree.search(point8)); // returns null

        //Find min for x
        Point2D minX = tree.findMin(0);
        System.out.println(minX.x + " " + minX.y); // 5 5

        //Find min for y
        Point2D minY = tree.findMin(1);
        System.out.println(minY.x + " " + minY.y); // 5 5

        //Find max for x
        Point2D maxX = tree.findMax(0);
        System.out.println(maxX.x + " " + maxX.y); // 40 50

        //Find max for y
        Point2D maxY = tree.findMax(1);
        System.out.println(maxY.x + " " + maxY.y); // 40 50

        //Iterable collection
        Iterator<Point2D> iterator = tree.inRectangle(new Point2D(0, 0), new Point2D(50, 55)).iterator();
        while (iterator.hasNext())
        {   
            Point2D next=iterator.next();
            System.out.println(next.x + " " + next.y); // Prints all points
            //ner next bir iterasyon bu kodda ikili gidiyor bu yüzden kapatıldı
            //System.out.println(iterator.next().x + " " + iterator.next().y); // Prints all points
        }

        //Nearest point
        Point2D neighbor1 = tree.nearestNeighbor(new Point2D(100, 100));
        System.out.println(neighbor1.x + " " + neighbor1.y); // 40 50

        Point2D neighbor2 = tree.nearestNeighbor(new Point2D(10, 10));
        System.out.println(neighbor2.x + " " + neighbor2.y); // 8 8

        Point2D neighbor3 = tree.nearestNeighbor(new Point2D(8, 10));
        System.out.println(neighbor3.x + " " + neighbor3.y); // 7 10

        //Basic remove and traverse
        tree.remove(point5);
        tree.remove(point7);
        System.out.println(tree); // with preorder traversal the tree should be (10, 20), (5, 5), (7, 10), (30, 40), (20, 15)
        tree.remove(point6);
        System.out.println(tree); // with preorder traversal the tree should be (10, 20), (5, 5), (30, 40), (20, 15)

        //Adding back the removed elements but different order
        tree.insert(point7);
        tree.insert(point6); //root's left subtree must be changed
        tree.insert(point5);
        System.out.println(tree); // with preorder traversal the tree should be (10, 20), (5, 5), (8, 8), (7, 10), (30, 40), (20, 15), (40, 50)

        // Remove nodes with child/children and traverse
        tree.remove(point1); //we are going to delete the root and the root has two children
        System.out.println(tree); // with preorder traversal the tree should be (20, 15), (5, 5), (8, 8), (7, 10), (30, 40), (40, 50)
    }
}
