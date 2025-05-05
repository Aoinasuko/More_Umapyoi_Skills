package bep.more_umapyoi_skills.skill;

import bep.more_umapyoi_skills.mobeffects.MUS_MobEffectRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tracen.umapyoi.api.UmapyoiAPI;
import net.tracen.umapyoi.registry.skills.UmaSkill;
import net.tracen.umapyoi.utils.UmaSoulUtils;
import net.tracen.umapyoi.utils.UmaStatusUtils;

public class Phoenix_Skill extends UmaSkill {

    public Phoenix_Skill(Builder builder) {
        super(builder);
    }

    public void applySkill(Level level, LivingEntity user) {
        if (user.hasEffect(MUS_MobEffectRegistry.ACTION_COOLDOWN.get())) {
            if(user instanceof Player player) {
                player.displayClientMessage(Component.translatable("info.more_umapyoi_skills.iscooldown"), true);
            }
            return;
        }
        ItemStack soul = UmapyoiAPI.getUmaSoul(user);
        int skillTime = 40 + (UmaSoulUtils.getProperty(soul)[0] >= 10 ? 20 : UmaSoulUtils.getProperty(soul)[0] >= 7 ? 10 : 0);
        user.heal(9999f);
        user.addEffect(new MobEffectInstance(MobEffects.SATURATION, skillTime / 4, 0));
        user.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, skillTime, 4));
        // クールダウンを30秒追加
        user.addEffect(new MobEffectInstance(MUS_MobEffectRegistry.ACTION_COOLDOWN.get(), 600, 0));
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

}
