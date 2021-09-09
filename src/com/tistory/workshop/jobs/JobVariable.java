package com.tistory.workshop.jobs;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class JobVariable {

    private static JobVariable instance = new JobVariable();

    public static JobVariable Instance() {
        return instance;
    }

    // 키값: 플레이어 고유코드, (스킬명, 쿨타임)

    public static HashMap<UUID, String> playerJob = new HashMap<UUID, String>();                                // 플레이어 직업
    public static HashMap<UUID, HashMap<String, Long>> coolTimes = new HashMap<UUID, HashMap<String, Long>>();  // 쿨타임 저장
    public static HashMap<UUID, Boolean> useAbilityNow = new HashMap<UUID, Boolean>();                          // 현재 능력이 발동 중인지를 확인
    public static HashMap<String, Integer> abilityCoolTime = new HashMap<String, Integer>();                    // 해당 능력에 부여되는 쿨타임

    // 해당 능력에게 부여되는 쿨타임 (onEnable 에 초기에 넣어질 메소드)
    public static void setAbilityCoolTimes() {
        abilityCoolTime.clear();
        abilityCoolTime.put("quick_hook", 15);
        abilityCoolTime.put("ducking", 8);
        abilityCoolTime.put("body_Blow", 15);
        abilityCoolTime.put("grenade", 20);
        abilityCoolTime.put("black_Hole", 15);
        abilityCoolTime.put("wolf", 30);
        abilityCoolTime.put("rush", 15);
        abilityCoolTime.put("blood_Flew", 20);
        abilityCoolTime.put("lighting", 15);
        abilityCoolTime.put("teleport", 20);
        abilityCoolTime.put("dark_Magic", 25);
        abilityCoolTime.put("meteor", 40);
        abilityCoolTime.put("escape", 30);
    }

    // 플레이어에게 능력을 지정하는 메소드
    public static void setPlayerJob(Player player, String job) {
        UUID playerUUID = player.getUniqueId();
        playerJob.put(playerUUID, job);
    }

    public static boolean hasJob(Player player) {
        if (playerJob.containsKey(player.getUniqueId())) {
            return true;
        }
        return false;
    }

    public static String getPlayerJob(Player player) {
        if (!hasJob(player)) {
            player.sendMessage("아직 직업을 선택하지 않으셨습니다!");
            return null;
        }
        return playerJob.get(player.getUniqueId());
    }

    public static boolean getPlayerJob(Player player, String job) {
        if (!hasJob(player))
            return false;

        return playerJob.get(player.getUniqueId()).equals(job);
    }

    // 쿨타임 조절하는 메소드 영역

    public static void setCoolTime(UUID player, long time, String skillName) {
        if (time < 1) {
            coolTimes.remove(player);
        }
        else {
            coolTimes.get(player).put(skillName, time);
        }
    }

    public static int getJobCoolTime(String skillName) {
        return abilityCoolTime.get(skillName);
    }


    public static long getCoolTime(UUID player, String skillName) {
        return coolTimes.get(player).getOrDefault(skillName, (long) 0);
    }

    public static boolean isAvailable(UUID player, String skillName) {
        if (!coolTimes.containsKey(player) || coolTimes.get(player).get(skillName) == null) {
            try {
                coolTimes.put(player, new HashMap<String, Long>());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return true;
        }
        long timeLeft = getSkillCool(player, skillName);
        if (TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= getJobCoolTime(skillName)) {
            return true;
        }
        return false;
    }

    public static long getSkillCool(UUID player, String skillName) {
        return System.currentTimeMillis() - getCoolTime(player, skillName);
    }

    public static long getSkillCoolLeft(UUID player, String skillName) {
        return TimeUnit.MILLISECONDS.toSeconds(getSkillCool(player, skillName)) - getJobCoolTime(skillName);
    }



}
