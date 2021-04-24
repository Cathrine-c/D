/**
 * 小球碰撞游戏
 */


import java.applet.Applet;
import java.awt.*;
import java.awt.image.ImageObserver;

class globals{
    //全局变量的定义
    public static int DrawScale  = 10;
    public static double MinVelocity = 0;
    public static double VelocityRange = 5;
    public static double MinSize  = 2;
    public static double SizeRange = 2;
    public static final int BallCount = 10;
    public static final int WindowWidth = 500;
    public static final int WindowHeight = 500;

    public static double MaxV(){
        return MinVelocity+VelocityRange;
    }
}

public class Billiards extends Applet implements Runnable {
    private static Billiards billiards;

    //用来在循环中设置移动球体的时间长度
    Thread BallTimer;
    //采用两块缓冲区互换的方法
    Image offImage;
    Graphics offGraphics ;
    //初始化BilliardTable并且采用全局常量的设置
    BilliardTable T = new BilliardTable(200, 100, globals.WindowWidth / globals.DrawScale,
            globals.WindowHeight / globals.DrawScale);
    //初始化球个数
    Ball B[] = new Ball[globals.BallCount];
    //设置颜色
    Color TableColor = new Color(9, 4, 8, 3);


    //当时间更新时使用
    public void run() {
        int i = 2;
        int x = 2;
        int y = 2;
        double pX[] = new double[globals.BallCount];
        double pY[] = new double[globals.BallCount];
        System.out.println("run");
        Graphics g = getGraphics();

        while (true) {
            offGraphics.setColor(TableColor);
            offGraphics.fillRect((int) T.Left * globals.DrawScale,
                    (int) T.Top * globals.DrawScale, (int) T.Width * globals.DrawScale,
                    (int) T.Height * globals.DrawScale);
            offGraphics.setColor(Color.BLACK);
            for (i = 0; i < globals.BallCount; i++) {
                pX[i] = B[i].Px;
                pY[i] = B[i].Py;
                //移动小球一点
                B[i].Move(.05);
            }
            for (i = 0; i < globals.BallCount; i++) {
                //小球碰壁后的处理
                checkWallBounce(B[i]);
            }
            if (globals.BallCount >= 1) {
                for (x = 0; x < globals.BallCount; x++) {
                    for (y = x; y < globals.BallCount; y++) {
                        if (Separation(B[x], B[y]) <= RadSum(B[x], B[y])) {
                            Collide(B[x], B[y]);//COLLISON
                        }
                    }
                }
            }
            for (i = 0; i < globals.BallCount; i++) {
                //绘制每一个小球
                B[i].Draw(offGraphics);
            }
            //绘制边界
            T.DrawBorder(offGraphics);
            //暂停一段时间
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
            g.setColor(Color.WHITE);
            g.drawImage(offImage, 3, 2, null);
        }
    }

