package com.chimericdream.blocktraders;

import com.chimericdream.blocktraders.registry.BTProfessions;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockTradersMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("blocktraders");

	@Override
	public void onInitialize() {
		BTProfessions.init();
		BTProfessions.populateTrades();
	}
}
