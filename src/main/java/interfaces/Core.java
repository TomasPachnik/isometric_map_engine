package interfaces;

import exceptions.BeanNotFoundException;

public interface Core extends Runnable {

    Object getByName(String name) throws BeanNotFoundException;
}
