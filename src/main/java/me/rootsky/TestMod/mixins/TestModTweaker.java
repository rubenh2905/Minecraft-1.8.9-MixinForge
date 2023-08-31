package me.rootsky.TestMod.mixins;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestModTweaker implements ITweaker {
    private List<String> launchArgs = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        launchArgs.addAll(args);

        addArgs("gameDir", gameDir);
        addArgs("assetsDir", assetsDir);
        addArgs("version", profile);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();

        MixinEnvironment e = MixinEnvironment.getDefaultEnvironment();
        e.addConfiguration("mixins.TestMod.json");

        // if (e.getObfuscationContext() == null) e.setObfuscationContext("notch");
        if (e.getObfuscationContext() == null) e.setObfuscationContext("searge");

        e.setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {
        return launchArgs.toArray(new String[0]);
        //return new String[]{};
    }

    private void addArgs(String label, Object value) {
        launchArgs.add("--" + label);
        launchArgs.add(value instanceof String ? (String) value :
                value instanceof File ? ((File) value).getAbsolutePath() : ".");
    }
}
