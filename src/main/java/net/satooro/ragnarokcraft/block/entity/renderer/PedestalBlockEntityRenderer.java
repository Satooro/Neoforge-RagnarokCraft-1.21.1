package net.satooro.ragnarokcraft.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.satooro.ragnarokcraft.block.entity.PedestalBlockEntity;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(PedestalBlockEntity pBlockEntity, float pPartialTick, PoseStack pPosStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = pBlockEntity.inventory.getStackInSlot(0);
        pPosStack.pushPose();
        /* ORIGINAL */
//        pPosStack.translate(0.5f, 1.15f, 0.5f);
//        pPosStack.scale(0.5f, 0.5f, 0.5f);
        int alturaMax = 5;
        int blocosContados = 0;
        for(int i = 1; i <= alturaMax; i++){
            BlockPos posAcima = pBlockEntity.getBlockPos().above(i);
            BlockState blockState = pBlockEntity.getLevel().getBlockState(posAcima);
            if (!blockState.isAir()) {
                blocosContados++;
            } else {
                break;
            }
        }
        float teste = 1.25f + blocosContados;

        pPosStack.translate(0.5f, teste, 0.5f);
//        pPosStack.translate(0.5f, 5f, 0.5f);
        pPosStack.scale(0.5f, 0.5f, 0.5f);

        pPosStack.mulPose(Axis.YP.rotationDegrees(pBlockEntity.getRenderingRotation()));
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPosStack, pBufferSource, pBlockEntity.getLevel(), 1);
        pPosStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
