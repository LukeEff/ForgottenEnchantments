package io.github.lukeeff.forgottenenchantments.item.customitem;

import io.github.lukeeff.forgottenenchantments.customenchants.EnchantInitializer;
import io.github.lukeeff.forgottenenchantments.customenchants.wrappers.CustomEnchantWrapper;
import io.github.lukeeff.forgottenenchantments.item.AbstractCustomItem;

public class LightningComponent extends AbstractCustomItem {

    public LightningComponent() {
        super((CustomEnchantWrapper) EnchantInitializer.LIGHTNING);
    }

}
