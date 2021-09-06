package com.tistory.workshop.Magition;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

public class Meteor implements WebSocket.Listener {

    @EventHandler
    public void Fire(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        double rand = Math.random();
        LargeFireball fireball = player.launchProjectile(LargeFireball.class);
        Location location = player.getTargetBlock(null, 10).getLocation();
        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            List<Location> locs = new ArrayList<Location>();

            for (int i = 0; i <= 4; i++) {
                Location loc = location.getBlock().getLocation().add(0, 20, 0);

                for (double x = -2; x <= 5; x += 0.5) {
                    for (double z = -2; z <= 5; z += 0.5) {
                        locs.add(loc.clone().add(x, 70, z));
                    }

                }
            }
            for (Location fireSpot : locs) {
                if(rand >= 30)
                    return;
                fireSpot.getWorld().spawnEntity(fireSpot, fireball.getType());
                fireball.setVelocity(new Vector(0,-3,0));


            }

        }
    }


    public void Boom(ProjectileHitEvent e){
        EntityType fb = e.getEntityType();
        if (fb != null && fb.equals(EntityType.FIREBALL)) {
            Fireball f = (Fireball) e.getEntity();
            Location loc = f.getLocation();
            f.getWorld().createExplosion(loc, 3, true, false);
        }
    }

    public void disable(EntityExplodeEvent e){
        e.setCancelled(true);
    }
}
