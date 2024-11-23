package me.jamino.wynnimations.animations;

import dev.tr7zw.notenoughanimations.access.PlayerData;
import dev.tr7zw.notenoughanimations.api.BasicAnimation;
import dev.tr7zw.notenoughanimations.util.AnimationUtil;
import dev.tr7zw.notenoughanimations.versionless.animations.BodyPart;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;

public class ContentBook extends BasicAnimation {

    private final BodyPart[] bothHands = new BodyPart[] { BodyPart.LEFT_ARM, BodyPart.RIGHT_ARM };
    private final BodyPart[] left = new BodyPart[] { BodyPart.LEFT_ARM };
    private final BodyPart[] right = new BodyPart[] { BodyPart.RIGHT_ARM };
    private BodyPart[] target = bothHands;

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isValid(AbstractClientPlayer entity, PlayerData data) {
        String mainHandName = entity.getMainHandItem().getDisplayName().getString();
        String offHandName = entity.getOffhandItem().getDisplayName().getString();

        // Both hands if main hand has book and offhand is empty
        if ("Content Book".equals(mainHandName) && entity.getOffhandItem().isEmpty()) {
            target = bothHands;
            return true;
        }
        // Only main hand animation if holding book but something else in offhand
        if ("Content Book".equals(mainHandName)) {
            target = entity.getMainArm().toString().equals("LEFT") ? left : right;
            return true;
        }
        // Only offhand animation if book is in offhand
        if ("Content Book".equals(offHandName)) {
            target = entity.getMainArm().toString().equals("RIGHT") ? left : right;
            return true;
        }
        return false;
    }

    @Override
    public BodyPart[] getBodyParts(AbstractClientPlayer entity, PlayerData data) {
        return target;
    }

    @Override
    public int getPriority(AbstractClientPlayer entity, PlayerData data) {
        return 300;
    }

    @Override
    public void apply(AbstractClientPlayer entity, PlayerData data, PlayerModel model, BodyPart part, float delta, float tickCounter) {
        if (target == bothHands) {
            // Both hands holding animation
            AnimationUtil.applyTransforms(model, part, -0.5f, part == BodyPart.LEFT_ARM ? -0.4f : 0f, 0.3f);
        } else {
            // Single hand holding animation
            AnimationUtil.applyTransforms(model, part, -0.5f, 0f, 0.3f);
        }
    }
}