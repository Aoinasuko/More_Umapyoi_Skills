package bep.more_umapyoi_skills.mobeffects;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MUS_MobEffectRegistry {
    public static final DeferredRegister<MobEffect> MOBEFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, More_Umapyoi_Skills.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> PEGASUS = MOBEFFECTS.register("pegasus", () -> new Pegasus_Effect(MobEffectCategory.BENEFICIAL, 0xffffff));

    public static final DeferredHolder<MobEffect, MobEffect> ACTION_COOLDOWN = MOBEFFECTS.register("action_cooldown", () -> new NoEffect_Effect(MobEffectCategory.NEUTRAL, 0x888888));
}
