package bep.more_umapyoi_skills.events;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import bep.more_umapyoi_skills.Util.MUS_Util;
import bep.more_umapyoi_skills.skill.MUS_SkillRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tracen.umapyoi.api.UmapyoiAPI;
import net.tracen.umapyoi.effect.MobEffectRegistry;
import net.tracen.umapyoi.utils.UmaSoulUtils;

import java.util.UUID;

@Mod.EventBusSubscriber
public class PassiveSkillEvents {

    public static final UUID PASSIVEUUID_ENOUGHSTRENGTH = UUID.fromString("467cdbc8-4575-4161-b76d-009ad547b0be");
    public static final UUID PASSIVEUUID_STRONGSMASH = UUID.fromString("ff106087-67f6-45f6-8169-8867895641a0");

    @SubscribeEvent
    public static void Passive_PlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        AttributeInstance enoughstrength = player.getAttribute(Attributes.MOVEMENT_SPEED);
        var enoughstrength_modifier = new AttributeModifier(PASSIVEUUID_ENOUGHSTRENGTH, "passive_skill_enoughstrength", 0.05D, AttributeModifier.Operation.ADDITION);

        AttributeInstance strongsmash = player.getAttribute(Attributes.ATTACK_DAMAGE);
        var strongsmash_modifier = new AttributeModifier(PASSIVEUUID_STRONGSMASH, "passive_skill_strongsmash", 0.05D, AttributeModifier.Operation.MULTIPLY_TOTAL);

        ItemStack soul = UmapyoiAPI.getUmaSoul(player);

        // ウマ娘魂を持っていない場合、パッシブのAttribute除去
        if (soul.isEmpty()) {
            enoughstrength.removeModifier(enoughstrength_modifier);
            strongsmash.removeModifier(strongsmash_modifier);
            return;
        }

        // 余力十分
        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.ENOUGHSTRENGTH.getId()) && player.getHealth() >= player.getMaxHealth()) {
            if (!enoughstrength.hasModifier(enoughstrength_modifier)) {
                double SpeedAmount = 0.02D * UmaSoulUtils.getProperty(soul)[0];
                enoughstrength_modifier = new AttributeModifier(PASSIVEUUID_ENOUGHSTRENGTH, "passive_skill_enoughstrength", SpeedAmount, AttributeModifier.Operation.MULTIPLY_TOTAL);
                enoughstrength.addTransientModifier(enoughstrength_modifier);
            }
        } else {
            enoughstrength.removeModifier(enoughstrength_modifier);
        }

        // 力強い一撃
        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.STRONGSMASH.getId())) {
            if (!strongsmash.hasModifier(strongsmash_modifier)) {
                double StrengthAmount = 0.025D * UmaSoulUtils.getProperty(soul)[2];
                strongsmash_modifier = new AttributeModifier(PASSIVEUUID_STRONGSMASH, "passive_skill_enoughstrength", StrengthAmount, AttributeModifier.Operation.MULTIPLY_TOTAL);
                strongsmash.addTransientModifier(strongsmash_modifier);
            }
        } else {
            strongsmash.removeModifier(strongsmash_modifier);
        }

        // 華麗なテクニック
        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.BRILLIANTTECHNIQUE.getId())) {
            if (!player.hasEffect(MobEffectRegistry.PANICKING.get())) {
                int WisdomAmount = UmaSoulUtils.getProperty(soul)[4];
                UmaSoulUtils.addActionPoint(soul, WisdomAmount >= 16 ? 3 : WisdomAmount >= 12 ? 2 : 1);
            }
        }

        // スマートな着地
        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.SMARTLANDING.getId())) {
            float GutsAmount = UmaSoulUtils.getProperty(soul)[3] >= 12 ? 0.0f : UmaSoulUtils.getProperty(soul)[3] >= 7 ? 10.0f : 15.0f;
            if (player.fallDistance >= GutsAmount) {
                player.fallDistance = GutsAmount;
            }
        }

        // 粘り強い脚力
        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.STRONGLEGS.getId())) {
            int StaminaAmount = UmaSoulUtils.getProperty(soul)[1] >= 12 ? 14 : UmaSoulUtils.getProperty(soul)[1] >= 7 ? 10 : 7;
            if (!player.hasEffect(MobEffectRegistry.PANICKING.get())) {
                if (player.getFoodData().getFoodLevel() <= StaminaAmount) {
                    player.getFoodData().setFoodLevel(StaminaAmount);
                }
            }
        }
    }

    // ソウルバウンドのアイテムをインベントリに保持する
    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        MUS_Util.copySoulBoundItems(event.getOriginal(), event.getEntity(), event.isWasDeath());
    }

}
