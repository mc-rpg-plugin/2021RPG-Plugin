package com.tistory.workshop.Musketeer;


import org.bukkit.entity.Egg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;


public class Grenade implements Listener {

    @EventHandler
    public void gnd(PlayerEggThrowEvent e){

        Egg egg = e.getEgg();
        egg.setVisualFire(true);

        egg.getWorld().createExplosion(egg.getLocation(),(float) 7.5,false,false);

    }
}
