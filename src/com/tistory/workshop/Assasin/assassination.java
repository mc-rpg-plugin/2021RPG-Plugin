package com.tistory.workshop.Assasin;

import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;



public class assassination implements Listener {

        double rand = Math.random();
        int InstanceKill = (int)(rand * 100) + 1;
        @EventHandler
        public void assassinate(EntityDamageByEntityEvent e) {
            if (e.getDamager() instanceof Player) {
                Player player = (Player) e.getDamager();
                Entity victim = e.getEntity();
                ((Damageable) victim).damage((2+assasin.stack) * 1.5);
                if(InstanceKill >= 1 || InstanceKill <= 2){
                    ((Damageable) victim).damage(999999999);
                }
                Location victimLocation = victim.getLocation();
                double nx;
                double nz;
                float angle = victimLocation.getYaw() + 90;
                if (angle < 0) angle += 360;
                nx = Math.cos(Math.toRadians(angle));
                nz = Math.sin(Math.toRadians(angle));
                Location location = new Location(victimLocation.getWorld(), victimLocation.getX() - nx,
                        victimLocation.getY(), victimLocation.getZ() - nz, victimLocation.getYaw(), victimLocation.getPitch());
                player.teleport(location);
            }
        }
}