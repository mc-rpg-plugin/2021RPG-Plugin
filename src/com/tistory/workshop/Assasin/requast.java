package com.tistory.workshop.Assasin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class requast implements Listener {

    //자신이 처음으로 피격한 엔티티에게 표식을 부여합니다.
    //대상 엔티티를 표식을 부여한 암살자에게는 피해를 받을 때 마다 각종 디버프(구속, 실명, 독, 나약함 중 한가지 [각각 5초씩])를 받습니다.
    private HashSet<UUID> UUID = new HashSet<UUID>();
    private boolean istargeting = false;
    Random rand = new Random();
    @EventHandler
    public void firsttarget(EntityDamageByEntityEvent e){
        List<PotionEffectType> potionlist = new ArrayList<>();
        potionlist.add(PotionEffectType.SLOW);
        potionlist.add(PotionEffectType.POISON);
        potionlist.add(PotionEffectType.BLINDNESS);
        potionlist.add(PotionEffectType.WEAKNESS);
        Iterator it= UUID.iterator();
        Player damager = (Player) e.getDamager(); //테스트 할떈 알아서 바꾸십쇼
        if(istargeting == false){
            istargeting = true;
            UUID.add(damager.getUniqueId());
        }else if(istargeting == true && it.next() == damager.getUniqueId()){
            int r = rand.nextInt(4);
            damager.addPotionEffect(new PotionEffect(potionlist.get(r), 100, 4));
            istargeting = false;
            UUID.removeAll(UUID);
        }else if(istargeting == true && it.next() != damager.getUniqueId()){
            UUID.removeAll(UUID);
            istargeting = false;
        }

    }
}
