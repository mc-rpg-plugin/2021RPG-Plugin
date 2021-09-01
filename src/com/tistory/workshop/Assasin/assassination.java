package com.tistory.workshop.Assasin;

import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;



public class assassination implements Listener {

        //주변 플레이어 위치 받아와서 그 플레이어에게 데미지를 주고 tp
        @EventHandler
        public void assassinate(EntityDamageByEntityEvent e) {
            if (e.getDamager() instanceof Player) {
                Player player = (Player) e.getDamager();
                Entity victim = e.getEntity();
                ((Damageable) victim).damage((2+assasin.stack) * 1.5);
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