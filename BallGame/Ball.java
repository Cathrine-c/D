import java.awt.*;

public class Ball {
    //构造球

        double Vx = 4;//x方向的速度
        double Vy = 3;//y方向的速度
        double Px = 3;//x轴的位置
        double Py = 4;//y轴的位置

        double Density =1;//球的密度
        double Radius=2;//球的半径
        double Mass;
        Color BallColor = new Color(181, 181, 181, 219);//球体初始化为白色
        public Ball(double x,double y,double radius){//构造函数
            double Volume = 4.0/3.0*Math.PI*radius*radius*radius;
            //v=4/3PI r^3
            Px = x;
            Py = y;
            Radius = radius;
            Mass = Density * Volume;
        }

        //输出全部球的信息
        public void PrintData(){
            System.out.println("Px:"+Px);
            System.out.println("Py:"+Py);
            System.out.println("Mass:"+Mass);
            System.out.println("Vx: "+Vx);
            System.out.println("Vy: "+Vy);
            System.out.println("V: "+Velocity());
            System.out.println("Ek: "+Kinetic());
            System.out.println("Mom: "+Momentum());
        }

        //增加球的速度
        public void adVelocity(double x,double y){
            Vx = x;
            Vy = y;

        }

        //返回球当前的速度
        public double Velocity(){
            return Math.sqrt(Vx*Vx+Vy*Vy);
        }

        public double Momentum(){
            //p=mv;
            return Mass*Vx+Mass*Vy;

        }

        public double Kinetic(){
            return .5*Mass*Velocity()*Velocity();
        }

        public void WallBounceX(){
            Vx -=Vx; //球变换x速度

        }

        public void WallBounceY(){//球移动到了边界上
            Vy -=Vy;//球变换了y速度
        }

        public void Move(double t){//将球移动一圈
            Px = Px +Vx*t;
            Py = Py +Vy*t;

        }

        public void Draw(Graphics g){
            int top,left,size;
            left = (int)(Px*globals.DrawScale);
            top =  (int)(Py*globals.DrawScale);
            size = (int)(2*Radius*globals.DrawScale);
            g.setColor(BallColor);//绘制球的内部颜色
            g.fillOval(left,top,size,size);
//        g.setColor(Color.BLACK);
//        g.drawOval(left,top,size,size);//绘制小球的外边框

        }

        public void Clear( Graphics g,Color TableColor){
            int top,left,size;
            left = (int)(Px*globals.DrawScale);
            top =  (int)(Py*globals.DrawScale);
            size = (int)(2*Radius*globals.DrawScale);
            //覆盖了老的位置
            g.setColor(TableColor);
            //确保所有的线条都被覆盖到了
            g.fillOval(left-1,top-1,size+2,size+2);

        }

    public static void main(String[] args) {
        Ball ball = new Ball(3,4,2);
        ball.PrintData();
    }

}
