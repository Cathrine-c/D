import sun.java2d.loops.DrawGlyphList;

import java.awt.*;

public class BilliardTable {

        double Left,Top,Width,Height;

        public BilliardTable(double left, double top, double width, double height) {
            Left = left;
            Top = top;
            Width = width;
            Height = height;
        }


        //球的最小的x值
        public double minX(){
            return Left;
        }

        public double minY(){
            return Top;
        }

        public double maxX(){
            return Left*Width;

        }

        public double maxY(){
            return Top*Height;
        }

        //绘制图表
        public void Draw(Graphics g, Color TableColor){
            //绘制所有颜色
            g.setColor(TableColor);

            g.fillRect((int)(Left*globals.DrawScale),(int)(Top*globals.DrawScale),
                    (int)(Width*globals.DrawScale),(int)(Height*globals.DrawScale));
            DrawBorder(g);
        }

        //绘制边界
        public void DrawBorder(Graphics g) {
            //采用了白颜色，更改方便
            g.setColor(new Color(255,255,255));
            g.drawRect((int)(Left*globals.DrawScale),(int)(Top*globals.DrawScale),
                    (int)(Width*globals.DrawScale),(int)(Height*globals.DrawScale));
        }

    public static void main(String[] args) {
        BilliardTable table = new BilliardTable(200,100,500,500);


    }



    }


