package io.github.lukeeff.forgottenenchantments.item;

import io.github.lukeeff.forgottenenchantments.customenchants.wrappers.CustomEnchantWrapper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

abstract public class AbstractCustomItem {

    private CustomEnchantWrapper KEY;

    public AbstractCustomItem(CustomEnchantWrapper KEY) {
        this.KEY = KEY;
    }

    public ItemStack getCustomItem(Material material, List<String> lore, String displayName) {
        ItemStack customItem = new ItemStack(material);
        ItemMeta customMeta = customItem.getItemMeta();
        setCustomMeta(lore, customMeta, displayName);
        setCustomProperties(customItem, customMeta);
        return customItem;
    }

    private void setCustomMeta(List<String> lore, ItemMeta meta, String displayName) {
        meta.setDisplayName(displayName);
        meta.setLore(lore);
    }

    private void setCustomProperties(ItemStack customItem, ItemMeta customMeta) {
        customItem.addUnsafeEnchantment(KEY, 4);
        customItem.setItemMeta(customMeta);

    }


}
