package io.github.lukeeff.forgottenenchantments.listeners;

import io.github.lukeeff.forgottenenchantments.customenchants.EnchantInitializer;
import io.github.lukeeff.forgottenenchantments.customenchants.wrappers.CustomEnchantWrapper;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CombatEnchantListener implements Listener {


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof LivingEntity && event.getEntity() instanceof LivingEntity) {
            final LivingEntity damager = (LivingEntity) event.getDamager();
            final List<CustomEnchantWrapper> customEnchants = getCustomEnchants(getEnchants(damager));
            customEnchants.forEach(enchant -> enchant.combatAbility(event));
        }
    }




    /**
     * Sorts through a list of enchantments for custom enchantments
     * and returns a new list containing custom enchantments only
     * @param enchants a list of the enchantments on a tool
     * @return a list of the custom enchantments on a tool
     */
    private List<CustomEnchantWrapper> getCustomEnchants(List<Enchantment> enchants) {
        final List<CustomEnchantWrapper> customEnchants = new ArrayList<>();
        enchants.stream().filter(enchant -> EnchantInitializer.customEnchantsList.contains(enchant))
                .forEach(enchant -> customEnchants.add((CustomEnchantWrapper) enchant));
        return customEnchants;
    }

    /**
     * Get a list of the enchantments on a tool
     * @param target the target Entity
     * @return a list of the enchantments on the tool
     */
    private List<Enchantment> getEnchants(LivingEntity target) {
        final ItemMeta toolMeta = target.getEquipment().getItemInMainHand().getItemMeta();
        final Map<Enchantment, Integer> toolEnchants = toolMeta.getEnchants();
        final List<Enchantment> enchantments = new ArrayList<>();
        toolEnchants.forEach((enchantment, level) -> enchantments.add(enchantment));
        return enchantments;
    }


}
