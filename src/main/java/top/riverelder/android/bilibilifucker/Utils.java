package top.riverelder.android.bilibilifucker;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import de.robv.android.xposed.XposedBridge;

public class Utils {

    //#region Debug tools

    public static final String LOG_HEADER = "BilibiliFucker: ";


    public static void log(String message) {
        XposedBridge.log(LOG_HEADER + message);
    }

    public static void printBean(Object object) {
        printBean(object, 1, new HashSet<>(), -1);
    }

    public static void printBean(Object object, int maxLevel) {
        printBean(object, 1, new HashSet<>(), maxLevel);
    }

    public static void printBean(Object object, int indentLevel, Set<Object> visited, int maxLevel) {
        if (object == null || (maxLevel >= 0 && indentLevel > maxLevel)) return;

        String indent = repeatString("|---", indentLevel);
        Class<?> clazz = object.getClass();

        if (!isLiteral(clazz)) {
            visited.add(object);
        }

        if (clazz.isArray()) {
            int length = Array.getLength(object);
            for (int i = 0; i < length; i++) {
                printBeanLine(indent, "[" + i + "]", Array.get(object, i), indentLevel, visited, maxLevel);
            }
        } if (object instanceof Collection) {
            Collection<?> collection = (Collection<?>) object;
            int i = 0;
            for (Object value : collection) {
                printBeanLine(indent, "[" + i + "]", value, indentLevel, visited, maxLevel);
                i++;
            }
        } if (object instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) object;
            Set<? extends Map.Entry<?, ?>> entries = map.entrySet();
            for (Map.Entry<?, ?> entry : entries) {
                printBeanLine(indent, "[" + entry.getKey() + "]", entry.getValue(), indentLevel, visited, maxLevel);
            }
        } else {
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) continue;

                field.setAccessible(true);
                try {
                    printBeanLine(indent, field.getName(), field.get(object), indentLevel, visited, maxLevel);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printBeanLine(String indent, String name, @Nullable Object value, int indentLevel, Set<Object> visited, int maxLevel) {
        String valueString = (value instanceof String && ((String) value).matches("^\\s*$")) ? "\"\"" : String.valueOf(value);
        if (value == null || isLiteral(value.getClass())) {
            log(indent + name + ": " + valueString);
        } else {
            if (visited.contains(value)) {
                log(indent + name + ": " + valueString + " (loop reference)");
            } else {
                log(indent + name + ": " + valueString);
                printBean(value, indentLevel + 1, visited, maxLevel);
            }
        }
    }

    public static boolean isLiteral(Class<?> clazz) {
        return clazz.isPrimitive() ||
                Number.class.isAssignableFrom(clazz) ||
                clazz == String.class ||
                clazz == Boolean.class;
    }

    public static String repeatString(String string, int amount) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static void printStackTrace() {
        for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
            log(stackTraceElement.getClassName() + "#" + stackTraceElement.getMethodName());
        }
    }

    @Nullable
    public static Object getDescendantField(Object object, String... path) throws Exception {
        Object currentObject = object;
        for (String fieldName : path) {
            Class<?> clazz = Objects.requireNonNull(currentObject).getClass();
            Field field = clazz.getDeclaredField(fieldName);
            Objects.requireNonNull(field).setAccessible(true);
            currentObject = field.get(currentObject);
        }
        return currentObject;
    }

    public static void setDescendantField(Object object, String[] path, @Nullable Object value) throws Exception {
        Object currentObject = object;
        Field field = null;
        for (int i = 0; i < path.length; i++) {
            String fieldName = path[i];
            Class<?> clazz = Objects.requireNonNull(currentObject).getClass();
            field = clazz.getDeclaredField(fieldName);
            Objects.requireNonNull(field).setAccessible(true);
            if (i < path.length - 1) {
                currentObject = field.get(currentObject);
            }
        }
        Objects.requireNonNull(field).set(currentObject, value);
    }

    static class BufferedResult {
        public final boolean succeeded;
        @Nullable public final Object result;

        BufferedResult(boolean succeeded, @Nullable Object result) {
            this.succeeded = succeeded;
            this.result = result;
        }

        BufferedResult(boolean succeeded) {
            this(succeeded, null);
        }
    }

    @NonNull
    public static BufferedResult tryGetDescendantField(Object object, String... path) throws Exception {
        Object currentObject = object;
        for (String fieldName : path) {
            if (currentObject == null) return new BufferedResult(false);
            Class<?> clazz = currentObject.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            Objects.requireNonNull(field).setAccessible(true);
            currentObject = field.get(currentObject);
        }
        return new BufferedResult(true, currentObject);
    }

    public static boolean trySetDescendantField(Object object, String[] path, @Nullable Object value) throws Exception {
        Object currentObject = object;
        Field field = null;
        for (int i = 0; i < path.length; i++) {
            if (currentObject == null) return false;
            String fieldName = path[i];
            Class<?> clazz = currentObject.getClass();
            field = clazz.getDeclaredField(fieldName);
            Objects.requireNonNull(field).setAccessible(true);
            if (i < path.length - 1) {
                currentObject = field.get(currentObject);
            }
        }
        Objects.requireNonNull(field).set(currentObject, value);
        return true;
    }

    //#endregion

}
