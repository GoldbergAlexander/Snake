/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/23/15.
 */
public class Food {
    boolean snakeAtPos;
    private int x, y;

    Food() {
        while (true) {
            x = (int) (Math.random() * 20);
            y = (int) (Math.random() * 20);
            if (Snake.snakeAt(x, y) == false) {
                break;
            }
        }
    }

    public int[] foodPos() {
        int[] tmp = new int[2];
        tmp[0] = x;
        tmp[1] = y;
        return tmp;
    }


}
