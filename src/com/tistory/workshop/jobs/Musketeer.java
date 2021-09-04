package com.tistory.workshop.jobs;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Musketeer implements Listener {

    public static HashMap<UUID, Integer> shotCount = new HashMap<>();

    @EventHandler
    public void headShot(EntityDamageByEntityEvent e) {
        Entity tool = e.getDamager();
        if (tool instanceof Arrow) {
            if (e.getEntity() instanceof Player) {
                if (((Arrow) tool).getShooter() instanceof Player) {
                    Player shooter = (Player) ((Arrow) tool).getShooter();
                    if (shooter != null) {
                        if (shotCount.containsKey(shooter.getUniqueId()) && shotCount.get(shooter.getUniqueId()) < 3) {
                            shotCount.put(shooter.getUniqueId(), shotCount.get(shooter.getUniqueId()) + 1);
                            if (shotCount.containsKey(shooter.getUniqueId()) && shotCount.get(shooter.getUniqueId()) > 3)
                                e.setDamage(e.getDamage() * 2);
                        }
                        else {
                            shotCount.put(shooter.getUniqueId(), 0);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void gnd(PlayerEggThrowEvent e){

        Egg egg = e.getEgg();
        egg.setVisualFire(true);

        if(egg.isEmpty()){

            egg.getWorld().createExplosion(egg.getLocation(),(float) 7.5,false,false);
        }else{
            egg.getWorld().createExplosion(egg.getLocation(),(float) 9,false,false);
            egg.getWorld().createExplosion(egg.getLocation(),(float) 7.5,false,false);
            egg.getWorld().createExplosion(egg.getLocation(),(float) 6,false,false);
            egg.getWorld().createExplosion(egg.getLocation(),(float) 4.5,false,false);
            egg.getWorld().createExplosion(egg.getLocation(),(float) 3,false,false);

        }

    }

    @EventHandler
    public void BlackholeShot(ProjectileHitEvent e){
        if(e.getEntity().getType() != EntityType.ARROW)
            return;

        Arrow arrow = (Arrow) e.getEntity();
        Entity ent = (Entity) arrow.getShooter();
        if (ent instanceof Player) {
            Location arrowlocation = arrow.getLocation();

            Player shooter = (Player) ent;

            double radius = 10D;

            List<Entity> nearEntity = arrow.getLocation().getWorld().getEntities();

            for (Entity entity : nearEntity) {
                if (entity.getLocation().distance(arrow.getLocation()) <= radius) {
                    if (entity == shooter)
                        continue;
                    entity.teleport(arrowlocation);

                }
            }
        }
    }
}
