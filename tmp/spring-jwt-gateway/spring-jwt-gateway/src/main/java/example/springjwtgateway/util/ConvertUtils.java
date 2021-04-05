package example.springjwtgateway.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertUtils {

    public static double convertToThousand(long val) {
        return BigDecimal.valueOf((double)val/1000).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
