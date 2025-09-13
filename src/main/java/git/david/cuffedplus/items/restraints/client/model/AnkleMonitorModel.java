package git.david.cuffedplus.items.restraints.client.model;
// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class AnkleMonitorModel<T extends LivingEntity> extends HumanoidModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(new ResourceLocation("cuffedplus", "ankle_monitor"), "main");
    private final ModelPart _root;

    public AnkleMonitorModel(ModelPart root) {
        super(root);
        _root = root;
    }

    public static LayerDefinition createArmorLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();


        // Create definitions for the player model's body parts so we can create child cubes for them.
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition right_leg = partdefinition.getChild("right_leg");

        PartDefinition right_cuff = right_leg.addOrReplaceChild("right_cuff", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));

        PartDefinition bb_main = right_cuff.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(64, 1).addBox(-8.0F, -5.0F, 0.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(-1.7F))
                .texOffs(97, 3).addBox(-7.5F, -5.5F, -0.5F, 7.0F, 6.0F, 5.0F, new CubeDeformation(-2.0F))
                .texOffs(64, 15).addBox(-7.0F, -5.5F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-2.5F))
                .texOffs(64, 15).addBox(-7.0F, -5.5F, -1.2F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-2.5F))
                .texOffs(96, 34).addBox(-8.0F, -5.0F, -0.3F, 8.0F, 5.0F, 4.0F, new CubeDeformation(-1.85F))
                .texOffs(64, 1).addBox(-8.0F, -5.0F, 0.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(-1.7F)), PartPose.offset(4.0F, 2.0F, -4.0F));

        PartDefinition cube_r1 = right_cuff.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(66, 98).addBox(-8.025F, -7.5F, -9.425F, 16.0F, 15.0F, 15.0F, new CubeDeformation(-7.0F))
                .texOffs(96, 54).addBox(-4.025F, -2.5F, -4.25F, 8.0F, 5.0F, 4.0F, new CubeDeformation(-1.85F)), PartPose.offsetAndRotation(0F, -0.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r2 = right_cuff.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(96, 44).addBox(-4.0F, -2.5F, -4.275F, 8.0F, 5.0F, 4.0F, new CubeDeformation(-1.85F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r3 = right_cuff.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(96, 64).addBox(-3.975F, -2.5F, -4.25F, 8.0F, 5.0F, 4.0F, new CubeDeformation(-1.85F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack stack, @Nonnull VertexConsumer buffer, int packedLight, int blockLight, float partialTick, float r, float g, float b) {
        _root.render(stack, buffer, packedLight, blockLight);
        super.renderToBuffer(stack, buffer, packedLight, blockLight, partialTick, r, g, b);
    }
}