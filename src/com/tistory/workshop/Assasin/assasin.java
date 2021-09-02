package com.tistory.workshop.Assasin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class assasin implements Listener {

    public static int stack = 0;

        ItemStack killingstack = new ItemStack(Material.BONE_MEAL);
        ItemMeta killingstackmeta = (ItemMeta)killingstack.getItemMeta();

        @EventHandler
        public void killedstack(EntityDeathEvent e) {

            LivingEntity entity = e.getEntity();
            Player player = (Player) e.getEntity().getKiller();
            if (entity.getType() == EntityType.PLAYER) {
                stack = 0;
            }
            if (killingstackmeta != null && player != null) {
                killingstackmeta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
                killingstackmeta.setDisplayName(ChatColor.RED + "살인청부");
                killingstackmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                killingstack.setItemMeta(killingstackmeta);
                player.getInventory().addItem(killingstack);
                for (ItemStack itemStack : player.getInventory().all(killingstack).values()) {
                    stack++;
                }
            }
        }

        @EventHandler
        public void cancelDropItem(PlayerDropItemEvent e) {
            if (e.getItemDrop().getItemStack().containsEnchantment(Enchantment.BINDING_CURSE) &&
                    e.getItemDrop().getItemStack().getType() == Material.BONE_MEAL) {
                e.setCancelled(true);
            }
        }

        @EventHandler
        public void cancelDropItem(PlayerInteractEvent e) {
            Player player = e.getPlayer();

            if (player.getInventory().getItemInMainHand().getType() == Material.BONE_MEAL &&
                    player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.BINDING_CURSE)) {
                e.setCancelled(true);
            }
        }
    }

