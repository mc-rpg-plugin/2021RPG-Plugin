package com.tistory.workshop;



import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;


public class escape implements Listener {

    private Location pleyerpos = null;


    @EventHandler
    public void es(PlayerToggleSneakEvent e){

        Player p = e.getPlayer();

        boolean ispressed = false;
        if(e.getPlayer().isSneaking() && ispressed == false){
            pleyerpos.add(p.getLocation());
            ispressed = true;
            p.sendMessage("위치저장!");
        }else if (e.getPlayer().isSneaking() && ispressed == true){
             p.teleport(pleyerpos);
             p.getWorld().createExplosion(pleyerpos,3,false,false);
             pleyerpos = null;
             p.sendMessage("위치이동!");
             ispressed = false;
        }
    }
}
