package com.chimericdream.blocktraders.client.render;

import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.Direction;

@Environment(EnvType.CLIENT)
public class SoakedVillagerPlushieItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private static SkullEntityModel MODEL = new SkullEntityModel(SkullEntityModel.getHeadTexturedModelData().createModel());

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        NbtCompound headTexture = new NbtCompound();
        headTexture.putString("Value", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzkxNzc0ZThlNDNjZjUzNWUzMDk3MTZhMDNiNGE0YTgxMTA4NzkzZGU3NjhlYmQzZjEyMjRlNjgwMjFmZTk3ZSJ9fX0=");
        NbtList textureList = new NbtList();
        textureList.add(headTexture);

        NbtCompound properties = new NbtCompound();
        properties.put("textures", textureList);

        NbtCompound owner = new NbtCompound();
        owner.putIntArray("Id", new int[]{517903274, 303778841, -1751461796, 1896795803});
        owner.put("Properties", properties);

        GameProfile gameProfile = NbtHelper.toGameProfile(owner);

        RenderLayer renderLayer = SkullBlockEntityRenderer.getRenderLayer(SkullBlock.Type.PLAYER, gameProfile);
        SkullBlockEntityRenderer.renderSkull((Direction) null, 180.0F, 0.0F, matrices, vertexConsumers, light, MODEL, renderLayer);
    }
}
