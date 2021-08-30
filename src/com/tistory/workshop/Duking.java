package com.tistory.workshop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.util.Vector;

public class Duking implements Listener {
    @EventHandler
    public void DK(PlayerEvent e){
        Player p = e.getPlayer();
        ClickType c = ClickType.SHIFT_RIGHT; //일단 직업 구분이 없으니 암살자와 안겹치게 Right Shift
        if(c.isKeyboardClick()){
            Vector vc = new Vector(p.getLocation().getDirection().getX(),0,p.getLocation().getDirection().getZ());
            vc.normalize();
            p.setVelocity(vc.multiply(1));
            p.attack(null);
        }
    }
}
