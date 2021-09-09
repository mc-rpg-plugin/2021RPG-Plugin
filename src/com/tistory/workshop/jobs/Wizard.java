package com.tistory.workshop.jobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Wizard implements Listener {

    @EventHandler
    public void ability(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();

        if ((action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            if (JobVariable.getPlayerJob(player, "Wizard")) {
                if (JobVariable.isAvailable(player.getUniqueId(), "lighting")) {
                    player.getWorld().strikeLightning(player.getTargetBlock(null, 10).getLocation());
                    JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "lighting");
                }
                else {
                    player.sendMessage(ChatColor.BLUE + "[낙뢰]" + ChatColor.RESET + " 쿨타임 남은 시간: "
                            + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "lighting")) + "초");
                }
            }
        } else if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            if (JobVariable.getPlayerJob(player, "Wizard")) {
                if (JobVariable.isAvailable(player.getUniqueId(), "teleport")) {
                    float pitch = player.getLocation().getPitch();
                    float yaw = player.getLocation().getYaw();
                    Location targetLocation = player.getTargetBlock(null, 10).getLocation();
                    targetLocation.add(0, 1, 0);
                    targetLocation.setPitch(pitch);
                    targetLocation.setYaw(yaw);
                    player.teleport(targetLocation);
                    JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "teleport");
                }
                else {
                    player.sendMessage(ChatColor.AQUA + "[텔레포트]" + ChatColor.RESET + " 쿨타임 남은 시간: "
                            + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "teleport")) + "초");
                }
            }
        }

        if ((action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            if (JobVariable.getPlayerJob(player, "Wizard")) {

                if (JobVariable.isAvailable(player.getUniqueId(), "dark_Magic")) {
                    ((Damageable) player).damage(4);
                    double Block = 20D;

                    List<LivingEntity> entity = player.getLocation().getWorld().getLivingEntities();

                    for (LivingEntity near : entity) {
                        if (near == player)
                            continue;
                        if (near.getLocation().distance(player.getLocation()) <= Block) {
                            near.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
                        }
                    }
                    JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "dark_Magic");
                }
                else {
                    player.sendMessage(ChatColor.BLACK + "[금지된 흑마법]" + ChatColor.RESET + " 쿨타임 남은 시간: "
                            + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "dark_Magic")) + "초");
                }
            }
        }
        else if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            if (JobVariable.getPlayerJob(player, "Wizard")) {
                if (JobVariable.isAvailable(player.getUniqueId(), "meteor")) {
                    LargeFireball f = player.launchProjectile(LargeFireball.class);
                    Block location = player.getTargetBlock(null, 10);
                    JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "meteor");
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED + "[메태오]" + ChatColor.RESET + " 쿨타임 남은 시간: "
                            + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "meteor")) + "초");
                }
            }
        }
    }

    @EventHandler
    public void meteor(ProjectileHitEvent e) {

        if (e.getEntity() instanceof LargeFireball) {

            if (e.getEntity().getShooter() instanceof Player player) {

                if (JobVariable.getPlayerJob(player, "Wizard")) {
                    List<Location> locationList = new ArrayList<>();

                    if (e.getHitEntity() != null && e.getHitEntity() instanceof LivingEntity) {
                        ((Damageable)e.getHitEntity()).damage(10);
                    }

                    if (e.getHitBlock() != null) {
                        for (Block fireBallLocation : getBlocks(e.getHitBlock(), 3)) {
                            locationList.add(fireBallLocation.getLocation().add(0, 20, 0));
                        }
                    }
                    else {
                        for (Block fireBallLocation : getBlocks(e.getHitEntity().getLocation().getBlock(), 3)) {
                            locationList.add(fireBallLocation.getLocation().add(0, 20, 0));
                        }
                    }

                    for (Location fireballGoTo : locationList) {
                        Fireball fb = (Fireball) player.getWorld().spawnEntity(fireballGoTo, EntityType.FIREBALL);
                        fb.setDirection(new Vector(0, -3, 0));
                        fb.setYield(3);
                    }
                }

            }
        }
    }

    public ArrayList<Block> getBlocks(Block start, int radius) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        if (start == null) {
            blocks.add(null);
            return blocks;
        }

        for(double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++) {
            for (double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++) {
                Location loc = new Location(start.getWorld(), x, start.getLocation().getY(), z);
                blocks.add(loc.getBlock());
            }
        }
        return blocks;
    }

    public ArrayList<Location> getLocations(Location start, int radius) {
        ArrayList<Location> locs = new ArrayList<Location>();
        if (start == null) {
            locs.add(null);
            return locs;
        }

        for(double x = start.getX() - radius; x <= start.getX() + radius; x++){
            for (double z = start.getZ() - radius; z <= start.getZ() + radius; z++) {
                Location loc = new Location(start.getWorld(), x, start.getY(), z);
                locs.add(loc);
            }
        }
        return locs;
    }

}
