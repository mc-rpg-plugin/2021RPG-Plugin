package com.tistory.workshop.jobs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Berserker implements Listener {

    @EventHandler
    public void berserker(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();

        if (p.getHealth() - e.getDamage() <= 5) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,4));
        }
        else if (p.getHealth() - e.getDamage() <= 7) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,2));
        }
        else if (p.getHealth() - e.getDamage() <= 10) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,2));
        }
    }
}
