package coursera.dailyselfie;

import java.util.Comparator;

public class SelfieComparator implements Comparator {
    @Override
    public int compare (Object o1, Object o2) {
        Selfie first = (Selfie)o1;
        Selfie second = (Selfie)o2;
        return first.compareTo (second);
    }
}
