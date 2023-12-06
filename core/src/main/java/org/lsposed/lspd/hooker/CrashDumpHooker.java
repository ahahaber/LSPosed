package org.lsposed.lspd.hooker;

import android.util.Log;

import org.lsposed.lspd.impl.LSPosedBridge;

import io.github.libxpesed.api.XpesedInterface;
import io.github.libxpesed.api.annotations.BeforeInvocation;
import io.github.libxpesed.api.annotations.XpesedHooker;

@XpesedHooker
public class CrashDumpHooker implements XpesedInterface.Hooker {

    @BeforeInvocation
    public static void beforeHookedMethod(XpesedInterface.BeforeHookCallback callback) {
        try {
            var e = (Throwable) callback.getArgs()[0];
            LSPosedBridge.log("Crash unexpectedly: " + Log.getStackTraceString(e));
        } catch (Throwable ignored) {
        }
    }
}
