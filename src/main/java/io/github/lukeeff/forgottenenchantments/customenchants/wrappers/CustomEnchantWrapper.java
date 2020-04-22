package io.github.lukeeff.forgottenenchantments.customenchants.wrappers;

import io.github.lukeeff.forgottenenchantments.ForgottenEnchantments;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public abstract class CustomEnchantWrapper extends Enchantment {

    protected String name;
    protected int maxLevel;
    protected static final int STARTLEVEL = 1;
    protected int chancePerLevel;

    public CustomEnchantWrapper(String nameSpace, final String NAME, final int MAXLVL, final int CHANCEPERLVL) {
        super(new NamespacedKey(ForgottenEnchantments.getPlugin(), nameSpace));
        this.name = NAME;
        this.maxLevel = MAXLVL;
        this.chancePerLevel = CHANCEPERLVL;
    }


    /**
     * Get the display name of a custom enchantment
     * @return the display name of the custom enchantment
     */
    public String getDisplayName() {
       return name;
    }

    /**
     * Gets the highest level this enchantment can have
     * @return an integer representing the highest level
     */
    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Gets the lowest level this enchantment can have
     * @return an integer representing the lowest level
     */
    @Override
    public int getStartLevel() {
        return STARTLEVEL;
    }

    /**
     * The method that initiates the ability of the custom enchantment
     * @param event the event in which the ability is applicable
     */
    abstract public void combatAbility(EntityDamageByEntityEvent event);

    /**
     * This method returns true a parameter percent of the time
     * <P>The number passed through as a parameter is essentially the probability
     * out of 100 that a boolean will return true. Passing in 20 would return
     * true 20% of the time.</P>
     * @param chance how often it will return true. 100 will always return true.
     */
    boolean chanceAbility(double chance) {
        double randomNumber = Math.random() * 100;
        return(chance > randomNumber);
    }



    @Deprecated
    @Override
    public String getName() {
        return name;
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
