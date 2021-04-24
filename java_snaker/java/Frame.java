package java_snaker.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Frame extends JFrame {

    private JPanel jPanel;
    private Snake snake;
    private Timer timer;//定时器，在指定时间调用蛇移动方法
    private Node food;
    //实现构造方法
    public Frame() throws HeadlessException {
        InitFrame();
        InitGamePanel();
        InitSnake();
        InitTimer();
        //设置键盘监听
        setKeyListener();
        InitFood();


    }

    private void InitFood() {
        food = new Node();
        food.RandomDirection();
    }

    //监听器
    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("键盘按下");
                //System.out.println(e.getKeyCode());
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        if (snake.getDirection()!=Direction.DOWN) {
                            snake.setDirection(Direction.UP);
                        }
                        break;
                        case KeyEvent.VK_DOWN:
                            if (snake.getDirection()!=Direction.UP) {
                                snake.setDirection(Direction.DOWN);
                            }
                            break;
                            case KeyEvent.VK_LEFT:
                                if (snake.getDirection()!=Direction.RIGHT) {
                                    snake.setDirection(Direction.LEFT);
                                }
                                break;
                                case KeyEvent.VK_RIGHT:
                                    if (snake.getDirection()!=Direction.LEFT) {
                                        snake.setDirection(Direction.RIGHT);
                                    }
                                    break;
                }
            }
        });
    }

    private void InitTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                snake.move();
                Node head=snake.getBody().getFirst();
                if (head.getX()==food.getX()&&head.getY()==food.getY()){
                    snake.eat(food);
                    food.RandomDirection();
                }
                //重绘游戏棋盘
                jPanel.repaint();
            }
        };
        //每100ms执行一次任务
        timer.scheduleAtFixedRate(timerTask,1,100);
    }

    private void InitSnake() {
        snake = new Snake();

    }

    //游戏棋盘
    private void InitGamePanel() {
         jPanel = new JPanel(){
            //游戏棋盘绘制
            @Override
            public void paint(Graphics g) {
                //清空棋盘
                g.clearRect(0,0,600,600);
                //绘制横线、竖线、蛇、食物
                for (int i =0;i<40;i++){
                    g.drawLine(0,i*15,600,i*15);
                }
                for (int j=0;j<41;j++){
                    g.drawLine(j*15,0,j*15,600);
                }
                LinkedList<Node> body = snake.getBody();
                for (Node node:body){
                    //填充
                    g.fillRect(node.getX()*15,node.getY()*15,15,15);
                }
                g.fillRect(food.getX()*15,food.getY()*15,15,15);
            }
        };
        add(jPanel);
    }

    //初始化窗口参数
    private void InitFrame() {
        //设置窗口宽高
        setSize(620,650);
        //设置窗口位置
        setLocation(480,480);
        //设置关闭窗口的按钮,默认按钮
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口不可变
        setResizable(false);
        //setBackground(Color.cyan);
    }

    public static void main(String[] args) {
        //创建窗体对象并显示
        new Frame().setVisible(true);
    }

}
