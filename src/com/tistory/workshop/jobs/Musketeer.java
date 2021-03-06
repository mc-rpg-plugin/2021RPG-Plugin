package com.tistory.workshop.jobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Musketeer implements Listener {

    public static HashMap<UUID, Integer> shotCount = new HashMap<>();

    @EventHandler
    public void headShot(EntityDamageByEntityEvent e) {
        Entity tool = e.getDamager();
        if (tool instanceof Arrow) {
            if (e.getEntity() instanceof Player) {
                if (((Arrow) tool).getShooter() instanceof Player) {
                    Player shooter = (Player) ((Arrow) tool).getShooter();
                    if (!JobVariable.getPlayerJob(shooter, "Musketeer")) {
                        return;
                    }
                    if (shooter != null) {
                        if (shotCount.containsKey(shooter.getUniqueId()) && shotCount.get(shooter.getUniqueId()) < 3) {
                            shotCount.put(shooter.getUniqueId(), shotCount.get(shooter.getUniqueId()) + 1);
                            if (shotCount.containsKey(shooter.getUniqueId()) && shotCount.get(shooter.getUniqueId()) > 3)
                                e.setDamage(e.getDamage() * 2);
                        }
                        else {
                            shotCount.put(shooter.getUniqueId(), 0);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void grenade(PlayerEggThrowEvent e) {

        Player player = e.getPlayer();

        if (!JobVariable.getPlayerJob(player, "Musketeer")) {
            return;
        }

        if (JobVariable.isAvailable(player.getUniqueId(), "grenade")) {

            Egg egg = e.getEgg();
            egg.setVisualFire(true);

            if (egg.isEmpty()) {

                egg.getWorld().createExplosion(egg.getLocation(), (float) 7.5, false, false);
            } else {
                egg.getWorld().createExplosion(egg.getLocation(), (float) 9, false, false);
                egg.getWorld().createExplosion(egg.getLocation(), (float) 7.5, false, false);
                egg.getWorld().createExplosion(egg.getLocation(), (float) 6, false, false);
                egg.getWorld().createExplosion(egg.getLocation(), (float) 4.5, false, false);
                egg.getWorld().createExplosion(egg.getLocation(), (float) 3, false, false);

            }
            JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "grenade");
        }
        else {
            player.sendMessage(ChatColor.BLACK + "[?????????]" + ChatColor.WHITE + " ????????? ?????? ??????: "
                    + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "grenade")) + "???");
        }

    }

    @EventHandler
    public void blackHoleShot(ProjectileHitEvent e){
        if(e.getEntity().getType() != EntityType.ARROW)
            return;

        Arrow arrow = (Arrow) e.getEntity();
        Entity ent = (Entity) arrow.getShooter();
        if (ent instanceof Player) {
            Location arrowlocation = arrow.getLocation();

            Player shooter = (Player) ent;

            if (!JobVariable.getPlayerJob(shooter, "Musketeer")) {
                return;
            }

            if (JobVariable.isAvailable(shooter.getUniqueId(), "black_Hole")) {
                double radius = 10D;

                List<Entity> nearEntity = arrow.getLocation().getWorld().getEntities();

                for (Entity entity : nearEntity) {
                    if (entity.getLocation().distance(arrow.getLocation()) <= radius) {
                        if (entity == shooter)
                            continue;
                        entity.teleport(arrowlocation);
                    }
                }
                JobVariable.setCoolTime(shooter.getUniqueId(), System.currentTimeMillis(), "black_Hole");
            }
            else {
                shooter.sendMessage(ChatColor.AQUA + "[????????????]" + ChatColor.WHITE + " ????????? ?????? ??????: "
                        + -1 * (JobVariable.getSkillCoolLeft(shooter.getUniqueId(), "black_Hole")) + "???");
            }
        }
    }

    @EventHandler
    public void hunterWolf(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();

        if (!JobVariable.getPlayerJob(player, "Musketeer")) {
            return;
        }

        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.IRON_INGOT) {
            if (JobVariable.isAvailable(player.getUniqueId(), "wolf")) {
                summonWolf(player, ChatColor.RED + "?????????", 30, 4);
                summonWolf(player, ChatColor.RED + "?????????", 30, 4);
                JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "wolf");
            }
            else {
                player.sendMessage(ChatColor.GRAY + "[?????????]" + ChatColor.WHITE + " ????????? ?????? ??????: "
                        + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "wolf")) + "???");
            }
        }
    }

    public void summonWolf(Player player, String name, float hp, float damage) {
        Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        wolf.setCustomName(name);
        wolf.setCustomNameVisible(true);
        wolf.setOwner((AnimalTamer) player);
        AttributeInstance health = wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        health.setBaseValue(hp);
        AttributeInstance attackDamage = wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        attackDamage.setBaseValue(damage);
    }

}
