package com.tistory.workshop.jobs;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Berserker implements Listener {

    @EventHandler
    public void berserker(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player player = (Player) e.getEntity();

        if (!JobVariable.getPlayerJob(player, "Berserker")) {
            return;
        }

        if (player.getHealth() - e.getDamage() <= 5) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,3));
        }
        else if (player.getHealth() - e.getDamage() <= 7) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,1));
        }
        else if (player.getHealth() - e.getDamage() <= 10) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,0));
        }
    }

    @EventHandler
    public void rush(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();

        if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)) {
            if (!JobVariable.getPlayerJob(player, "Berserker")) {
                return;
            }
            if (JobVariable.isAvailable(player.getUniqueId(), "rush")) {
                Vector vector = new Vector(player.getLocation().getDirection().getX(), 0, player.getLocation().getDirection().getZ());
                player.setVelocity(vector);
                for (Entity entity : player.getNearbyEntities(2, 2, 2)) {
                    if (!(entity instanceof LivingEntity))
                        continue;
                    for (Block block : player.getLineOfSight(null, 3)) {
                        if (entity.getLocation().distance(block.getLocation()) > 3)
                            continue;
                        ((LivingEntity) entity).damage(8);
                        player.setVelocity(new Vector(0, 0, 0));
                    }
                }
                JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "rush");
            }
            else {
                player.sendMessage(ChatColor.BLUE + "[무모한 공격]" + ChatColor.WHITE + " 쿨타임 남은 시간: "
                        + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "rush")) + "초");
                
            }
        }

        if ((action == Action.LEFT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BONE && e.getClickedBlock() != null) {
            if (!JobVariable.getPlayerJob(player, "Berserker")) {
                return;
            }
            if (JobVariable.isAvailable(player.getUniqueId(), "blood_Flew")) {
                AreaEffectCloud areaEffectCloud = (AreaEffectCloud) player.getWorld().spawnEntity(e.getClickedBlock().getLocation(), EntityType.AREA_EFFECT_CLOUD);
                areaEffectCloud.setColor(Color.RED);
                areaEffectCloud.setParticle(Particle.DAMAGE_INDICATOR);
                areaEffectCloud.setRadius(6.0f);
                areaEffectCloud.setRadiusOnUse(6.0f);
                areaEffectCloud.setDuration(10);
                areaEffectCloud.setDurationOnUse(10);
                areaEffectCloud.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 60, 0, false, false, false), false);
                areaEffectCloud.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 1, false, false, false), true);
                JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "blood_Flew");
            }
            else {
                player.sendMessage(ChatColor.DARK_RED + "[혈류 폭발]" + ChatColor.WHITE + " 쿨타임 남은 시간: "
                        + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "blood_Flew")) + "초");
            }
        }
    }

}
