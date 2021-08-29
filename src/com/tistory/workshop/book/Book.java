package com.tistory.workshop.book;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
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

        // TODO 들어온 사람한테 해당 책이 이미 있다면 더 이상 지급할 필요없음
        e.setJoinMessage("입장");
        Player player = e.getPlayer();  // 들어오는 사람

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);  // 반드시 WRITIEN_BOOK으로 하여야함
        BookMeta bookMeta = (BookMeta) book.getItemMeta();      // 해당 책의 메타타입을 설정함

        BaseComponent[] page = new ComponentBuilder("Click me")
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://spigot.org"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("GO TO SEE WEBSITE!").create()))
                .create();

        // OPEN_URL 말고 명령어 사용하는 것도 존재함

        bookMeta.spigot().addPage(page);    // 책 메타 타입에 페이지를 하나 추가함

        bookMeta.setTitle("JUST BOOK");     // 책 제목
        bookMeta.setAuthor("ME");   // 책 지은이

        book.setItemMeta(bookMeta);     // 해당 책에게 메타타입 적용

        player.getInventory().addItem(book);    // 들어오는 사람한테 책 한 권 지급

    }
}
