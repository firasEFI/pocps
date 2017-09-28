package findus_pageobjects;

import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public abstract class GenericInstantiator<T> {

    private Class<T> instantiationType;

    private Constructor<T> ctor;

    public GenericInstantiator(Class<T> instantiationType) {
        this.instantiationType = instantiationType;

        if (Modifier.isAbstract(instantiationType.getModifiers())) {
            throw new IllegalArgumentException(instantiationType.getName() + " is abstract");
        }

        if (!Modifier.isPublic(instantiationType.getModifiers())) {
            throw new IllegalArgumentException(instantiationType + " is not public");
        }

        try {
            ctor = instantiationType.getConstructor();
        } catch (NoSuchMethodException x) {
            throw new IllegalArgumentException(x);
        }

        Arrays.stream(ctor.getExceptionTypes())
                .filter(x -> !RuntimeException.class.isAssignableFrom(x) && !Error.class.isAssignableFrom(x))
                .findFirst()
                .ifPresent(x -> { throw new IllegalArgumentException(ctor + " declares a checked exception"); });
    }

    public T create() {
        try {
            return ctor.newInstance();
        } catch (InvocationTargetException x) {
            Throwable cause = x.getCause();

            if (cause instanceof RuntimeException)
                throw (RuntimeException) cause;
            else if (cause instanceof Error)
                throw (Error) cause;

            // This won't happen because we checked for
            // it in the constructor.
            throw new RuntimeException(cause);
        } catch (IllegalAccessException | InstantiationException x) {
            // These also won't happen because we checked
            // for it in the constructor.
            throw new RuntimeException(x);
        }
    }
}
