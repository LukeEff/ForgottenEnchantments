package io.github.lukeeff.forgottenenchantments.customenchants.wrappers;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LightningWrapper extends CustomCombatEnchantWrapper {

    private static final String NAME = "Smitten";
    private static final int MAXLEVEL = 4;
    private static final int chancePerLevel = 5;
    private static final Sound LIGHTNING = Sound.ENTITY_LIGHTNING_BOLT_IMPACT;

    public LightningWrapper(String nameSpace) {
        super(nameSpace);
    }

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

    @Override
    @Deprecated
    public String getName() {
        return NAME;
    }

    public String getDisplayName() {
        return NAME;
    }

    @Override
    public int getMaxLevel() {
        return MAXLEVEL;
    }

    @Override
    public int getStartLevel() {
        return STARTLEVEL;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    @Deprecated
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }
}
