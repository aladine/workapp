/**
 * Created by aladine on 15/5/15.
 */
public class zumata {

    public void solve(int test, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int sum = 0;
        int tmp = 0;
        int[] arr = IOUtils.readIntArray(in, n);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
//        System.out.println(sum);
        int balance = 0;
        for (int i = 0; i < n-1; i++) {
            tmp += arr[i];
            if(2*tmp == sum){
                balance = i+1;
                break;
            }
        }
        System.out.println(balance);
    }
}
