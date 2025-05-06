package bep.more_umapyoi_skills.mixin;

import bep.more_umapyoi_skills.Util.MUS_Util;
import bep.more_umapyoi_skills.skill.MUS_SkillRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.tracen.umapyoi.item.ItemRegistry;
import net.tracen.umapyoi.utils.UmaSoulUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(Inventory.class)
public class InventoryMixin {

    @Shadow
    @Final
    public Player player;

    @Shadow
    @Final
    private List<NonNullList<ItemStack>> compartments;

    @Unique
    private final Map<int[], ItemStack> more_umapyoi_skills$isSoulBound = new HashMap<>();

    @Inject(method = "dropAll", at = @At("HEAD"))
    public void more_umapyoi_skills$reserveItems(CallbackInfo callbackInfo) {
        for (int listIndex = 0; listIndex < compartments.size(); listIndex++) {
            NonNullList<ItemStack> list = compartments.get(listIndex);
            for (int itemIndex = 0; itemIndex < list.size(); itemIndex++) {
                ItemStack itemStack = list.get(itemIndex);
                if (more_umapyoi_skills$isSoulBound(itemStack)) {
                    more_umapyoi_skills$isSoulBound.put(new int[]{listIndex, itemIndex}, itemStack);
                    list.set(itemIndex, ItemStack.EMPTY);
                }
            }
        }
    }

    @Inject(method = "dropAll", at = @At("RETURN"))
    public void more_umapyoi_skills$restoreItems(CallbackInfo callbackInfo) {
        more_umapyoi_skills$isSoulBound.forEach((position, itemStack) -> compartments.get(position[0]).set(position[1], itemStack));
        more_umapyoi_skills$isSoulBound.clear();
    }

    @Unique
    public boolean more_umapyoi_skills$isSoulBound(ItemStack stack) {
        if (this.player.isAlive()) {
            return true;
        }
        if (MUS_Util.hasSoulBound(stack)) {
            return true;
        }
        return false;
    }

}
