package com.tistory.workshop.book;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Book implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("입장");
        Player player = e.getPlayer();  // 들어오는 사람

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);  // 반드시 WRITIEN_BOOK으로 하여야함
        BookMeta bookMeta = (BookMeta) book.getItemMeta();      // 해당 책의 메타타입을 설정함

        // 파이터

        BaseComponent[] quick_hook = new ComponentBuilder(ChatColor.DARK_RED + "[퀵 훅]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription quick_Hook"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("퀵 훅 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] the_king = new ComponentBuilder(ChatColor.DARK_BLUE + "[더 킹]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription the_King"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("더 킹 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] selectFighter = new ComponentBuilder(ChatColor.LIGHT_PURPLE + "[직업 선택]")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ setCharacter Fighter"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("파이터 클래스로 설정합니다.").create()))
                .create();

        BaseComponent[] fighters = new ComponentBuilder("FIGHTER\n")
                .append(ChatColor.RED + "[체력]" + ChatColor.BLACK + "  30\n")
                .append(ChatColor.AQUA + "[기본 방어력]" + ChatColor.BLACK + "  0\n")
                .append(quick_hook)
                .append(the_king)
                .append(selectFighter)
                .create();
        
        // 머스킷티어

        BaseComponent[] grenade = new ComponentBuilder(ChatColor.DARK_RED + "[수류탄]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription grenade"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("수류탄 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] head_Shot = new ComponentBuilder(ChatColor.DARK_BLUE + "[헤드샷]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription head_Shot"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("헤드샷 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] selectMusketeer = new ComponentBuilder(ChatColor.LIGHT_PURPLE + "[직업 선택]")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ setCharacter Musketeer"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("머스킷티어 클래스로 설정합니다.").create()))
                .create();

        BaseComponent[] musketeer = new ComponentBuilder("MUSKETEER\n")
                .append(ChatColor.RED + "[체력]" + ChatColor.BLACK + "  20\n")
                .append(ChatColor.AQUA + "[기본 방어력]" + ChatColor.BLACK + "  0\n")
                .append(grenade)
                .append(head_Shot)
                .append(selectMusketeer)
                .create();

        // 버서커

        BaseComponent[] rush = new ComponentBuilder(ChatColor.DARK_RED + "[무모한 공격]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription rush"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("무모한 공겨 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] berserker_Active = new ComponentBuilder(ChatColor.DARK_BLUE + "[광전사]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription berserker_Active"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("광전사 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] selectBerserker = new ComponentBuilder(ChatColor.LIGHT_PURPLE + "[직업 선택]")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ setCharacter Berserker"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("버서커 클래스로 설정합니다.").create()))
                .create();

        BaseComponent[] berserker = new ComponentBuilder("BERSERKER\n")
                .append(ChatColor.RED + "[체력]" + ChatColor.BLACK + "  40\n")
                .append(ChatColor.AQUA + "[기본 방어력]" + ChatColor.BLACK + "  2\n")
                .append(rush)
                .append(berserker_Active)
                .append(selectBerserker)
                .create();

        // 마법사

        BaseComponent[] lighting = new ComponentBuilder(ChatColor.DARK_RED + "[낙뢰]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription lighting"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("낙뢰 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] teleport = new ComponentBuilder(ChatColor.DARK_BLUE + "[텔레포트]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription teleport"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("텔레포트 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] selectWizard = new ComponentBuilder(ChatColor.LIGHT_PURPLE + "[직업 선택]")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ setCharacter Wizard"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("마법사 클래스로 설정합니다.").create()))
                .create();

        BaseComponent[] wizard = new ComponentBuilder("WIZARD\n")
                .append(ChatColor.RED + "[체력]" + ChatColor.BLACK + "  16\n")
                .append(ChatColor.AQUA + "[기본 방어력]" + ChatColor.BLACK + "  0\n")
                .append(lighting)
                .append(teleport)
                .append(selectWizard)
                .create();

        // 암살자

        BaseComponent[] assassinate = new ComponentBuilder(ChatColor.DARK_RED + "[암살]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription assassinate"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("암살 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] escape = new ComponentBuilder(ChatColor.DARK_BLUE + "[도주]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription escape"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("도주 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] killingStack = new ComponentBuilder(ChatColor.GOLD + "[살생의 경험]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription killing_Stack"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("살생의 경험에 대한 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] selectAssassin = new ComponentBuilder(ChatColor.LIGHT_PURPLE + "[직업 선택]")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ setCharacter Assassin"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("암살자 클래스로 설정합니다.").create()))
                .create();

        BaseComponent[] assassin = new ComponentBuilder("ASSASSIN\n")
                .append(ChatColor.RED + "[체력]" + ChatColor.BLACK + "  16\n")
                .append(ChatColor.AQUA + "[기본 방어력]" + ChatColor.BLACK + "  0\n")
                .append(assassinate)
                .append(escape)
                .append(killingStack)
                .append(selectAssassin)
                .create();


        bookMeta.spigot().addPage(fighters, musketeer, berserker, wizard, assassin);    // 책 메타 타입에 페이지를 하나 추가함

        bookMeta.setTitle("JUST BOOK");     // 책 제목
        bookMeta.setAuthor("ME");   // 책 지은이

        book.setItemMeta(bookMeta);     // 해당 책에게 메타타입 적용

        if (player.getInventory().contains(book) || player.getInventory().contains(Material.WRITTEN_BOOK))
            return;

        player.getInventory().addItem(book);    // 들어오는 사람한테 책 한 권 지급

    }
}
