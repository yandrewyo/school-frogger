import pkg.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Face {
    public Scanner scan = new Scanner(System.in);

    private Ellipse head;
    private Ellipse headOutline;

    private Ellipse eye1;
    private Ellipse eye1Outline;
    private Ellipse eye2;
    private Ellipse eye2Outline;
    private Ellipse pupil1;
    private Ellipse pupil2;

    private Ellipse mouth;
    private Ellipse mouthOutline;

    private Ellipse[] all = new Ellipse[10];

    public Face(int x, int y, int width, int height) {
        head = new Ellipse(x, y, width, height);
        headOutline = new Ellipse(x, y, width, height);
        head.setColor(Color.YELLOW);
        all[0] = head;
        all[1] = headOutline;

        eye1 = new Ellipse(head.getX() + 10, head.getY() + 10, 10, 10);
        eye1Outline = new Ellipse(head.getX() + 10, head.getY() + 10, 10, 10);
        eye1.setColor(Color.WHITE);
        all[2] = eye1;
        all[3] = eye1Outline;

        eye2 = new Ellipse(head.getX() + head.getWidth() - 20, head.getY() + 10, 10, 10);
        eye2Outline = new Ellipse(head.getX() + head.getWidth() - 20, head.getY() + 10, 10, 10);
        eye2.setColor(Color.WHITE);
        all[4] = eye2;
        all[5] = eye2Outline;

        pupil1 = new Ellipse(eye1.getX() + 2.5, eye1.getY() + 2.5, 5, 5);
        pupil2 = new Ellipse(eye2.getX() + 2.5, eye2.getY() + 2.5, 5, 5);
        all[6] = pupil1;
        all[7] = pupil2;

        mouth = new Ellipse(eye1.getX(), head.getY() + head.getHeight() - 15, eye2.getX() - eye1.getX() + eye2.getWidth(), 10);
        mouthOutline = new Ellipse(eye1.getX(), head.getY() + head.getHeight() - 15, eye2.getX() - eye1.getX() + eye2.getWidth(), 10);
        mouth.setColor(Color.RED);
        all[8] = mouth;
        all[9] = mouthOutline;
    }

    public void draw() {
        head.fill();
        headOutline.draw();
        eye1.fill();
        eye1Outline.draw();
        eye2.fill();
        eye2Outline.draw();
        pupil1.fill();
        pupil2.fill();
        mouth.fill();
        mouthOutline.draw();
    }

    public int getX() {
        return head.getX();
    }

    public int getY() {
        return head.getY();
    }

    public int getHeight() {
        return head.getHeight();
    }

    public int getWidth() {
        return head.getWidth();
    }

    public void translate(int x, int y) {
        for (int i = 0; i < all.length; i++) {
            all[i].translate(x, y);
        }
    }

    public boolean crash(Car c) {
        return head.contains(c.getBoundBox());
    }
}
