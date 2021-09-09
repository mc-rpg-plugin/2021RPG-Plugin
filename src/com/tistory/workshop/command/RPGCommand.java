package com.tistory.workshop.command;

import com.tistory.workshop.jobs.Fighter;
import com.tistory.workshop.jobs.JobVariable;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RPGCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) return false;

            Player player = (Player) sender;

            if (strings.length == 0) {
                // TODO help 명령어 사용하게 추후 개발
            }
            else {
                if (strings[0].equals("setCharacter")) {
                    if (strings.length >= 2) {
                        if (strings[1].equals("Fighter")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("파이터 클래스로 설정하였습니다.");
                                JobVariable.setPlayerJob(player, "Fighter");
                                Fighter.hasSneak.put(player.getUniqueId(), false);
                            }
                        }
                        else if (strings[1].equals("Musketeer")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("머스킷티어 클래스로 설정하였습니다.");
                                JobVariable.setPlayerJob(player, "Musketeer");
                            }
                        }
                        else if (strings[1].equals("Berserker")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("버서커 클래스로 설정하였습니다.");
                                JobVariable.setPlayerJob(player, "Berserker");
                            }
                        }
                        else if (strings[1].equals("Wizard")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("마법사 클래스로 설정하였습니다.");
                                JobVariable.setPlayerJob(player, "Wizard");
                            }
                        }
                        else if (strings[1].equals("Assassin")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("암살자 클래스로 설장하였습니다.");
                                JobVariable.setPlayerJob(player, "Assassin");
                            }
                        }
                    }
                    else {
                        sender.sendMessage("틀린 명령어");   // TODO 추후 빨간색 텍스트로 에러 문자열 전송
                    }
                }
                else if (strings[0].equals("showDescription")) {
                     if (strings.length >= 2) {
                         switch (strings[1]) {
                             case "quick_Hook" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[퀵 훅]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 15초");
                                 sender.sendMessage("플레이어를 가격시, 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage("해당 플레이어에게 2배 가량의 피해를 주며");
                                 sender.sendMessage("해당 플레이어가 능력을 발동 중인 상태라면");
                                 sender.sendMessage("3초간 스턴을 겁니다.");
                             }
                             case "ducking" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[더킹]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 8초");
                                 sender.sendMessage("웅크리기를 통하여 작동하는 패시브형 능력입니다.");
                                 sender.sendMessage("바라보는 방향으로 빠르게 이동합니다.");
                                 sender.sendMessage("웅크리기하는 동안은 방어력이 2상승합니다.");
                                 sender.sendMessage("웅크리기하는 동안은 공격이 불가능합니다. (단, 바디 블로우 제외)");
                             }
                             case "body_Blow" ->  {
                                 sender.sendMessage(ChatColor.DARK_RED + "[바디 블로우]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 15초");
                                 sender.sendMessage("웅크리기를 하는 상태일 때만 사용가능한 액티브형 능력입니다.");
                                 sender.sendMessage("생명체를 피격 시 자신이 바라보는 방향으로 멀리 띄웁니다.");
                             }
                             case "jab" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[잽]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("기존 피해량의 2배가 피해량으로 고정됩니다.");
                             }
                             case "grenade" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[수류탄]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 20초");
                                 sender.sendMessage(ChatColor.BOLD + "수류탄" + ChatColor.RESET + "을 들고 우 클릭하여 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage("20초마다" + ChatColor.BOLD + "  수류탄" + ChatColor.RESET + "을 지급합니다.");
                                 sender.sendMessage(ChatColor.BOLD + "수류탄을 든 채로 우 클릭하여 투척을 하여 해당 수류탄이 땅에 닿으면 즉시 폭파합니다.");
                             }
                             case "head_Shot" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[헤드샷]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("플레이어 공격 4회째마다 작동하는 패시브형 능력입니다.");
                                 sender.sendMessage("플레이어를 상대로 공격 4회째 공격 때 마다 기존 피해량의 2배를 입힙니다.");
                             }
                             case "black_Hole" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[중력자탄]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 15초");
                                 sender.sendMessage("화살을 블럭에 맞추면 주위에 있는 모든 엔티티(본인 제외)를 해당 블럭위치로 끌어당깁니다.");
                             }
                             case "wolf" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[사냥개]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 30초");
                                 sender.sendMessage("자신의 사냥개 (체력: 30, 공격력: 4) 2마리를 생성합니다.");
                                 sender.sendMessage("해당 사냥개들은 자동으로 죽지않습니다.");
                             }
                             case "rush" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[무모한 공격]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 15초");
                                 sender.sendMessage("우 클릭시, 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage("바라보는 방향으로 빠르게 이동하여 해당 경로 근처에 있는 생명체들에게 8의 피해량을 줍니다.");
                             }
                             case "berserker_Active" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[광전사]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("체력이 일정량 이하일때 작동하는 패시브형 능력입니다.");
                                 sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "* 체력이 50%이하일때 - 힘 1증가");
                                 sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "* 체력이 35%이하일때 - 힘 2증가");
                                 sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "* 체력이 25%이하일때 - 힘 4증가");
                             }
                             case "blood_Flew" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[혈류 폭발]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 20초");
                                 sender.sendMessage("블럭을 좌클릭시, 해당 주변으로 피가 퍼지며");
                                 sender.sendMessage("해당 피 이펙트 위에 있으면, 구속과 피해를 줍니다.");
                                 sender.sendMessage("해당 피 이펙트는 5초가 지나면 자동으로 사라집니다.");
                             }
                             case "lighting" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[낙뢰]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 15초");
                                 sender.sendMessage(ChatColor.BOLD + "지팡이" + ChatColor.RESET + "를 든 상태에서 좌 클릭시 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage(ChatColor.BOLD + "지팡이" + ChatColor.RESET + "를 든 상태에서 좌 클릭시");
                                 sender.sendMessage("자신이 바라보는 블럭위치에 낙뢰를 떨어뜨립니다. (최대거리: 10블럭)");
                             }
                             case "teleport" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[텔레포트]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 20초");
                                 sender.sendMessage(ChatColor.BOLD + "지팡이" + ChatColor.RESET + "를 든 상태에서 우 클릭시 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage(ChatColor.BOLD + "지팡이" + ChatColor.RESET + "를 든 상태에서 우 클릭시");
                                 sender.sendMessage("자신이 바라보는 블럭위치로 순간이동합니다. (최대거리: 10블럭)");
                             }
                             case "meteor" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[메태오]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 40초");
                                 sender.sendMessage("");
                             }
                             case "dark_Magic" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[금지된 흑마법]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 25초");
                                 sender.sendMessage("우클릭 시, 자신의 체력을 소모시켜서 주변에 있는 모든 생명체들에게");
                                 sender.sendMessage("10초간 위더 효과를 줍니다.");
                             }
                             case "assassinate" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[암살]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("플레이어를 가격시, 2%확률로 작동하는 패시브형 능력입니다.");
                                 sender.sendMessage("플레이어를 가격시, 2%확률로 상대 플레이어를 즉사시킵니다.");
                             }
                             case "escape" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[도주]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 30초");
                                 sender.sendMessage("웅크리기를 하여 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage("웅크리기를 하면 현재 위치를 저장합니다.");
                                 sender.sendMessage("다시 웅크리기를 하면 저장하였던 위치로 이동하면서 이동 전 위치에 강력한 폭팔을 일으킵니다.");
                                 sender.sendMessage("죽더라도 저장된 위치는 저장된 채로 있습니다.");
                             }
                             case "killing_Stack" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[살생의 경험]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("생명체 (엔티티)를 죽일 때마다" + ChatColor.BOLD + " 살생의 경험" + ChatColor.RESET + "을 하나씩 얻습니다.");
                                 sender.sendMessage("기존 피해량에 " + ChatColor.BOLD + "살생의 경험" + ChatColor.RESET + "갯수만큼 피해량이 추가됩니다.");
                                 sender.sendMessage(ChatColor.BOLD + "살생의 경험" + ChatColor.RESET + "은 죽을 시 초기화됩니다.");
                                 sender.sendMessage("또, 피격할 때마다 상대방의 시야 반대로 이동합니다.");
                             }
                             case "request" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[의뢰]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("처음으로 피격한 상대를 의뢰 대상으로 설정합니다.");
                                 sender.sendMessage("의뢰 대상을 피격할 때마다 의뢰 대상에게 온갖 디버프를 랜덤하게 부여합니다. (한 번에 하나만 생기며, 지속시간은 5초입니다.");
                                 sender.sendMessage("의뢰 대상이 죽을 때까지 의뢰 상태가 풀리지않습니다.");
                                 sender.sendMessage("단, 도주를 사용한 경우 의뢰 대상이 사라집니다.");
                             }
                         }
                     }
                }
                else if (strings[1].equals("currentJob") || strings[1].equals("현재직업")) {
                    sender.sendMessage("현재 직업은 " + JobVariable.getPlayerJob(player) + "입니다.");
                }
            }
        return true;
    }
}