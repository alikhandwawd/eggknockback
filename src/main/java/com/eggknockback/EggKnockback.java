package com.eggknockback;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class EggKnockback implements ModInitializer {
	private static final double STRENGTH = 0.4;

	@Override
	public void onInitialize() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((target, source, amount) -> {
			Entity proj = source.getDirectEntity();
			if (proj == null) return true;

			EntityType<?> type = proj.getType();
			if (type != EntityType.SNOWBALL && type != EntityType.EGG) return true;

			if (target.isBlocking()) return true;

			target.knockback(STRENGTH, proj.getX() - target.getX(), proj.getZ() - target.getZ());
			target.hurtMarked = true;
			return true;
		});
	}
}
