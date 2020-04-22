package io.github.lukeeff.forgottenenchantments.customenchants.wrappers;

import io.github.lukeeff.forgottenenchantments.ForgottenEnchantments;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public abstract class CustomCombatEnchantWrapper extends Enchantment {

    static final float VOLUME = 1;
    static final float PITCH = 1;
    static final int STARTLEVEL = 1;


    CustomCombatEnchantWrapper(String nameSpace) {
        super(new NamespacedKey(ForgottenEnchantments.getPlugin(), nameSpace));
    }

    /**
     * The method that initiates the ability of the custom enchantment
     * @param event the event in which the ability is applicable
     */
    abstract public void combatAbility(EntityDamageByEntityEvent event);

    /**
     * Get the display name of a custom enchantment
     * @return the display name of the custom enchantment
     */
    abstract public String getDisplayName();

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


}
