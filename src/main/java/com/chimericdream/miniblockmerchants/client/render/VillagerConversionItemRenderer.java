package com.chimericdream.miniblockmerchants.client.render;

import com.chimericdream.miniblockmerchants.data.MiniblockTextures;
import com.chimericdream.miniblockmerchants.item.*;
import com.chimericdream.miniblockmerchants.util.NbtHelpers;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Direction;

@Environment(EnvType.CLIENT)
public class VillagerConversionItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private static final SkullEntityModel MODEL = new SkullEntityModel(SkullEntityModel.getHeadTexturedModelData().createModel());

    private Pair<String, int[]> getHeadData(Item item) {
        if (item instanceof AncientShellItem) {
            return MiniblockTextures.ANCIENT_SHELL;
        }

        if (item instanceof BookOfRitualsItem) {
            return MiniblockTextures.BOOK_OF_RITUALS;
        }

        if (item instanceof BuddingCactusItem) {
            return MiniblockTextures.BUDDING_CACTUS;
        }

        if (item instanceof CrystalPhialItem) {
            return MiniblockTextures.CRYSTAL_PHIAL;
        }

        if (item instanceof CultivatedSaplingItem) {
            return MiniblockTextures.CULTIVATED_SAPLING;
        }

        if (item instanceof DrenchedScoreSheetItem) {
            return MiniblockTextures.DRENCHED_SCORE_SHEET;
        }

        if (item instanceof EnchantedRedDeliciousItem) {
            return MiniblockTextures.ENCHANTED_RED_DELICIOUS;
        }

        if (item instanceof EndlessBookshelfItem) {
            return MiniblockTextures.ENDLESS_BOOKSHELF;
        }

        if (item instanceof FineThreadItem) {
            return MiniblockTextures.FINE_THREAD;
        }

        if (item instanceof ForgottenScrapMetalItem) {
            return MiniblockTextures.FORGOTTEN_SCRAP_METAL;
        }

        if (item instanceof FragrantFlowerItem) {
            return MiniblockTextures.FRAGRANT_FLOWER;
        }

        if (item instanceof GalileanSpyglassItem) {
            return MiniblockTextures.GALILEAN_SPYGLASS;
        }

        if (item instanceof MastercraftedIronItem) {
            return MiniblockTextures.MASTERCRAFTED_IRON;
        }

        if (item instanceof MixologyStationItem) {
            return MiniblockTextures.MIXOLOGY_STATION;
        }

        if (item instanceof OvergrownCarrotItem) {
            return MiniblockTextures.OVERGROWN_CARROT;
        }

        if (item instanceof PrismaticHoneycombItem) {
            return MiniblockTextures.PRISMATIC_HONEYCOMB;
        }

        if (item instanceof PureGoldItem) {
            return MiniblockTextures.PURE_GOLD;
        }

        if (item instanceof RadiatingRedstoneItem) {
            return MiniblockTextures.RADIATING_REDSTONE;
        }

        if (item instanceof RottingRecyclingBinItem) {
            return MiniblockTextures.ROTTING_RECYCLING_BIN;
        }

        if (item instanceof SculptingClayItem) {
            return MiniblockTextures.SCULPTING_CLAY;
        }

        if (item instanceof ShimmeringWheatItem) {
            return MiniblockTextures.SHIMMERING_WHEAT;
        }

        if (item instanceof SoakedVillagerPlushieItem) {
            return MiniblockTextures.SOAKED_VILLAGER_PLUSHIE;
        }

        if (item instanceof SparklingBlazePowderItem) {
            return MiniblockTextures.SPARKLING_BLAZE_POWDER;
        }

        if (item instanceof StabilizedExplosionItem) {
            return MiniblockTextures.STABILIZED_EXPLOSION;
        }

        if (item instanceof UnusuallyDenseRockItem) {
            return MiniblockTextures.UNUSUALLY_DENSE_ROCK;
        }

        if (item instanceof WagyuBeefItem) {
            return MiniblockTextures.WAGYU_BEEF;
        }

        // https://minecraft-heads.com/custom-heads/miscellaneous/54713-trollface
        return new Pair<>("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWNjZWMzZmMwNmExYjZiZjk2MmQyMzA3MDRiNjYyM2JiZmI0MzA0M2YwNjY3OTY2YzIwYzg5YzMwYzRhMzMwIn19fQ==", new int[]{-1628763693, 1365655993, -1207198870, 1241365991});
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Pair<String, int[]> data = getHeadData(stack.getItem());

        NbtCompound headTexture = new NbtCompound();
        headTexture.putString("Value", data.getLeft());
        NbtList textureList = new NbtList();
        textureList.add(headTexture);

        NbtCompound properties = new NbtCompound();
        properties.put("textures", textureList);

        NbtCompound owner = new NbtCompound();
        owner.putIntArray("Id", data.getRight());
        owner.put("Properties", properties);

        GameProfile gameProfile = NbtHelpers.toGameProfile(owner);

        RenderLayer renderLayer = SkullBlockEntityRenderer.getRenderLayer(SkullBlock.Type.PLAYER, new ProfileComponent(gameProfile));
        SkullBlockEntityRenderer.renderSkull((Direction) null, 180.0F, 0.0F, matrices, vertexConsumers, light, MODEL, renderLayer);
    }
}
