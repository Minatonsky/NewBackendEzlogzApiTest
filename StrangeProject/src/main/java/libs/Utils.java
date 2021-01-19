package libs;

public class Utils {

    /**
     * Hard wait
     * @param second
     */
    public static void waitABit(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
