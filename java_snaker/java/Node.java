package java_snaker.java;

import java.util.Random;

//蛇由节点组成
public class Node {
    private int x;
    private int y;

    public Node(){

    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //随机生成位置
    public void RandomDirection(){
        Random random = new Random();
        this.x= random.nextInt(40);
        this.y= random.nextInt(40);
    }
}
