import java.io.InputStreamReader;
import java.io.BufferedReader;

public class tcpReader {

    public static void main(String[] args) {

        InputStreamReader isReader = new InputStreamReader(System.in);
        BufferedReader bufReader = new BufferedReader(isReader);
        long lastTime = System.nanoTime();
        /* Baseline system time */
        long sumReceived = 0;
        long sumSent = 0;
        while(true){
            try {
                long time = System.nanoTime();
                /* Baseline time while running*/
                long timeDiff = time - lastTime;
                /* Calculates interval of N seconds*/
                String inputStr = bufReader.readLine();
                if(inputStr == null) {
                    break;
                }
                if (inputStr.matches(".*192\\.168\\.0\\..*>.*"))
                /* Regex to determine if localhost is sending */
                {
                    String[]inputStrArr = inputStr.split("length");
                    if (inputStrArr.length>=2)
                    {
                        inputStr = inputStrArr[1];
                        inputStr = inputStr.replaceAll("\\D+", "");
                        /* Regex to confine byte value after length*/
                        /* System.out.println(inputStr + " Bytes sent")*/
                        sumSent+=Long.valueOf(inputStr);
                    }

                }
                else if (inputStr.matches(".*>.*192\\.168\\.0\\..*"))
                /* Regex to determine if localhost is receiving */
                {
                    String[]inputStrArr = inputStr.split("length");
                    if (inputStrArr.length>=2)
                    {
                        inputStr = inputStrArr[1];
                        inputStr = inputStr.replaceAll("\\D+", "");
                        /* Regex to confine byte value after length*/
                        /* System.out.println(inputStr + " Bytes received")*/
                        sumReceived+=Long.valueOf(inputStr);
                    }
                }
                /* Timer input in milliseconds */
                if (timeDiff > 10000000000L) {
                    /* L because of variable length */
                    lastTime = System.nanoTime();
                    /* Reset timer to baseline after 10 seconds */
                    System.out.println(sumReceived + " bytes received in last 10 seconds");
                    System.out.println(sumSent + " bytes sent in last 10 seconds");
                    sumReceived = 0;
                    sumSent = 0;
                    /* Reset sum to 0 before new iteration */
                }
            }
            catch (Exception e) {
                System.err.println("Error "+e);
            }
        }
    }
}



