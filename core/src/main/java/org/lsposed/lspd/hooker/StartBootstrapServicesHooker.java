/*
 * This file is part of LSPosed.
 *
 * LSPosed is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LSPosed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LSPosed.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2020 EdXposed Contributors
 * Copyright (C) 2021 LSPosed Contributors
 */

package org.lsposed.lspd.hooker;

import static org.lsposed.lspd.util.Utils.logD;

import androidx.annotation.NonNull;

import org.lsposed.lspd.impl.LSPosedContext;
import org.lsposed.lspd.util.Hookers;

import dx.rxbv.android.xpesed.XpesedBridge;
import dx.rxbv.android.xpesed.callbacks.XC_LoadPackage;
import io.github.libxpesed.api.XpesedInterface;
import io.github.libxpesed.api.XpesedModuleInterface;
import io.github.libxpesed.api.annotations.BeforeInvocation;
import io.github.libxpesed.api.annotations.XpesedHooker;

@XpesedHooker
public class StartBootstrapServicesHooker implements XpesedInterface.Hooker {

    @BeforeInvocation
    public static void beforeHookedMethod() {
        logD("SystemServer#startBootstrapServices() starts");

        try {
            dx.rxbv.android.xpesed.XpesedInit.loadedPackagesInProcess.add("android");

            XC_LoadPackage.LoadPackageParam lpparam = new XC_LoadPackage.LoadPackageParam(XpesedBridge.sLoadedPackageCallbacks);
            lpparam.packageName = "android";
            lpparam.processName = "android"; // it's actually system_server, but other functions return this as well
            lpparam.classLoader = HandleSystemServerProcessHooker.systemServerCL;
            lpparam.appInfo = null;
            lpparam.isFirstApplication = true;
            XC_LoadPackage.callAll(lpparam);

            LSPosedContext.callOnSystemServerLoaded(new XpesedModuleInterface.SystemServerLoadedParam() {
                @Override
                @NonNull
                public ClassLoader getClassLoader() {
                    return HandleSystemServerProcessHooker.systemServerCL;
                }
            });
        } catch (Throwable t) {
            Hookers.logE("error when hooking startBootstrapServices", t);
        }
    }
}