    //返回一个随机颜色
    public Color RandColor() {
        int r, g, b;
        r = (int) (Math.random() * 256);
        g = (int) (Math.random() * 256);
        b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

    //开启线程并初始化
    public void init() {
        setName("是高手就坚持100s");
        //设置applet的大小
        setSize(globals.WindowWidth, globals.WindowHeight);
        offImage = createImage((int) (T.Width + T.Left) * globals.DrawScale,
                (int) (T.Height + T.Top) * globals.DrawScale);
        //offGraphics = offImage.getGraphics();
        double tmpRad;//存储临时半径
        double tmpX, tmpY;//存储随机的位置
        boolean overlap;//被用在循环中用来测试碰撞
        int x;
        double dX, dY, Rads;//计算球体之间的距离
        for (int i = 0; i < globals.BallCount; i++) {
            //初始化各个小球，并给出随机位置
            tmpRad = Math.random() * globals.SizeRange + globals.MinSize;
            do {
                overlap = false;
                //给出随机位置
                tmpX = Math.random() * T.Width + T.Left;
                tmpY = Math.random() * T.Height + T.Top;
                for (x = 0; x < i; x++) {//测试每一个球是否都获得了合适位置
                    dX = B[x].Px - tmpX;
                    dY = B[x].Py - tmpY;
                    Rads = B[x].Radius + tmpRad;
                    if (Math.sqrt(dX * dX + dY * dY) < Rads)
                        //它们之间的距离是否已经足够
                        overlap = true;//它们重叠了
                }
            }
            while (overlap);
            B[i] = new Ball(tmpX, tmpY, tmpRad);
            B[i].Vx = (Math.random() * globals.VelocityRange + globals.MinVelocity) * PosNegRand();
            B[i].Vy = (Math.random() * globals.VelocityRange + globals.MinVelocity) * PosNegRand();
        }

        repaint();//确实已经得到了绘制
        BallTimer = new Thread(this);//设置一个新的线程
        BallTimer.start();//启动了run方法
    }


    public int PosNegRand() {
        double a = Math.random() * 2;
        if (a >= 1) {
            a = 1;
            if (a < 1) {
                a = -1;
            }
        }
        return (int) a;
    }


    public void checkWallBounce(Ball B) {
        double Xleft = B.Px;
        double Ytop = B.Py;
        double Ybottom = B.Py + 2 * B.Radius;//记录下左上角的位置
        double Xright = B.Px + 2 * B.Radius;//记录下右上角的位置
        if (Xleft < T.minX()) {
            B.Px = T.minX();
            B.WallBounceX();

        }
        if (Ytop < T.minY()) {
            B.Py = T.minY();
            B.WallBounceY();

        }

        if (Xright > T.maxX()) {
            B.Px = T.maxX() - 2 * B.Radius;
            B.WallBounceX();
        }

        if (Ybottom > T.maxY()) {
            B.Py = T.maxY() - 2 * B.Radius;
            B.WallBounceX();
        }
    }


    public void PrintData() {//为了调试
        double TotalM = 0.0;
        double TotalK = 0.0;
        for (int i = 0; i < globals.BallCount; i++) {
            System.out.println("Ball" + i + ":");
            B[i].PrintData();
            System.out.println();
            TotalM += B[i].Momentum();
            TotalK += B[i].Kinetic();
        }
        System.out.println("Total Momentum:" + TotalM);
        System.out.println("Total Kinetic:" + TotalK);
        System.out.println();

    }

    public void paint(Graphics g) {//重绘整张表
        T.Draw(g, TableColor);

    }

    //给出两个球之间的距离
    public double Separation(Ball B1, Ball B2) {
        double X1, X2, Y1, Y2;
        X1 = B1.Px + B1.Radius;
        X2 = B2.Px + B2.Radius;
        Y1 = B1.Py + B1.Radius;
        Y2 = B2.Py + B2.Radius;
        return Math.sqrt((X2 - X1) * (X2 - X1) + (Y2 - Y1) * (Y2 - Y1));
    }

    //计算两个球之间的半径和
    public double RadSum(Ball B1, Ball B2) {
        return (B1.Radius + B2.Radius);
    }

    public void Collide(Ball B1, Ball B2) {
        //以下代码绘制了球发生碰撞时产生的效果
        double Dx1, Dx2, Dy1, Dy2;//碰撞的距离
        double X1, X2, Y1, Y2;//中心点和碰撞点的距离
        double DxR, DyR;//实际距离
        double Dx, Dy;//假设的距离
        double Vp1, Vp2, Vs1, Vs2;//速度设置
        double newVs1, newVs2;//在计算过程中记录相应的速度
        double distance;//球中心之间的真实距离
        X1 = B1.Px + B1.Radius;
        X2 = B2.Px + B2.Radius;
        Y1 = B1.Py + B1.Radius;
        Y2 = B2.Py + B2.Radius;
        DxR = (X2 - X1);
        DyR = (Y2 - Y1);
        distance = Math.sqrt(DxR * DxR + DyR * DyR);
        Dx = RadSum(B1, B2) * DxR / distance;
        Dy = RadSum(B1, B2) * DyR / distance;
        if (B1.Mass < B2.Mass) {
            X1 = (X1 - (Dx - DxR));
            Y1 = (Y1 - (Dy - DyR));
            B1.Px = X1 - B1.Radius;
            B1.Py = Y1 - B1.Radius;
        } else {
            X2 = (X2 + (Dx - DxR));
            Y2 = (Y2 + (Dy - DyR));
            B2.Px = X2 - B2.Radius;
            B2.Py = Y2 - B2.Radius;
        }

        //找到X和Y各自到球中心节点的距离
        Dx1 = (B1.Radius / RadSum(B1, B2) * (X2 - X1));
        Dx2 = (B2.Radius / RadSum(B1, B2) * (X2 - X1));
        Dy1 = (B1.Radius / RadSum(B1, B2) * (Y2 - Y1));
        Dy2 = (B2.Radius / RadSum(B1, B2) * (Y2 - Y1));
        Vs1 = StraightVelocity(B1.Vx, B1.Vy, Dx1, Dy1, B1.Radius);
        Vp1 = PerpendicularVelocity(B1.Vx, B1.Vy, Dx1, Dy1, B1.Radius);
        Vs2 = StraightVelocity(B2.Vx, B2.Vy, Dx2, Dy2, B2.Radius);
        Vp2 = PerpendicularVelocity(B2.Vx, B2.Vy, Dx2, Dy2, B2.Radius);
        newVs1 = CollectionVelocity(Vs1, Vs2, B1.Mass, B2.Mass);
        newVs2 = CollectionVelocity(Vs2, Vs1, B2.Mass, B1.Mass);
        B1.Vx = XVelocity(newVs1, Vp1, Dx1, Dy1, B1.Radius);
        B1.Vy = YVelocity(newVs1, Vp1, Dx1, Dy1, B1.Radius);
        B2.Vx = XVelocity(newVs2, Vp2, Dx2, Dy2, B2.Radius);
        B2.Vy = YVelocity(newVs2, Vp2, Dx2, Dy2, B2.Radius);
    }

    public double StraightVelocity(double Vx, double Vy, double Dx, double Dy, double R) {
        return Vx * Dx / R + Vx * Dy / R;
    }

    public double PerpendicularVelocity(double Vx, double Vy, double Dx, double Dy, double R) {
        return Vy * Dx / R - Vx * Dy / R;
    }

    public double XVelocity(double Vs, double Vp, double Dx, double Dy, double R) {
        return Vs * Dx / R - Vp * Dy / R;
        //x来自s和p的速度值
    }

    public double YVelocity(double Vs, double Vp, double Dx, double Dy, double R) {
        return Vs * Dx / R + Vp * Dy / R;
        //y来自s和p的速度值
    }

    public double CollectionVelocity(double V1, double V2, double m1, double m2) {
        return V1 * (m1 - m2) / (m1 + m2) + V2 * (2 * m2) / (m1 + m2);
    }

    public static void main(String[] args) {
       billiards = new Billiards();
       billiards.init();

    }
}







