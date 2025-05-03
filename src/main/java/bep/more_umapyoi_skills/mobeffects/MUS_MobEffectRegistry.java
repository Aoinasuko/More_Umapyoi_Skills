package bep.more_umapyoi_skills.mobeffects;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MUS_MobEffectRegistry {
    public static final DeferredRegister<MobEffect> MOBEFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, More_Umapyoi_Skills.MODID);

    public static final RegistryObject<MobEffect> PEGASUS = MOBEFFECTS.register("pegasus", () -> new Pegasus_Effect(MobEffectCategory.BENEFICIAL, 0xffffff));

    public static final RegistryObject<MobEffect> ACTION_COOLDOWN = MOBEFFECTS.register("action_cooldown", () -> new NoEffect_Effect(MobEffectCategory.NEUTRAL, 0x888888));
}
