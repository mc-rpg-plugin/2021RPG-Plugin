package com.tistory.workshop.jobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Assassin implements Listener {

    private static HashMap<UUID, Location> saveLocation = new HashMap<UUID,Location>();
    private static HashMap<UUID,Location> previousLocation = new HashMap<UUID,Location>();

    public static int stack = 0;
    ItemStack killingStack = new ItemStack(Material.BONE_MEAL);
    ItemMeta killingStackMeta = (ItemMeta)killingStack.getItemMeta();

    private static HashMap<UUID, UUID> assassinationQuest = new HashMap<UUID, UUID>();
    Random rand = new Random();

    @EventHandler
    public void escape(PlayerToggleSneakEvent e) {

        Player player = e.getPlayer();
        if (!JobVariable.getPlayerJob(player, "Assassin")) {
            return;
        }

        if (JobVariable.isAvailable(player.getUniqueId(), "escape")) {

            player.sendMessage("실행 안 한 것처럼 보이는 거다");

            if (saveLocation.isEmpty()) {
                saveLocation.put(player.getUniqueId(), player.getLocation());
                player.sendMessage("위치저장!");
            }
            else {
                previousLocation.put(player.getUniqueId(), player.getLocation());
                player.teleport(saveLocation.get(player.getUniqueId()));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 9999));
                player.getWorld().createExplosion(previousLocation.get(player.getUniqueId()), 3, false, false);
                player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                player.sendMessage("위치이동!");
                saveLocation.remove(player.getUniqueId());
                previousLocation.remove(player.getUniqueId());
                if (assassinationQuest.get(player.getUniqueId()) != null) {
                    assassinationQuest.replace(player.getUniqueId(), null);
                }
            }
            JobVariable.setCoolTime(player.getUniqueId(), System.currentTimeMillis(), "escape");
        }
        else {
            player.sendMessage(ChatColor.DARK_RED + "[도주]" + ChatColor.WHITE + " 쿨타임 남은 시간: "
                    + -1 * (JobVariable.getSkillCoolLeft(player.getUniqueId(), "escape")) + "초");
            player.sendMessage("?: " + JobVariable.isAvailable(player.getUniqueId(), "escape"));
            player.sendMessage("도주 기본 쿨타임: " + JobVariable.getJobCoolTime("escape"));
        }
    }

    @EventHandler
    public void assassinate(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player) {
            Player assassin = (Player) e.getDamager();
            LivingEntity victim = (LivingEntity) e.getEntity();
            if (!JobVariable.getPlayerJob(assassin, "Assassin")) {
                return;
            }
            List<PotionEffectType> potionlist = new ArrayList<>();
            potionlist.add(PotionEffectType.SLOW);
            potionlist.add(PotionEffectType.POISON);
            potionlist.add(PotionEffectType.BLINDNESS);
            potionlist.add(PotionEffectType.WEAKNESS);
            int r = rand.nextInt(4);
            if (assassinationQuest.get(assassin.getUniqueId()) == null) {
                assassinationQuest.put(assassin.getUniqueId(), victim.getUniqueId());
                assassin.sendMessage("의뢰대상: " + victim.getType());
            } else {
                if (assassinationQuest.get(assassin.getUniqueId()).equals(victim.getUniqueId())) {
                    victim.addPotionEffect(new PotionEffect(potionlist.get(r), 100, 4));
                }
            }
        }

        double rand = Math.random(); // 0.0 ~ 1.0
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            if (!JobVariable.getPlayerJob(player, "Assassin")) {
                return;
            }
            Entity victim = e.getEntity();
            ((Damageable) victim).damage((2 + stack) * 1.5);
            player.sendMessage("현재 피해량: " + (2 + stack) * 1.5);
            player.sendMessage("현재 스텍: " + stack);
            if((rand * 100) <= 2) {
                ((Damageable) victim).damage(999999999);
                player.sendMessage("즉사");
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

    @EventHandler
    public void killingStackUp(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null)
            return;
        LivingEntity entity = e.getEntity();
        Player player = (Player) e.getEntity().getKiller();
        if (!JobVariable.getPlayerJob(player, "Assassin")) {
            return;
        }
        if (entity.getType() == EntityType.PLAYER) {
            stack = 0;
            /*if (assassinationQuest.get(player.getUniqueId()) != null) {
                assassinationQuest.replace(player.getUniqueId(), entity.getUniqueId(), null);
            }*/
        }
        if (killingStackMeta != null && player != null) {
            killingStackMeta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
            killingStackMeta.setDisplayName(ChatColor.RED + "살생의 경험");
            killingStackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            killingStack.setItemMeta(killingStackMeta);
            player.getInventory().addItem(killingStack);
            ItemStack[] inv = player.getInventory().getContents();
            int temp = 0;
            for (ItemStack itemStack : inv) {
                if (itemStack != null) {
                    if (itemStack.getType() == Material.BONE_MEAL && itemStack.containsEnchantment(Enchantment.BINDING_CURSE)) {
                        temp = temp + itemStack.getAmount();
                    }
                }
            }
            stack = temp;
            if (assassinationQuest.get(player.getUniqueId()) != null) {
                assassinationQuest.replace(player.getUniqueId(), null);
                player.sendMessage("초기화 대상: " + assassinationQuest.get(player.getUniqueId()));
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
