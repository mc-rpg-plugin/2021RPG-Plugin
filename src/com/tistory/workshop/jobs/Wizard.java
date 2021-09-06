package com.tistory.workshop.jobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
                player.getWorld().strikeLightning(player.getTargetBlock(null, 10).getLocation());
            }
        } else if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            if (JobVariable.getPlayerJob(player, "Wizard")) {
                float pitch = player.getLocation().getPitch();
                float yaw = player.getLocation().getYaw();
                Location targetLocation = player.getTargetBlock(null, 10).getLocation();
                targetLocation.add(0, 1, 0);
                targetLocation.setPitch(pitch);
                targetLocation.setYaw(yaw);
                player.teleport(targetLocation);
            }
        }

        if ((action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            if (JobVariable.getPlayerJob(player, "Wizard")) {
                for (Block block : getBlocks(e.getClickedBlock(), 4)) {
                    if (block == null)
                        continue;
                    player.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, block.getLocation(), 10);

                }
            }
        }
        else if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            if (JobVariable.getPlayerJob(player, "Wizard")) {
                LargeFireball f = player.launchProjectile(LargeFireball.class);
                Block location = player.getTargetBlock(null, 10);
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
