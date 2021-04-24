package java_snaker.java;


import java.util.LinkedList;

public class Snake {
    private Direction direction = Direction.LEFT;

   private LinkedList<Node> body;

   private boolean isLive=true;

//构造方法，创建Snake对象时执行
   public Snake(){

       initSnake();
   }

//初始化蛇身五个节点
    public void initSnake() {
       body=new LinkedList<>();
       body.add(new Node(20,16));
       body.add(new Node(21,16));
       body.add(new Node(22,16));
       body.add(new Node(23,16));
       body.add(new Node(24,16));
        body.add(new Node(25,16));
        body.add(new Node(26,16));
        body.add(new Node(27,16));
        body.add(new Node(28,16));
    }

    //蛇跟着蛇头移动,在蛇头添加一个节点，蛇尾减少一个
    public void move(){
       if (isLive) {
           Node head = body.getFirst();
           switch (direction) {
               case UP:
                   body.addFirst(new Node(head.getX(), head.getY() - 1));
                   break;
               case DOWN:
                   body.addFirst(new Node(head.getX(), head.getY() + 1));
                   break;
               case LEFT:
                   body.addFirst(new Node(head.getX() - 1, head.getY()));
                   break;
               case RIGHT:
                   body.addFirst(new Node(head.getX() + 1, head.getY()));
                   break;
           }
           body.removeLast();
           //判断蛇是否撞墙
           head = body.getFirst();
           if (head.getX() < 0 || head.getY() < 0 || head.getX() >= 40 || head.getY() >= 40) {
               isLive = false;
           }
           //判断蛇是否碰到自己的身体
           for (int i=1;i<body.size();i++){
               Node node = body.get(i);
               if (head.getX()==node.getX()&&head.getY()==node.getY()){
                   isLive=false;
               }
           }
       }
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public LinkedList<Node> getBody(){
       return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void eat(Node food) {
        Node head = body.getFirst();
        switch (direction) {
            case UP:
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }

}