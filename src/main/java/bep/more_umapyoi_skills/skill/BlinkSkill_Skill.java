package bep.more_umapyoi_skills.skill;

import bep.more_umapyoi_skills.mobeffects.MUS_MobEffectRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tracen.umapyoi.api.UmapyoiAPI;
import net.tracen.umapyoi.registry.skills.UmaSkill;
import net.tracen.umapyoi.utils.UmaSoulUtils;
import org.joml.Vector3d;

public class BlinkSkill_Skill extends UmaSkill {

    public BlinkSkill_Skill(Builder builder) {
        super(builder);
    }

    public void applySkill(Level level, LivingEntity user) {
        user.fallDistance = 0f;
        user.hurtMarked = true;
        user.setDeltaMovement(user.getLookAngle());
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.ENDER_DRAGON_FLAP;
    }

}
