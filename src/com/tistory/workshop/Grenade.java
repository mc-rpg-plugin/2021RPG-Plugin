package com.tistory.workshop;


import org.bukkit.entity.Egg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

import java.util.concurrent.TimeUnit;

public class Grenade implements Listener {

    @EventHandler
    public void gnd(PlayerEggThrowEvent e){

            if (e.getEgg().isDead()) {

                Egg egg = e.getEgg();
                egg.getWorld().createExplosion(egg.getLocation(),(float) 7.5,false,false);

            }
    }
}
