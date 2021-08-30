package com.tistory.workshop.jobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Wizard implements Listener {

    @EventHandler
    public void ability(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();

        if ((action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            player.getWorld().strikeLightning(player.getTargetBlock(null, 10).getLocation());
        } else if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType() == Material.STICK) {
            float pitch = player.getLocation().getPitch();
            float yaw = player.getLocation().getYaw();
            Location targetLocation = player.getTargetBlock(null, 10).getLocation();
            targetLocation.add(0, 1, 0);
            targetLocation.setPitch(pitch);
            targetLocation.setYaw(yaw);
            player.teleport(targetLocation);
        }
    }
}
