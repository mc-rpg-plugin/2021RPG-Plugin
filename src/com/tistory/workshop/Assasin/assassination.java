package com.tistory.workshop.Assasin;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;


public class assassination implements Listener {

        //주변 플레이어 위치 받아와서 그 플레이어에게 데미지를 주고 tp
    @EventHandler
    public void assassinate(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {

            Player attacker = (Player) e.getDamager();
            double radius = 10D;
            List<Entity> nearEntity = attacker.getLocation().getWorld().getEntities();

            for (Entity entity : nearEntity) {
                if (entity.getLocation().distance(attacker.getLocation()) <= radius) {
                    ((Damageable)entity).damage((2+assasin.stack) * 1.5);
                    attacker.teleport(entity.getLocation().add(0,0,-1));
                    attacker.getInventory().remove(assasin.killingstack);
                    assasin.stack = 0;
                }
            }


        }
    }
}