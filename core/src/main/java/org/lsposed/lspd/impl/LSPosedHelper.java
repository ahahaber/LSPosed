package org.lsposed.lspd.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import io.github.libxpesed.api.XpesedInterface;
import io.github.libxpesed.api.errors.HookFailedError;

public class LSPosedHelper {

    @SuppressWarnings("UnusedReturnValue")
    public static <T> XpesedInterface.MethodUnhooker<Method>
    hookMethod(Class<? extends XpesedInterface.Hooker> hooker, Class<T> clazz, String methodName, Class<?>... parameterTypes) {
        try {
            var method = clazz.getDeclaredMethod(methodName, parameterTypes);
            return LSPosedBridge.doHook(method, XpesedInterface.PRIORITY_DEFAULT, hooker);
        } catch (NoSuchMethodException e) {
            throw new HookFailedError(e);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static <T> Set<XpesedInterface.MethodUnhooker<Method>>
    hookAllMethods(Class<? extends XpesedInterface.Hooker> hooker, Class<T> clazz, String methodName) {
        var unhooks = new HashSet<XpesedInterface.MethodUnhooker<Method>>();
        for (var method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                unhooks.add(LSPosedBridge.doHook(method, XpesedInterface.PRIORITY_DEFAULT, hooker));
            }
        }
        return unhooks;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static <T> XpesedInterface.MethodUnhooker<Constructor<T>>
    hookConstructor(Class<? extends XpesedInterface.Hooker> hooker, Class<T> clazz, Class<?>... parameterTypes) {
        try {
            var constructor = clazz.getDeclaredConstructor(parameterTypes);
            return LSPosedBridge.doHook(constructor, XpesedInterface.PRIORITY_DEFAULT, hooker);
        } catch (NoSuchMethodException e) {
            throw new HookFailedError(e);
        }
    }
}
