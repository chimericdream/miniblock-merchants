package com.chimericdream.miniblockmerchants.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class TextHelpers {
    public static MutableComponent getTooltip(String tooltipId) {
        return formatTooltip(Component.translatable(tooltipId).copy());
    }

    public static MutableComponent formatTooltip(MutableComponent tooltipText) {
        return tooltipText.withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC);
    }

    public static String getColorTranslationKey(String color) {
        return String.format("color.minecraft.%s", color);
    }
}
