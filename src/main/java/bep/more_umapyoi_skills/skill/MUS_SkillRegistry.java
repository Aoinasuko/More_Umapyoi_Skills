package bep.more_umapyoi_skills.skill;

import bep.more_umapyoi_skills.More_Umapyoi_Skills;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tracen.umapyoi.registry.skills.SkillType;
import net.tracen.umapyoi.registry.skills.UmaSkill;

public class MUS_SkillRegistry {

    public static final DeferredRegister<UmaSkill> SKILLS = DeferredRegister.create(UmaSkill.REGISTRY_KEY, More_Umapyoi_Skills.MODID);

    // 飛行系スキル
    public static final RegistryObject<UmaSkill> PEGASUS = SKILLS.register("pegasus", () -> new Pegasus_Skill(new UmaSkill.Builder().level(1).type(SkillType.BUFF).actionPoint(900).requiredWisdom(4)));
    public static final RegistryObject<UmaSkill> LORDOFSKY = SKILLS.register("lordofsky", () -> new Pegasus_Skill(new UmaSkill.Builder().level(2).type(SkillType.BUFF).actionPoint(1400).requiredWisdom(6)));

    // 跳躍系スキル
    public static final RegistryObject<UmaSkill> HIGHJUMP = SKILLS.register("highjump", () -> new Jump_Skill(new UmaSkill.Builder().level(1).type(SkillType.BUFF).actionPoint(100).requiredWisdom(2)));
    public static final RegistryObject<UmaSkill> POWERFULJUMP = SKILLS.register("powerfuljump", () -> new Jump_Skill(new UmaSkill.Builder().level(2).type(SkillType.BUFF).actionPoint(100).requiredWisdom(4)));

    // アクションポイント回復系スキル
    public static final RegistryObject<UmaSkill> TRICKY = SKILLS.register("tricky", () -> new ActionPointHeal_Skill(new UmaSkill.Builder().level(1).type(SkillType.HEAL).actionPoint(0).requiredWisdom(4)));
    public static final RegistryObject<UmaSkill> ACROBAT = SKILLS.register("acrobat", () -> new ActionPointHeal_Skill(new UmaSkill.Builder().level(2).type(SkillType.HEAL).actionPoint(0).requiredWisdom(6)));

    // 調子回復スキル
    public static final RegistryObject<UmaSkill> HARDSTRUGGLE = SKILLS.register("hardstruggle", () -> new HardStruggle_Skill(new UmaSkill.Builder().level(2).type(SkillType.HEAL).actionPoint(1900).requiredWisdom(6)));

}
