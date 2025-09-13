package git.david.cuffedplus;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import git.david.cuffedplus.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class JumpsuitLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {


    private static final ResourceLocation DCLASS_CLASSIC_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/dclass_jumpsuit_classic.png");
    private static final ResourceLocation DCLASS_SLIM_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/dclass_jumpsuit_slim.png");
    private static final ResourceLocation JUMPSUIT1_CLASSIC_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit1_classic.png");
    private static final ResourceLocation JUMPSUIT1_SLIM_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit1_slim.png");
    private static final ResourceLocation JUMPSUIT2_CLASSIC_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit2_classic.png");
    private static final ResourceLocation JUMPSUIT2_SLIM_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit2_slim.png");
    private static final ResourceLocation JUMPSUIT3_CLASSIC_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit3_classic.png");
    private static final ResourceLocation JUMPSUIT3_SLIM_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit3_slim.png");

    public JumpsuitLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }


    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight,
                       AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ResourceLocation texture = null;
        boolean slim = "slim".equals(player.getModelName());

        if (chest.is(ModItems.DCLASS_JUMPSUIT.get())) {
            texture = slim ? DCLASS_SLIM_TEXTURE : DCLASS_CLASSIC_TEXTURE;
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_1.get())) {
            texture = slim ? JUMPSUIT1_SLIM_TEXTURE : JUMPSUIT1_CLASSIC_TEXTURE;
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_2.get())) {
            texture = slim ? JUMPSUIT2_SLIM_TEXTURE : JUMPSUIT2_CLASSIC_TEXTURE;
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_3.get())) {
            texture = slim ? JUMPSUIT3_SLIM_TEXTURE : JUMPSUIT3_CLASSIC_TEXTURE;
        }

        if (texture == null) return;

        // Save original visibility
        PlayerModel<AbstractClientPlayer> parentModel = this.getParentModel();
        boolean jacketWasVisible = parentModel.jacket.visible;
        boolean leftSleeveWasVisible = parentModel.leftSleeve.visible;
        boolean rightSleeveWasVisible = parentModel.rightSleeve.visible;
        boolean leftPantsWasVisible = parentModel.leftPants.visible;
        boolean rightPantsWasVisible = parentModel.rightPants.visible;

        // Hide second layers on base model
        parentModel.jacket.visible = false;
        parentModel.leftSleeve.visible = false;
        parentModel.rightSleeve.visible = false;
        parentModel.leftPants.visible = false;
        parentModel.rightPants.visible = false;

        // Setup jumpsuit model
        PlayerModel<AbstractClientPlayer> jumpsuitModel = new PlayerModel<>(
                Minecraft.getInstance().getEntityModels().bakeLayer(
                        slim ? net.minecraft.client.model.geom.ModelLayers.PLAYER_SLIM : net.minecraft.client.model.geom.ModelLayers.PLAYER
                ),
                slim
        );

        parentModel.copyPropertiesTo(jumpsuitModel);
        jumpsuitModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        // Hide second layers on the jumpsuit itself (optional if your texture covers it)
        jumpsuitModel.jacket.visible = true;
        jumpsuitModel.leftSleeve.visible = true;
        jumpsuitModel.rightSleeve.visible = true;
        jumpsuitModel.leftPants.visible = true;
        jumpsuitModel.rightPants.visible = true;

        // Render jumpsuit
        VertexConsumer vertex = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
        jumpsuitModel.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);

        // Restore second-layer visibility
     /*   parentModel.jacket.visible = jacketWasVisible;
        parentModel.leftSleeve.visible = leftSleeveWasVisible;
        parentModel.rightSleeve.visible = rightSleeveWasVisible;
        parentModel.leftPants.visible = leftPantsWasVisible;
        parentModel.rightPants.visible = rightPantsWasVisible; */
    }

}
