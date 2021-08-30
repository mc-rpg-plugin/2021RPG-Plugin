package com.tistory.workshop.jobs;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.UUID;

public class Musketeer implements Listener {

    public static HashMap<UUID, Integer> shotCount = new HashMap<>();

    @EventHandler
    public void setShotCount(EntityDamageByEntityEvent e) {
        Entity tool = e.getDamager();
        if (tool instanceof Arrow) {
            if (e.getEntity() instanceof Player) {
                if (((Arrow) tool).getShooter() instanceof Player) {
                    Player shooter = (Player) ((Arrow) tool).getShooter();
                    if (shooter != null) {
                        if (shotCount.containsKey(shooter.getUniqueId()) && shotCount.get(shooter.getUniqueId()) < 3) {
                            shotCount.put(shooter.getUniqueId(), shotCount.get(shooter.getUniqueId()) + 1);
                        } else {
                            shotCount.put(shooter.getUniqueId(), 0);
                            e.setDamage(e.getDamage() * 2);
                        }
                    }
                }
            }
        }
    }

}
