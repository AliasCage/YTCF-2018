import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {

    private static final String SPLITTER = " ";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] points = in.nextLine().split(SPLITTER);
        int n = Integer.parseInt(points[0]);
        int r = Integer.parseInt(points[1]);

        int[] array = Pattern.compile(SPLITTER)
                .splitAsStream(in.nextLine())
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(evaluatePoints(n, r, array));
    }

    private static int evaluatePoints(int N, int R, int[] points) {
        if (N == 0){
            return 0;
        }

        Arrays.sort(points);
        int count = 0;
        int current = points[0];
        exit:
        for (int i = 1; i <= N; i++) {
            if (i >= N) {
                count++;
                break;
            }
            if ((points[i] - current) > R) {
                count++;
                current = points[i];
                continue;
            } else {
                int temp = points[i];
                while ((temp - current) <= R) {
                    if ((points[i] - current) <= R) {
                        temp = points[i];
                    } else {
                        break;
                    }
                    i++;
                    if (i >= N) {
                        count++;
                        break exit;
                    }
                }

                current = temp;
                temp = points[i];
                while ((temp - current) <= R) {
                    if ((points[i] - current) <= R) {
                        temp = points[i];
                    } else {
                        break;
                    }
                    i++;
                    if (i >= N) {
                        count++;
                        break exit;
                    }
                }
                current = points[i];
                count++;
            }
        }
        return count;
    }

}
