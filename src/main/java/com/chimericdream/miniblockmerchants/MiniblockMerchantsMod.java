package com.chimericdream.miniblockmerchants;

import com.chimericdream.miniblockmerchants.config.ConfigManager;
import com.chimericdream.miniblockmerchants.item.ModItems;
import com.chimericdream.miniblockmerchants.loot.MMLootTables;
import com.chimericdream.miniblockmerchants.registry.MMProfessions;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiniblockMerchantsMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(ModInfo.MOD_ID);

	public static final ModItems ITEMS = new ModItems();

	static {
		ConfigManager.registerAutoConfig();
	}

	@Override
	public void onInitialize() {
		ITEMS.init();

		MMProfessions.init();
		MMProfessions.populateTrades();

		MMLootTables.init();
	}
}
