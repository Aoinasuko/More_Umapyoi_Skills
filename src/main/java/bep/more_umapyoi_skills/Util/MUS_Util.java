package bep.more_umapyoi_skills.Util;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import bep.more_umapyoi_skills.skill.MUS_SkillRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.neoforged.neoforge.common.NeoForge;
import net.tracen.umapyoi.item.ItemRegistry;
import net.tracen.umapyoi.utils.UmaSoulUtils;
import top.theillusivec4.curios.api.event.DropRulesEvent;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class MUS_Util {
    // ソウルバウンドスキルを持っている
    public static boolean hasSoulBound(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.getItem() == ItemRegistry.UMA_SOUL.get()) {
            return UmaSoulUtils.hasSkill(stack, MUS_SkillRegistry.IMMORTALWILL.getId());
        }
        return false;
    }

    // ソウルバウンドのアイテムを復元する
    public static void copySoulBoundItems(Player oldPlayer, Player newPlayer, boolean wasDeath) {
        if (wasDeath && !(newPlayer.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) || oldPlayer.isSpectator())) {
            for (int i = 0; i < oldPlayer.getInventory().getContainerSize(); i++) {
                ItemStack oldStack = oldPlayer.getInventory().getItem(i);
                ItemStack newStack = newPlayer.getInventory().getItem(i);
                if (MUS_Util.hasSoulBound(oldStack) && !ItemStack.matches(oldStack, newStack)) {
                    if (newStack.isEmpty()) {
                        newPlayer.getInventory().setItem(i, oldStack);
                    } else {
                        newPlayer.getInventory().placeItemBackInInventory(oldStack);
                    }
                }
            }
        }
    }

    // Curiosのインベントリのアイテム保持
    public static void curiosDropListener() {
        NeoForge.EVENT_BUS.<DropRulesEvent>addListener(event -> {
            Entity entity = event.getEntity();
            if (!(entity instanceof ServerPlayer player)) {
                return;
            }
            event.addOverride(stack -> {
                if (hasSoulBound(stack)) {
                    return true;
                }
                return false;
            }, ICurio.DropRule.ALWAYS_KEEP);
        });
    }

}
