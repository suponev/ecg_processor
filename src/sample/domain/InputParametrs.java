package sample.domain;

import java.lang.reflect.Field;
import java.util.Map;

public class InputParametrs {

    double c1, c2, c3;
    double s1, s2, s3;

    public InputParametrs(Map<String, Double> values) {
        for (String propName : values.keySet()) {
            write(propName, values.get(propName));
        }
    }

    private void write(String propName, Double value) {
        try {
            Field field = this.getClass().getDeclaredField(propName);
            field.setAccessible(true);
            field.set(this, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}
