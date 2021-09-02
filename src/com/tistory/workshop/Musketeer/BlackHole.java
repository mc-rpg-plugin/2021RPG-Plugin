package com.tistory.workshop.Musketeer;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.List;



public class BlackHole implements Listener {


    @EventHandler
    public void BlackholeShot(ProjectileHitEvent e){
        if(e.getEntity().getType() != EntityType.ARROW)
            return;

        Arrow arrow = (Arrow) e.getEntity();
        Location arrowlocation = arrow.getLocation();


        double radius = 10D;

        List<Entity> nearEntity = arrow.getLocation().getWorld().getEntities();

        for (Entity entity : nearEntity) {
            if (entity.getLocation().distance(arrow.getLocation()) <= radius) {
                entity.teleport(arrowlocation);
            }
        }
    }
}
