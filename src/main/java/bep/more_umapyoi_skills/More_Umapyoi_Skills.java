package bep.more_umapyoi_skills;

import bep.more_umapyoi_skills.Util.MUS_Util;
import bep.more_umapyoi_skills.mobeffects.MUS_MobEffectRegistry;
import bep.more_umapyoi_skills.skill.MUS_SkillRegistry;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(More_Umapyoi_Skills.MODID)
public class More_Umapyoi_Skills {

    public static final String MODID = "more_umapyoi_skills";
    public static final Logger LOGGER = LogUtils.getLogger();

    public More_Umapyoi_Skills(IEventBus modEventBus) {
        MUS_MobEffectRegistry.MOBEFFECTS.register(modEventBus);
        MUS_SkillRegistry.SKILLS.register(modEventBus);

        MUS_Util.curiosDropListener();
    }
}
