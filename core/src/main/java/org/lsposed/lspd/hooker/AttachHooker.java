package org.lsposed.lspd.hooker;

import android.app.ActivityThread;

import io.github.libxpesed.api.XpesedInterface;
import io.github.libxpesed.api.annotations.AfterInvocation;
import io.github.libxpesed.api.annotations.XpesedHooker;

@XpesedHooker
public class AttachHooker implements XpesedInterface.Hooker {

    @AfterInvocation
    public static void afterHookedMethod(XpesedInterface.AfterHookCallback callback) {
        dx.robv.android.xpesed.XpesedInit.loadModules((ActivityThread) callback.getThisObject());
    }
}
