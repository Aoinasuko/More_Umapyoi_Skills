package bep.more_umapyoi_skills.skill;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import bep.more_umapyoi_skills.mobeffects.MUS_MobEffectRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tracen.umapyoi.api.UmapyoiAPI;
import net.tracen.umapyoi.registry.skills.UmaSkill;
import net.tracen.umapyoi.utils.UmaSoulUtils;

public class ActionPointHeal_Skill extends UmaSkill {

    public int SkillHealAmount = 0;

    public ActionPointHeal_Skill(Builder builder) {
        super(builder);
    }

    public void applySkill(Level level, LivingEntity user) {
        this.SkillHealAmount = 0;
        if (user.hasEffect(MUS_MobEffectRegistry.ACTION_COOLDOWN.get())) {
            if(user instanceof Player player) {
                player.displayClientMessage(Component.translatable("info.more_umapyoi_skills.iscooldown"), true);
            }
            return;
        }
        ItemStack soul = UmapyoiAPI.getUmaSoul(user);
        int skillLevel = this.getSkillLevel();
        int healamount = (1000 * skillLevel) + (UmaSoulUtils.getProperty(soul)[3] >= 10 ? 1000 : UmaSoulUtils.getProperty(soul)[2] >= 7 ? 500 : 0);
        // クールダウンを30秒追加
        user.addEffect(new MobEffectInstance(MUS_MobEffectRegistry.ACTION_COOLDOWN.get(), 600, 0));
        // アクションポイントを回復
        this.SkillHealAmount = healamount * -1;
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.PLAYER_LEVELUP;
    }

    @Override
    public int getActionPoint() {
        return this.SkillHealAmount;
    }

}
