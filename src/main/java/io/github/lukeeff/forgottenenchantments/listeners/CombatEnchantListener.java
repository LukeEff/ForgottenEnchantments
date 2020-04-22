package io.github.lukeeff.forgottenenchantments.listeners;

import io.github.lukeeff.forgottenenchantments.customenchants.EnchantInitializer;
import io.github.lukeeff.forgottenenchantments.customenchants.wrappers.CustomCombatEnchantWrapper;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CombatEnchantListener implements Listener {

    private EntityDamageByEntityEvent event;


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof LivingEntity && event.getEntity() instanceof LivingEntity) {
            this.event = event;
            final LivingEntity damager = (LivingEntity) event.getDamager();
            processAttack(damager);

        }
    }

    /**
     * Processes the attack from a target damager
     * @param damager the entity that attacked another
     */
    private void processAttack(LivingEntity damager) {
        final ItemStack tool = damager.getEquipment().getItemInMainHand();
        if((tool.getType().equals(Material.AIR))) { return; } //For readability. Could be in parent conditional.
        final ItemMeta toolMeta = tool.getItemMeta();
        final List<Enchantment> enchants = getEnchants(toolMeta);
        processAbilities(getCustomEnchants(enchants));

    }

    /**
     * Processes the abilities of each custom enchantment on a tool
     * @param customEnchants a list of the custom enchantments on the tool
     */
    private void processAbilities(List<CustomCombatEnchantWrapper> customEnchants) {
        customEnchants.forEach(enchant -> enchant.combatAbility(event));
    }

    /**
     * Sorts through a list of enchantments for custom enchantments
     * and returns a new list containing custom enchantments only
     * @param enchants a list of the enchantments on a tool
     * @return a list of the custom enchantments on a tool
     */
    private List<CustomCombatEnchantWrapper> getCustomEnchants(List<Enchantment> enchants) {
        final List<CustomCombatEnchantWrapper> customEnchants = new ArrayList<>();
        enchants.stream().filter(enchant -> EnchantInitializer.customEnchantsList.contains(enchant))
                .forEach(enchant -> customEnchants.add((CustomCombatEnchantWrapper) enchant));
        return customEnchants;
    }

    /**
     * Get a list of the enchantments on a tool
     * @param toolMeta the target tool's ItemMeta
     * @return a list of the enchantments on the tool
     */
    private List<Enchantment> getEnchants(ItemMeta toolMeta) {
        final Map<Enchantment, Integer> toolEnchants = toolMeta.getEnchants();
        final List<Enchantment> enchantments = new ArrayList<>();
        toolEnchants.forEach((enchantment, level) -> enchantments.add(enchantment));
        return enchantments;
    }


}
