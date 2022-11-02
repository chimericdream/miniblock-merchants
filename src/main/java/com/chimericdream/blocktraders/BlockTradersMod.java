package com.chimericdream.blocktraders;

import com.chimericdream.blocktraders.item.ModItems;
import com.chimericdream.blocktraders.registry.BTProfessions;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockTradersMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("blocktraders");

	public static final ModItems ITEMS = new ModItems();

	@Override
	public void onInitialize() {
		ITEMS.init();

		BTProfessions.init();
		BTProfessions.populateTrades();
	}
}
