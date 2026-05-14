package bep.more_umapyoi_skills.events;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import bep.more_umapyoi_skills.Util.MUS_Util;
import bep.more_umapyoi_skills.skill.MUS_SkillRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tracen.umapyoi.api.UmapyoiAPI;
import net.tracen.umapyoi.effect.MobEffectRegistry;
import net.tracen.umapyoi.utils.UmaSoulUtils;

@EventBusSubscriber(modid = More_Umapyoi_Skills.MODID)
public class PassiveSkillEvents {

    public static final ResourceLocation PASSIVE_ENOUGHSTRENGTH = ResourceLocation.fromNamespaceAndPath(More_Umapyoi_Skills.MODID, "passive_skill_enoughstrength");
    public static final ResourceLocation PASSIVE_STRONGSMASH = ResourceLocation.fromNamespaceAndPath(More_Umapyoi_Skills.MODID, "passive_skill_strongsmash");

    @SubscribeEvent
    public static void Passive_PlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        AttributeInstance enoughstrength = player.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeInstance strongsmash = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if (enoughstrength == null || strongsmash == null) {
            return;
        }

        ItemStack soul = UmapyoiAPI.getUmaSoul(player);
        if (soul.isEmpty()) {
            enoughstrength.removeModifier(PASSIVE_ENOUGHSTRENGTH);
            strongsmash.removeModifier(PASSIVE_STRONGSMASH);
            return;
        }

        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.ENOUGHSTRENGTH.getId()) && player.getHealth() >= player.getMaxHealth()) {
            if (!enoughstrength.hasModifier(PASSIVE_ENOUGHSTRENGTH)) {
                double speedAmount = 0.02D * UmaSoulUtils.getProperty(soul).speed();
                enoughstrength.addTransientModifier(new AttributeModifier(PASSIVE_ENOUGHSTRENGTH, speedAmount, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
        } else {
            enoughstrength.removeModifier(PASSIVE_ENOUGHSTRENGTH);
        }

        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.STRONGSMASH.getId())) {
            if (!strongsmash.hasModifier(PASSIVE_STRONGSMASH)) {
                double strengthAmount = 0.025D * UmaSoulUtils.getProperty(soul).strength();
                strongsmash.addTransientModifier(new AttributeModifier(PASSIVE_STRONGSMASH, strengthAmount, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
        } else {
            strongsmash.removeModifier(PASSIVE_STRONGSMASH);
        }

        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.BRILLIANTTECHNIQUE.getId())) {
            if (!player.hasEffect(MobEffectRegistry.PANICKING)) {
                int wisdomAmount = UmaSoulUtils.getProperty(soul).wisdom();
                UmaSoulUtils.addActionPoint(soul, wisdomAmount >= 16 ? 3 : wisdomAmount >= 12 ? 2 : 1);
            }
        }

        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.SMARTLANDING.getId())) {
            int guts = UmaSoulUtils.getProperty(soul).guts();
            float gutsAmount = guts >= 12 ? 0.0f : guts >= 7 ? 10.0f : 15.0f;
            if (player.fallDistance >= gutsAmount) {
                player.fallDistance = gutsAmount;
            }
        }

        if (UmaSoulUtils.hasSkill(soul, MUS_SkillRegistry.STRONGLEGS.getId())) {
            int stamina = UmaSoulUtils.getProperty(soul).stamina();
            int staminaAmount = stamina >= 12 ? 14 : stamina >= 7 ? 10 : 7;
            if (!player.hasEffect(MobEffectRegistry.PANICKING) && player.getFoodData().getFoodLevel() <= staminaAmount) {
                player.getFoodData().setFoodLevel(staminaAmount);
            }
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        MUS_Util.copySoulBoundItems(event.getOriginal(), event.getEntity(), event.isWasDeath());
    }
}
