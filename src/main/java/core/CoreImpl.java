package core;

import interfaces.Core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import annotations.Autowired;
import annotations.Bean;
import annotations.Config;
import exceptions.BeanNotFoundException;
import exceptions.MultipleBeansWithSameNameException;
import exceptions.WrongConfigClassException;

public class CoreImpl implements Core {

    private Map<String, Object> beans;

    public CoreImpl(Class<?> objectClass) {
        beans = new HashMap<String, Object>();
        try {
            build(objectClass);
            fill();
            this.run();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | WrongConfigClassException
                | MultipleBeansWithSameNameException | BeanNotFoundException e) {
            System.err.println(e);
        }
    }

    private void build(Class<?> objectClass) throws WrongConfigClassException, InstantiationException, IllegalAccessException,
            MultipleBeansWithSameNameException, IllegalArgumentException, InvocationTargetException {
        checkConfigClass(objectClass);
        Object object = objectClass.newInstance();

        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(Bean.class)) {
                Object generatedObject = method.invoke(object);
                beans.put(method.getName(), generatedObject);
            }
        }
    }

    private void checkConfigClass(Class<?> objectClass) throws WrongConfigClassException {
        if (objectClass == null || !objectClass.isAnnotationPresent((Class<? extends Annotation>) Config.class)) {
            throw new WrongConfigClassException();
        }
    }

    private void fill() throws IllegalArgumentException, IllegalAccessException, BeanNotFoundException {
        for (Entry<String, Object> entry : beans.entrySet()) {
            for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent((Class<? extends Annotation>) Autowired.class)) {
                    field.setAccessible(true);
                    if (field.getAnnotation(Autowired.class).value().equals("")) {
                        field.set(entry.getValue(), getByName(field.getName()));
                    } else {
                        field.set(entry.getValue(), getByName(field.getAnnotation(Autowired.class).value()));
                    }
                }
            }
        }
    }

    @Override
    public Object getByName(String name) throws BeanNotFoundException {
        if (beans.containsKey(name)) {
            return beans.get(name);
        } else {
            throw new BeanNotFoundException(name);
        }
    }

    @Override
    public void run() {
        // mainScreen.draw();
        // core.start();

    }

}
