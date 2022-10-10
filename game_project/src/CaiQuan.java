import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import netscape.javascript.JSUtil;

import java.util.Random;
import java.util.Scanner;
public class CaiQuan {
    public static void main(String[] args) {
        Games games = new Games();
        System.out.println("猜拳游戏：0-石头，1-剪刀，2-布");
        System.out.println("==========================");
        for (int i = 0; i < 3; i++) {
            games.tom();
            games.compute();
            if (games.vscompute()) {
                System.out.println("你赢了！");
                games.countt++;
                games.countfc++;
                games.printgame();
            } else if (games.vscompute() == null) {
                games.countp++;
                games.printgame();
            } else {
                System.out.println("你输了...");
                games.countc++;
                games.countft++;
                games.printgame();
            }
        }
    }
}
class Games {
    int commethod;
    int tommethod;
    int countft;
    int countfc;
    int countt;
    int countc;
    int countp;
    Scanner my = new Scanner(System.in);

    public Boolean vscompute() {
        if (this.tommethod == 0 && this.commethod == 1) {
            return true;
        } else if (this.tommethod == 1 && this.commethod == 2) {
            return true;
        } else if (this.tommethod == 2 && this.commethod == 0) {
            return true;
        } else if (this.commethod == this.tommethod) {
            return null;
        }
        return false;
    }

    public void compute() {
        Random ran = new Random();
        this.commethod = ran.nextInt(3);
    }

    public void tom() {
        System.out.println("你想出什么(0-石头，1-剪刀，2-布)?");
        this.tommethod = my.nextInt();
    }

    public void printgame() {
        System.out.println("==========================");
        System.out.println("当前胜负：");
        System.out.println("\t\t   " + "胜" + "\t\t" + "负" + "\t\t" + "平局");
        System.out.println("tom:" + "\t\t" + this.countt + "\t\t" + this.countft + "\t\t" + this.countp);
        System.out.println("电脑：" + "\t\t" + this.countc + "\t\t" + this.countfc + "\t\t" + this.countp);
        System.out.println("==========================");
    }
}