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
import net.tracen.umapyoi.registry.umadata.Motivations;
import net.tracen.umapyoi.utils.UmaSoulUtils;
import net.tracen.umapyoi.utils.UmaStatusUtils;

public class HardStruggle_Skill extends UmaSkill {

    public HardStruggle_Skill(Builder builder) {
        super(builder);
    }

    public void applySkill(Level level, LivingEntity user) {
        ItemStack soul = UmapyoiAPI.getUmaSoul(user);
        int skillLevel = this.getSkillLevel();
        for (int i = 0; i < skillLevel; i++) {
            UmaStatusUtils.addMotivation(soul);
        }
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.PLAYER_LEVELUP;
    }

}
