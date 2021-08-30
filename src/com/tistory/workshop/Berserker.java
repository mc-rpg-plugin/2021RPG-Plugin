package com.tistory.workshop;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Berserker implements Listener {
    @EventHandler
    public void bsk(EntityDamageEvent e){
        if(e.getEntityType() != EntityType.PLAYER)
            return;

        Player p = (Player) e.getEntity();
        if(p.getHealth() - e.getDamage() <= 10){
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,1000000000,1));
        }else if(p.getHealth() - e.getDamage() <= 7 && p.getHealth() > 5 ){
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,1000000000,2));
        }else if(p.getHealth() - e.getDamage()<= 5){
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,1000000000,4));
        }
    }
}
