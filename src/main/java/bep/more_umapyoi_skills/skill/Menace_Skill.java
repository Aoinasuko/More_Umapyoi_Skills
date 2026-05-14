package bep.more_umapyoi_skills.skill;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.tracen.umapyoi.api.UmapyoiAPI;
import net.tracen.umapyoi.registry.skills.UmaSkill;
import net.tracen.umapyoi.utils.UmaSoulUtils;

import java.util.List;

public class Menace_Skill extends UmaSkill {

    public Menace_Skill(Builder builder) {
        super(builder);
    }

    public void applySkill(Level level, LivingEntity user) {

        ItemStack soul = UmapyoiAPI.getUmaSoul(user);

        int skillLevel = this.getSkillLevel() * 2 - 1;
        int wisdom = UmaSoulUtils.getProperty(soul).wisdom();
        int skillTime = (200 * skillLevel) + (wisdom >= 10 ? 200 : wisdom >= 7 ? 100 : 0);

        AABB box = new AABB(user.getX() - 10.0f, user.getY() + 5.0f, user.getZ() - 10.0f, user.getX() + 10.0f, user.getY() - 5.0f, user.getZ() + 10.0f);
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, box);

        if (user instanceof Player player) {
            for (LivingEntity entity : entities) {
                if (entity != user) {
                    if (entity instanceof Player entity_player) {
                        if (!player.canHarmPlayer(entity_player)) {
                            continue;
                        }
                    }
                    entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, skillTime, skillLevel));
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, skillTime, skillLevel));
                    if (level instanceof ServerLevel serverWorld) {
                        for (int i = 0; i < 16; ++i) {
                            serverWorld.sendParticles(ParticleTypes.PORTAL, entity.getX(), entity.getY() + level.random.nextDouble() * 2.0D, entity.getZ(), 1, level.random.nextGaussian(), 0.0D, level.random.nextGaussian(), 0);
                        }
                    }
                }
            }
        }

    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.WOLF_GROWL;
    }

}
