package io.github.lukeeff.forgottenenchantments.customenchants.wrappers;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class LightningWrapper extends CustomEnchantWrapper {

    private static final String NAME = "Smitten";
    private static final int MAXLEVEL = 4;
    private static final int CHANCEPERLEVEL = 5;
    private static final Sound LIGHTNING = Sound.ENTITY_LIGHTNING_BOLT_IMPACT;
    protected static final float VOLUME = 1;
    protected static final float PITCH = 1;

    public LightningWrapper(String nameSpace) {
        super(nameSpace, NAME, MAXLEVEL, CHANCEPERLEVEL);
    }

    /**
     * The lightning combat ability method called when a living entity
     * attacks another living entity.
     * @param event the event in which the ability is applicable
     */
    @Override
    public void combatAbility(EntityDamageByEntityEvent event) {
        ItemMeta meta =((LivingEntity) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta();
        final int level = meta.getEnchantLevel(this);
        double chanceToStrike = level * chancePerLevel;
        if(chanceAbility(chanceToStrike)) {
            final Entity victim = event.getEntity();
            smiteEntity(victim);
        }
    }

    /**
     * Strikes lightning at the location of a target victim
     * @param victim the target entity to be smitten
     */
    private void smiteEntity(Entity victim) {
        Location location = victim.getLocation();
        World world = location.getWorld();
        world.strikeLightning(location);
        world.playSound(location, LIGHTNING, VOLUME, PITCH);
    }

}
