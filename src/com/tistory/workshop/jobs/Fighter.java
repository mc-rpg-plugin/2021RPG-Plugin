package com.tistory.workshop.jobs;

import com.mojang.math.Vector3fa;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

public class Fighter implements Listener {

    @EventHandler
    public void theKing(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        Vector vector = new Vector(player.getLocation().getDirection().getX(), 0, player.getLocation().getDirection().getZ());
        vector.normalize();
        player.setVelocity(vector.multiply(1));
    }
}
