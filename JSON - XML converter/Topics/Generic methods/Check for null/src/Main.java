// do not remove imports
import java.util.*;
import java.util.function.Function;

class ArrayUtils {
    // define hasNull method here
    public static <T> boolean hasNull(T[] array) {
        return Arrays.asList(array).contains(null);
    }

}
