import java.util.ArrayList;

public class Sequence {

    private Sequence() {}

    public static int[] filter(int[] array, ByCondition condition) {
        ArrayList<Integer> res = new ArrayList<>();
        for(int num: array) {
            if(condition.isOk(num))
                res.add(num);
        }
        Object[] res2 = res.toArray();
        int[] res3 = new int[res.size()];
        for(int i=0; i<res.size(); i++) {
            res3[i] = (int) res2[i];
        }
        return res3;
    }
}
