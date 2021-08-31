package com.tistory.workshop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class Duking implements Listener {
    @EventHandler
    public void DK(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.isSneaking()){
            Vector vc = new Vector(p.getLocation().getDirection().getX(),0,p.getLocation().getDirection().getZ());
            vc.normalize();
            p.setVelocity(vc.multiply(1));
            p.attack(null);
        }
    }
}
