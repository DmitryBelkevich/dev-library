import java.lang.reflect.Field;

public class Cloner {
    private Object clone(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            Object clone = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                field.set(clone, field.get(obj));
            }

            return clone;
        } catch (Exception e) {
            return null;
        }
    }
}

