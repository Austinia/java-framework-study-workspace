package com.austinia.user;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class UserRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("**************** Request init ****************");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("**************** Request destroy ****************");
    }
}
