package Games;

import java.util.concurrent.TimeUnit;

public class ZigZag {

    private int indent = 0;
    private boolean increasingIndent = true;

    public void printEmptySpaces() {
        for (int i = 0; i <= indent; i++) {
            System.out.print(" ");
        }
    }

    public void printHashesSimple() {
        System.out.print("#########\n");
    }

    public void zigZagGame() throws InterruptedException {
        while (true) {
            printEmptySpaces();
            printHashesSimple();
            TimeUnit.SECONDS.sleep(1);

            if (increasingIndent) {
                indent++;
                if (indent == 20) {
                    increasingIndent = false;
                }
            }
            else {
                indent--;
                if (indent == 0) {
                    increasingIndent = true;
                }
            }

        }

    }
}

/*
    int spaceIncrementer = 0;
    boolean movingRight = true;

    public void printEmptySpace() {
        for (int i = 0; i <= spaceIncrementer; i++ ) {
            System.out.print(" ");
        }
    }

    public void printHashes(){
        System.out.print("#########\n");
        if (!decideToTurn(spaceIncrementer)) {
            spaceIncrementer++;
        }
        else {
            spaceIncrementer--;
        }
    }

    public void runZigZagGame() throws InterruptedException {
        while (true) {
            printEmptySpace();
            printHashes();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public boolean decideToTurn(int num){
        if (num >= 10){
            return true;
        }
        return false;

    }

 */
