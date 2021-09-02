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

import java.util.ArrayList;

public class Book implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("입장");
        Player player = e.getPlayer();  // 들어오는 사람

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);  // 반드시 WRITIEN_BOOK으로 하여야함
        BookMeta bookMeta = (BookMeta) book.getItemMeta();      // 해당 책의 메타타입을 설정함

        // 능력들은 명령어랑 결합하고
        // 직업 선택도 명령어로 하고
        // 그외는 전부 기본 String으로 처리

        BaseComponent[] quick_hook = new ComponentBuilder(ChatColor.DARK_RED + "[퀵 훅]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription quick_hook"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("퀵 훅 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        BaseComponent[] the_king = new ComponentBuilder(ChatColor.DARK_BLUE + "[더 킹]\n")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription the_king"))
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

        bookMeta.spigot().addPage(fighters);    // 책 메타 타입에 페이지를 하나 추가함

        bookMeta.setTitle("JUST BOOK");     // 책 제목
        bookMeta.setAuthor("ME");   // 책 지은이

        book.setItemMeta(bookMeta);     // 해당 책에게 메타타입 적용

        if (player.getInventory().contains(book) || player.getInventory().contains(Material.WRITTEN_BOOK))
            return;

        player.getInventory().addItem(book);    // 들어오는 사람한테 책 한 권 지급

    }
}
