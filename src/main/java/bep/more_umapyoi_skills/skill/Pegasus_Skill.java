package bep.more_umapyoi_skills.skill;

import bep.more_umapyoi_skills.mobeffects.MUS_MobEffectRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tracen.umapyoi.api.UmapyoiAPI;
import net.tracen.umapyoi.registry.skills.UmaSkill;
import net.tracen.umapyoi.utils.UmaSoulUtils;

public class Pegasus_Skill extends UmaSkill {

    public Pegasus_Skill(UmaSkill.Builder builder) {
        super(builder);
    }

    public void applySkill(Level level, LivingEntity user) {
        ItemStack soul = UmapyoiAPI.getUmaSoul(user);
        int wisdom = UmaSoulUtils.getProperty(soul).wisdom();
        int skillTime = (this.getSkillLevel() * 200) + (wisdom >= 10 ? 200 : wisdom >= 7 ? 100 : 0);
        int skillLevel = 0;
        user.addEffect(new MobEffectInstance(MUS_MobEffectRegistry.PEGASUS, skillTime, skillLevel));
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.ENDER_DRAGON_FLAP;
    }

}
