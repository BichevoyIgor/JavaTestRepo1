
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        try {
            start(Cat.class);
            start(Dog.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public static void start(Class testClass) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class reflClas = Class.forName(testClass.getName());
        Constructor constructor = testClass.getConstructor(String.class, int.class);
        Object cat = constructor.newInstance("Murzik", 3);
        Method[] methods = reflClas.getMethods();
        int countBefore = 0;
        int countAfter = 0;

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(BeforeSuite.class)) {
                countBefore = countBefore + 1;
                if (countBefore == 2) {
                    throw new RuntimeException();
                }
            }
            if (methods[i].isAnnotationPresent(AfterSuite.class)) {
                countAfter = countAfter + 1;
                if (countAfter == 2) {
                    throw new RuntimeException();
                }
            }
        }

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(BeforeSuite.class)) {
                methods[i].invoke(cat);
            }
        }

        ArrayList<Method> testMethods = new ArrayList<>();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(Test.class)) {
                testMethods.add(methods[i]);
            }
        }

        Method[] newTestArrayMethods = new Method[testMethods.size()];
        for (int i = 0; i < newTestArrayMethods.length; i++) {
            newTestArrayMethods[i] = testMethods.get(i);
        }

        for (int i = 0; i < newTestArrayMethods.length; i++) {
            for (int j = 0 + 1; j < newTestArrayMethods.length; j++) {
                if (newTestArrayMethods[i].getAnnotation(Test.class).priority() < newTestArrayMethods[j].getAnnotation(Test.class).priority()) {
                    Method m = newTestArrayMethods[i];
                    newTestArrayMethods[i] = newTestArrayMethods[j];
                    newTestArrayMethods[j] = m;
                }
            }
        }

        for (Method newTestMethod : newTestArrayMethods) {
            newTestMethod.invoke(cat);
        }

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(AfterSuite.class)) {
                methods[i].invoke(cat);
            }
        }
    }
}
