package com.chimericdream.miniblockmerchants.client.render;

import com.chimericdream.miniblockmerchants.data.MiniblockTextures;
import com.chimericdream.miniblockmerchants.item.*;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class VillagerConversionItemRenderer extends BlockEntityWithoutLevelRenderer {
    private static final SkullModelBase MODEL = new SkullModel(SkullModel.createHumanoidHeadLayer().bakeRoot());

    public VillagerConversionItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
    }

    private Tuple<String, int[]> getHeadData(Item item) {
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

        if (item instanceof UnusuallyDenseRockItem) {
            return MiniblockTextures.UNUSUALLY_DENSE_ROCK;
        }

        if (item instanceof WagyuBeefItem) {
            return MiniblockTextures.WAGYU_BEEF;
        }

        // https://minecraft-heads.com/custom-heads/miscellaneous/54713-trollface
        return new Tuple<>("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWNjZWMzZmMwNmExYjZiZjk2MmQyMzA3MDRiNjYyM2JiZmI0MzA0M2YwNjY3OTY2YzIwYzg5YzMwYzRhMzMwIn19fQ==", new int[]{-1628763693, 1365655993, -1207198870, 1241365991});
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.@NotNull TransformType mode, @NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, int overlay) {
        Tuple<String, int[]> data = getHeadData(stack.getItem());

        CompoundTag headTexture = new CompoundTag();
        headTexture.putString("Value", data.getA());
        ListTag textureList = new ListTag();
        textureList.add(headTexture);

        CompoundTag properties = new CompoundTag();
        properties.put("textures", textureList);

        CompoundTag owner = new CompoundTag();
        owner.putIntArray("Id", data.getB());
        owner.put("Properties", properties);

        GameProfile gameProfile = NbtUtils.readGameProfile(owner);

        RenderType renderLayer = SkullBlockRenderer.getRenderType(SkullBlock.Types.PLAYER, gameProfile);
        SkullBlockRenderer.renderSkull((Direction) null, 180.0F, 0.0F, matrices, vertexConsumers, light, MODEL, renderLayer);
    }
}
