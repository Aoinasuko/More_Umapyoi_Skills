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
        int skillTime = (this.getSkillLevel() * 200) + (UmaSoulUtils.getProperty(soul)[4] >= 10 ? 200 : UmaSoulUtils.getProperty(soul)[4] >= 7 ? 100 : 0);
        int skillLevel = 0;
        user.addEffect(new MobEffectInstance(MUS_MobEffectRegistry.PEGASUS.get(), skillTime, skillLevel));
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.ENDER_DRAGON_FLAP;
    }

}
