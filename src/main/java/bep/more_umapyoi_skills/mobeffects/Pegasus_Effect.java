package bep.more_umapyoi_skills.mobeffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class Pegasus_Effect extends MobEffect {
    public Pegasus_Effect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void addAttributeModifiers(LivingEntity livingentity, @NotNull AttributeMap map, int amplifier) {
        if (livingentity instanceof Player player) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
        super.addAttributeModifiers(livingentity, map, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity livingentity, @NotNull AttributeMap map, int amplifier) {
        if (livingentity instanceof Player player) {
            if (!player.isCreative() && !player.isSpectator()) {
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
                player.onUpdateAbilities();
            }
        }
        super.removeAttributeModifiers(livingentity, map, amplifier);
    }
}
