package bep.more_umapyoi_skills;

import bep.more_umapyoi_skills.mobeffects.MUS_MobEffectRegistry;
import bep.more_umapyoi_skills.skill.MUS_SkillRegistry;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(More_Umapyoi_Skills.MODID)
public class More_Umapyoi_Skills {

    // MODID
    public static final String MODID = "more_umapyoi_skills";
    // ログ
    public static final Logger LOGGER = LogUtils.getLogger();

    public More_Umapyoi_Skills() {
    }

    public More_Umapyoi_Skills(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // MobEffectを登録
        MUS_MobEffectRegistry.MOBEFFECTS.register(modEventBus);

        // UmaSkillを登録
        MUS_SkillRegistry.SKILLS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

}

