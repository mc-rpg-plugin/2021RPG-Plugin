package com.tistory.workshop.Assasin;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;

public class assasin implements Listener {

    public static int stack = 0;
    public ItemStack killingstack = new ItemStack(Material.BONE_MEAL);
    public ItemMeta killingstackmeta = (ItemMeta)killingstack.getItemMeta();
    @EventHandler
    public void killedstack(EntityDeathEvent e){
        if(e.getEntityType() == EntityType.PLAYER)
            return;

        Player player = (Player)e.getEntity();
        if(player == ((Player) e.getEntity()).getKiller()){
            killingstack.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            killingstackmeta.setDisplayName(Color.red.toString() + "살인청부");
            killingstack.setItemMeta(killingstackmeta);
            player.getInventory().addItem(killingstack);
            for (ItemStack itemStack : player.getInventory().all(killingstack).values()) {
                stack++;
            }
        }

        if(player.isDead()){
            stack = 0;
        }

    }

}
