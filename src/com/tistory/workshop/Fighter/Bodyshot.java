package com.tistory.workshop.Fighter;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.net.http.WebSocket;

public class Bodyshot implements WebSocket.Listener {

    @EventHandler
    public void Bodyblow(EntityDamageByEntityEvent e){

        Player damager = (Player) e.getDamager();
        ((Damageable) damager).damage(8);
        damager.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,600,10));
        damager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,600,10));

    }
}
