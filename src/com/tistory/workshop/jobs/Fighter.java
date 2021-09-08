package com.tistory.workshop.jobs;

import com.mojang.math.Vector3fa;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class Fighter implements Listener {

    public static HashMap<UUID, Boolean> hasSneak = new HashMap<>();

    @EventHandler
    public void ducking(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        if (!JobVariable.getPlayerJob(player, "Fighter")) {
            return;
        }
        if (e.isSneaking()) {
            hasSneak.put(player.getUniqueId(), true);
            Vector vector = new Vector(player.getLocation().getDirection().getX(), 0, player.getLocation().getDirection().getZ());
            vector.normalize();
            player.setVelocity(vector.multiply(1));

        }
        else {
            hasSneak.replace(player.getUniqueId(), true, false);
        }
    }

    // if player has sneak, player can't attack (physical)
    @EventHandler
    public void isDucking(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            if (!JobVariable.getPlayerJob(player, "Fighter")) {
                return;
            }
            if (hasSneak.get(player.getUniqueId())) {
                e.setCancelled(true);
            }
            else {
                ((Damageable)e.getEntity()).damage(8);
                ((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 10));
                ((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 10));
            }
        }
    }

}
