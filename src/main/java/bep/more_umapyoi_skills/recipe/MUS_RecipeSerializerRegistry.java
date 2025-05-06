package bep.more_umapyoi_skills.recipe;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MUS_RecipeSerializerRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, More_Umapyoi_Skills.MODID);

    public static final RegistryObject<RecipeSerializer<?>> SHARPLESS_SKILLBOOK = RECIPE_SERIALIZER.register("shapeless_skillbook", () -> ShapelessSkillBookRecipe.SERIALIZER);
}
