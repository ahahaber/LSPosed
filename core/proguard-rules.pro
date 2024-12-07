-keep class dx.rxbv.android.xpesed.** {*;}
-keep class io.github.libxpesed.** {*;}
-keepattributes RuntimeVisibleAnnotations
-keep class android.** { *; }
-keepclasseswithmembers,includedescriptorclasses class * {
    native <methods>;
}
-keepclassmembers class org.lsposed.lspd.impl.LSPosedContext {
    public <methods>;
}
-keepclassmembers class org.lsposed.lspd.impl.LSPosedHookCallback {
    public <methods>;
}
-keep,allowoptimization,allowobfuscation @io.github.libxpesed.api.annotations.* class * {
    @io.github.libxpesed.api.annotations.BeforeInvocation <methods>;
    @io.github.libxpesed.api.annotations.AfterInvocation <methods>;
}
-keepclassmembers class org.lsposed.lspd.impl.LSPosedBridge$NativeHooker {
    <init>(java.lang.reflect.Executable);
    callback(...);
}
-keepclassmembers class org.lsposed.lspd.impl.LSPosedBridge$HookerCallback {
    final *** beforeInvocation;
    final *** afterInvocation;
    HookerCallback(...);
}
-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
}
-repackageclasses
-allowaccessmodification
-dontwarn org.slf4j.impl.StaticLoggerBinder
