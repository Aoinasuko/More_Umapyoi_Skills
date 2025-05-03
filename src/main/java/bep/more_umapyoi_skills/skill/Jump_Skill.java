package bep.more_umapyoi_skills.skill;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tracen.umapyoi.api.UmapyoiAPI;
import net.tracen.umapyoi.registry.skills.UmaSkill;
import net.tracen.umapyoi.utils.UmaSoulUtils;

public class Jump_Skill extends UmaSkill {

    public Jump_Skill(Builder builder) {
        super(builder);
    }

    public void applySkill(Level level, LivingEntity user) {
        ItemStack soul = UmapyoiAPI.getUmaSoul(user);
        int skillLevel = this.getSkillLevel() * 3;
        int skillTime = 10 + (UmaSoulUtils.getProperty(soul)[2] >= 10 ? 10 : UmaSoulUtils.getProperty(soul)[2] >= 7 ? 5 : 0);
        user.addEffect(new MobEffectInstance(MobEffects.LEVITATION, skillTime, skillLevel));
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.FIREWORK_ROCKET_LAUNCH;
    }

}
