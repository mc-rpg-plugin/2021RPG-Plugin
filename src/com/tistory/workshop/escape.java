package com.tistory.workshop;



import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerEvent;

public class escape implements Listener {

    private Location pleyerpos = null;


    @EventHandler
    public void es(PlayerEvent e){

        Player p = e.getPlayer();
        ClickType c = ClickType.SHIFT_LEFT;
        boolean ispressed = false;
        if(c.isKeyboardClick() && ispressed == false){
            pleyerpos.add(p.getLocation());
            ispressed = true;
            p.sendMessage("위치저장!");
        }else if (c.isKeyboardClick() && ispressed == true){
             p.teleport(pleyerpos);
             pleyerpos = null;
             p.sendMessage("위치이동!");
             ispressed = false;
        }
    }
}
