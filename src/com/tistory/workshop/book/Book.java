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



        BaseComponent[] quick_hook = new ComponentBuilder(ChatColor.DARK_RED + "[퀵 훅]")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ showDescription quick_hook"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("퀵 훅 설명을 보실려면 클릭하여주세요.").create()))
                .create();

        // BaseComponent[] the_king

        BaseComponent[] Fighter = new ComponentBuilder("FIGHTER")
                .append(ChatColor.RED + "[체력]" + ChatColor.WHITE + "\t30")
                .append(ChatColor.AQUA + "[기본 방어력]" + ChatColor.WHITE + "\t0")
                .append(ChatColor.DARK_RED + "[퀵 훅]" + ChatColor.WHITE + "\t평타의 2배의 피해를 입힙니다.")
                .append(quick_hook)
                .create();

        BaseComponent[] page1 = new ComponentBuilder("Click me")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg_ setCharacter Fighter"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("파이터 클래스로 설정합니다.").create()))
                .append("Hello")
                .create();

        







        // 각 페이지당 노가다 뛰어야함

        bookMeta.spigot().addPage(page1);    // 책 메타 타입에 페이지를 하나 추가함

        bookMeta.setTitle("JUST BOOK");     // 책 제목
        bookMeta.setAuthor("ME");   // 책 지은이

        book.setItemMeta(bookMeta);     // 해당 책에게 메타타입 적용

        if (player.getInventory().contains(book) || player.getInventory().contains(Material.WRITTEN_BOOK))
            return;

        player.getInventory().addItem(book);    // 들어오는 사람한테 책 한 권 지급

    }
}
