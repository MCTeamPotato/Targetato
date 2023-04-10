package com.teampotato.targetato;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;

@Mod("targetato")
public class Targetato {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> LIST;
    public static ForgeConfigSpec.ConfigValue<String> MODE;

    public Targetato() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
        MinecraftForge.EVENT_BUS.register(this);
    }

    static {
        ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
        CONFIG_BUILDER.comment("Targetato").push("General");
        MODE = CONFIG_BUILDER.comment("Use 'B' for blacklist, use any other word(s) (e.g.  I love you) for whitelist").define("whitelist or blacklist", "B");
        LIST = CONFIG_BUILDER.defineList("entity list", Lists.newArrayList(), o -> o instanceof String);
        CONFIG_BUILDER.pop();
        COMMON_CONFIG = CONFIG_BUILDER.build();
    }
}