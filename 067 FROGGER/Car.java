import pkg.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Car {
    public Scanner scan = new Scanner(System.in);
    
    private Color windowColor = new Color(200, 200, 255);
    
    private Rectangle body;
    private Rectangle top;
    private Rectangle window;
    private Rectangle bounding;

    private Ellipse wheel1;
    private Ellipse wheel2;

    private Text label;

    private double step;

    private Rectangle[] rects = new Rectangle[4];

    public Car(double x, double y, Color color, String mess) {
        body = new Rectangle(x, y+50, 125, 50);
        body.setColor(color);
        rects[0] = body;

        top = new Rectangle(body.getWidth()/5+x, y, body.getWidth()/5*3, body.getHeight());
        top.setColor(color);
        rects[1] = top;

        window = new Rectangle(body.getWidth()/5*2+x, y + 10, body.getWidth()/5*2, body.getHeight()-10);
        window.setColor(windowColor);
        rects[2] = window;

        wheel1 = new Ellipse(top.getX()-15, body.getY() + body.getHeight()-15, 30, 30);
        wheel1.setColor(new Color(0,0,0));
        wheel2 = new Ellipse(top.getX()+top.getWidth()-15, body.getY() + body.getHeight()-15, 30, 30);
        wheel2.setColor(new Color(0,0,0));

        bounding = new Rectangle(x, y, body.getWidth(), body.getHeight() + top.getHeight() + wheel1.getHeight()/2);
        rects[3] = bounding;

        label = new Text(bounding.getWidth()/2 + bounding.getX(), bounding.getHeight()/2 + bounding.getY(), mess);
        label = new Text(label.getX() - label.getWidth()/2, label.getY(), mess);
    }

    public void draw() {
        body.fill();
        top.fill();
        window.fill();
        wheel1.fill();
        wheel2.fill();
        bounding.draw();
        label.draw();
    }

    public void drive(boolean start) {
        Canvas.pause(1);
        if (start) {
            for (int i = 0; i < rects.length; i++) {
                rects[i].translate(step, 0);
            }
            wheel1.translate(step,0);
            wheel2.translate(step,0);
            label.translate(step,0);

            if (bounding.getX() > 1400) {
                for (int i = 0; i < rects.length; i++) {
                    rects[i].translate(-1400, 0);
                }
                wheel1.translate(-1400,0);
                wheel2.translate(-1400,0);
                label.translate(-1400,0);
            }
        }
    }

    public void driveRandom(boolean start) {
        Canvas.pause(10);
        if (start) {
            for (int i = 0; i < rects.length; i++) {
                rects[i].translate(step, 0);
            }
            wheel1.translate(step,0);
            wheel2.translate(step,0);
            label.translate(step,0);

            int[] location = {0, 0};
            if (bounding.getX() > 500) {
                location[0] = -500;
                switch (Canvas.rand(3)) {
                    case 1:
                        location[1] = 115;
                        break;
                    case 2:
                        location[1] = -115;
                        break;
                }
                if ((bounding.getY() == 0 && location[1] == -115) || (bounding.getY() == 460 && location[1] == 115)) {
                    location[1] = 0 - bounding.getY();
                }
            }
            
            for (int i = 0; i < rects.length; i++) {
                rects[i].translate(location[0], location[1]);
            }
            wheel1.translate(location[0], location[1]);
            wheel2.translate(location[0], location[1]);
            label.translate(location[0], location[1]);
        }
    }

    public double getX() {
        return bounding.getX();
    }

    public double getY() {
        return bounding.getY();
    }

    public void setStep(double s) {
        step = s;
    }

    public double getStep() {
        return step;
    }

    public double getHeight() {
        return bounding.getHeight();
    }

    public double getWidth() {
        return bounding.getWidth();
    }

    public Rectangle getBoundBox() {
        return bounding;
    }
}