package com.tistory.workshop.Assasin;



import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashMap;
import java.util.UUID;


public class escape implements Listener {

    private static HashMap<UUID,Location> lo = new HashMap<UUID,Location>();
    private static HashMap<UUID,Location> lo2 = new HashMap<UUID,Location>();

    @EventHandler
    public void es(PlayerToggleSneakEvent e){

        Player p = e.getPlayer();

        if(p.isSneaking() && lo.isEmpty()) {
            lo.put(p.getUniqueId(), p.getLocation());
            p.sendMessage("위치저장!");
        }else if (p.isSneaking()){
            lo2.put(p.getUniqueId(),p.getLocation());
             p.teleport(lo.get(p.getUniqueId()));

             p.getWorld().createExplosion(lo2.get(p.getUniqueId()),3,false,false);
             p.sendMessage("위치이동!");
             lo.remove(p.getUniqueId());
             lo2.remove(p.getUniqueId());
        }
    }
}
