package com.tistory.workshop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;


public class TP implements Listener {
    private static HashMap<UUID, Location> tp = new HashMap<UUID,Location>();
    public void rightclick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        if(a == Action.RIGHT_CLICK_BLOCK && p.getInventory().getItemInMainHand().getType() == Material.STICK){
            tp.put(p.getUniqueId(),p.getTargetBlock(null,10).getLocation());
            p.teleport(tp.get(p.getUniqueId()));

        }
    }

}
