import pkg.*;
import java.util.ArrayList;
import java.util.Scanner;

public class starter implements InputControl, InputKeyControl {
    public static Face smile = new Face(550, 550, 50, 50);
    public static ArrayList<Car> traffic = new ArrayList<Car>();
    public static Color[] colorList = {Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.PINK, Color.RED, Color.WHITE};
    public static Car sampleCar = new Car(0, 0, Color.RED, "Label");
    public static Rectangle safezone;
    public static boolean start = false;
    public static boolean gameOver = false;
    public static Text winMess = new Text(1440/2, 900/2, "YOU WIN!");
    public static Text loseMess = new Text(1440/2, 900/2, "YOU LOSE...");


    public static void main(String args[]) {
        // please leave following line alone, necessary for keyboard/mouse input
        EasyReader es = new EasyReader();
        KeyController kC = new KeyController(Canvas.getInstance(),new starter());
        MouseController mC = new MouseController(Canvas.getInstance(),new starter());
        Scanner scan = new Scanner(System.in);
        makeRoads();
        
        smile.draw();
        winMess.grow(500, 250);
        winMess.translate(2000, 0);
        winMess.setColor(new Color(255, 200, 0));
        winMess.draw();
        loseMess.grow(500, 250);
        loseMess.translate(2000, 0);
        loseMess.draw();

        while(true) {
            Canvas.pause(0);
            if (start) {
                for (int i = 0; i < traffic.size(); i++) {
                    traffic.get(i).drive(true);
                    if (smile.crash(traffic.get(i))) {
                        gameOver();
                    } else if (smile.getY() < 0) {
                        win();
                    }
                }
            }
        }
    }

    public static void makeRoads() {
        double roadWidth = smile.getHeight();
        Rectangle newRoad = new Rectangle(0, 0, 750, roadWidth);
        for (int i = 0; i < 850; i+=newRoad.getHeight()) {
            newRoad = new Rectangle(0, i, 1440, roadWidth);
            if (roadWidth == sampleCar.getHeight()) {
                newRoad.setColor(Color.GRAY);
                roadWidth = smile.getHeight()+5;
                newRoad.fill();
                for (int j = 0; j < Canvas.rand(5) + 3; j++) {
                    makeCar(Canvas.rand(1400), i);
                }
            } else {
                newRoad.setColor(Color.GREEN);
                roadWidth = sampleCar.getHeight();
                newRoad.fill();
            }
            safezone = new Rectangle(0, i, 1440, 100);
        }
        safezone.setColor(Color.GREEN);
        safezone.fill();
        smile = new Face(safezone.getWidth()/2 - smile.getWidth()/2, safezone.getY(), 40, 40);
    }

    public static void makeCar(int x, int y) {
        Car car = new Car(x, y, colorList[Canvas.rand(colorList.length)], "");
        car.setStep(Canvas.rand(5) + 2);
        car.draw();
        traffic.add(car);
    }

    public static void gameOver() {
        for (int i = 0; i < traffic.size(); i++) {
            traffic.get(i).setStep(0);
        }
        gameOver = true;
        loseMess.translate(1440/2 - loseMess.getX() - loseMess.getWidth()/2, 900/2 - loseMess.getY() - loseMess.getHeight()/2);
    }

    public static void win() {
        for (int i = 0; i < traffic.size(); i++) {
            traffic.get(i).setStep(0);
        }
        gameOver = true;
        winMess.translate(1440/2 - winMess.getX() - winMess.getWidth()/2, 900/2 - winMess.getY() - winMess.getHeight()/2);
    }

    public void onMouseClick(double x, double y) {
        
    }

    public void keyPress(String s) {
        if (!gameOver && start) {
            if (s.equals("w")) {
                smile.translate(0, -10);
            } else if (s.equals("a") && smile.getX() > 0) {
                smile.translate(-10, 0);
            } else if (s.equals("s") && smile.getY()+smile.getHeight() < 900) {
                smile.translate(0, 10);
            } else if (s.equals("d") && smile.getX()+smile.getWidth() < 1440) {
                smile.translate(10, 0);
            }
        }
        if (s.equals("p")) {
            if (start) {
                start = false;
            } else {
                start = true;
            }
        }
        if (s.equals("r")) {
            gameOver = false;
            start = true;
            smile.translate(safezone.getWidth()/2 - smile.getWidth()/2 - smile.getX() - smile.getWidth()/2, safezone.getY() - smile.getY());
            for (int i = 0; i < traffic.size(); i++) {
                traffic.get(i).setStep(Canvas.rand(5) + 2);
            }
            winMess.translate(2000, 0);
            loseMess.translate(2000, 0);
        }
    }
}
