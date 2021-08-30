package com.tistory.workshop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Berserker implements Listener {
    @EventHandler
    public void bsk(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getHealth() <= 10){
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,1));
        }else if(p.getHealth() <= 7){
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,2));
        }else if(p.getHealth() <= 5){
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,4));
        }
    }
}
