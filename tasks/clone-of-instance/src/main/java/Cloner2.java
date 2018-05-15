import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Cloner2 {
    private Object cloneObject(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            Object clone = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(obj) == null || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }

                if (field.getType().isPrimitive()
                        || field.getType().equals(String.class)
                        || field.getType().getSuperclass().equals(Number.class)
                        || field.getType().equals(Boolean.class)) {
                    field.set(clone, field.get(obj));
                } else {
                    Object childObj = field.get(obj);

                    if (childObj == obj) {
                        field.set(clone, clone);
                    } else {
                        field.set(clone, cloneObject(field.get(obj)));
                    }
                }
            }

            return clone;
        } catch (Exception e) {
            return null;
        }
    }
}
