package act.inject;

import act.app.App;
import act.event.ActEvent;
import org.osgl.util.E;

import javax.inject.Provider;

/**
 * Used to pass class binding resolution to DI plugin(s)
 */
public abstract class DependencyInjectionBinder<T> extends ActEvent implements Provider<T> {

    private Class<T> targetClass;

    public DependencyInjectionBinder(Object source, Class<T> targetClass) {
        super(source);
        E.NPE(targetClass);
        this.targetClass = targetClass;
    }

    @Override
    public Class eventType() {
        return DependencyInjectionBinder.class;
    }

    public Class<T> targetClass() {
        return targetClass;
    }

    public abstract T resolve(App app);

    @Override
    public T get() {
        return resolve(App.instance());
    }
}
