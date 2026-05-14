package bep.more_umapyoi_skills.mobeffects;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.NeoForgeMod;

public class Pegasus_Effect extends MobEffect {
    private static final ResourceLocation CREATIVE_FLIGHT_MODIFIER = ResourceLocation.fromNamespaceAndPath(More_Umapyoi_Skills.MODID, "pegasus_creative_flight");

    public Pegasus_Effect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(NeoForgeMod.CREATIVE_FLIGHT, CREATIVE_FLIGHT_MODIFIER, 1.0, AttributeModifier.Operation.ADD_VALUE);
    }
}
