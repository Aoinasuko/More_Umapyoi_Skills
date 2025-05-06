package bep.more_umapyoi_skills.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import net.tracen.umapyoi.item.ItemRegistry;
import net.tracen.umapyoi.registry.skills.UmaSkill;

public class ShapelessSkillBookRecipe extends ShapelessRecipe {

    public static final RecipeSerializer<ShapelessSkillBookRecipe> SERIALIZER = new SkillBookRecipeSerializer<>(RecipeSerializer.SHAPELESS_RECIPE, ShapelessSkillBookRecipe::new);

    private final ResourceLocation outputSkill;

    public ShapelessSkillBookRecipe(ShapelessRecipe compose, ResourceLocation output) {
        super(compose.getId(), compose.getGroup(), compose.category(), getResultItem(output), compose.getIngredients());
        this.outputSkill = output;
    }

    private static ItemStack getResultItem(ResourceLocation output) {
        Item item = ForgeRegistries.ITEMS.containsKey(output) ? ForgeRegistries.ITEMS.getValue(output) : ItemRegistry.SKILL_BOOK.get();
        return item.getDefaultInstance();
    }

    public ResourceLocation getOutputSkill() {
        return outputSkill;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryaccess) {
        return this.getResultItem(registryaccess).copy();
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        ItemStack result = ItemRegistry.SKILL_BOOK.get().getDefaultInstance().copy();
        result.getOrCreateTag().putString("skill", this.outputSkill.toString());
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

}
