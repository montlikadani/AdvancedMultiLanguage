package me.smessie.MultiLanguage.bungeecord.commands;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bungeecord.ChangeLanguageEvent;
import me.smessie.MultiLanguage.bungeecord.Implement;
import me.smessie.MultiLanguage.bungeecord.Main;
import me.smessie.MultiLanguage.main.Cache;
import me.smessie.MultiLanguage.main.Languages;
import me.smessie.MultiLanguage.main.MySQL;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.sql.SQLException;

public class Portuguese extends Command {

    public Portuguese() {
        super("linguagem");
    }

    ChatColor red = ChatColor.RED;

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {

            final ProxiedPlayer player = (ProxiedPlayer) sender;

            if (args.length == 1) {

                String taal = args[0];

                if (Languages.isSupportedLanguage(taal.toUpperCase())) {
                    final String formatTaal = taal.toUpperCase();
                    if (Implement.languageEnabled(formatTaal)) {
                        ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                        ProxyServer.getInstance().getPluginManager().callEvent(event);
                        if (event.isCancelled()) {
                            return;
                        }
                        ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
                            if (Main.useMysql) {
                                try {
                                    MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
                            }
                            Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                        });
                        player.sendMessage(new TextComponent(ChatColor.GREEN + "Sua linguagem foi setada para " + taal + "."));

                        if (Implement.warnOnSelect(taal)) {
                            player.sendMessage(new TextComponent(red + "Atenção! Você não pode falar " + taal + " no chat."));
                        }
                    } else {
                        player.sendMessage(new TextComponent(red + "Essa lingua está desativada! :("));
                    }
                } else if (Languages.isSupportedLanguageFull(taal.toLowerCase())) {
                    final String formatTaal = Languages.languagesFull.get(taal.toLowerCase());
                    if (Implement.languageEnabled(formatTaal)) {
                        ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                        ProxyServer.getInstance().getPluginManager().callEvent(event);
                        if (event.isCancelled()) {
                            return;
                        }
                        ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
                            if (Main.useMysql) {
                                try {
                                    MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
                            }
                            Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                        });
                        player.sendMessage(new TextComponent(ChatColor.GREEN + "Sua linguagem foi setada para " + taal + "."));

                        if (Implement.warnOnSelect(taal)) {
                            player.sendMessage(new TextComponent(red + "Atenção! Você não pode falar " + taal + " no chat."));
                        }
                    } else {
                        player.sendMessage(new TextComponent(red + "Essa lingua está desativada! :("));
                    }
                } else if (Languages.isSupportedLanguageOwn(taal.toLowerCase())) {
                    final String formatTaal = Languages.languagesOwn.get(taal.toLowerCase());
                    if (Implement.languageEnabled(formatTaal)) {
                        ChangeLanguageEvent event = new ChangeLanguageEvent(Language.getLanguageFromString(formatTaal), player);
                        ProxyServer.getInstance().getPluginManager().callEvent(event);
                        if (event.isCancelled()) {
                            return;
                        }
                        ProxyServer.getInstance().getScheduler().runAsync(Main.plugin, () -> {
                            if (Main.useMysql) {
                                try {
                                    MySQL.setLanguageMysql(player.getUniqueId().toString(), formatTaal, player.getAddress().toString());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Implement.setLanguageFile(player.getUniqueId().toString(), formatTaal);
                            }
                            Cache.setPlayerCachedLanguage(player.getUniqueId().toString(), Language.getLanguageFromString(formatTaal));
                        });
                        player.sendMessage(new TextComponent(ChatColor.GREEN + "Sua linguagem foi setada para " + taal + "."));

                        if (Implement.warnOnSelect(taal)) {
                            player.sendMessage(new TextComponent(red + "Atenção! Você não pode falar " + taal + " no chat."));
                        }
                    } else {
                        player.sendMessage(new TextComponent(red + "Essa lingua está desativada! :("));
                    }
                } else {
                    player.sendMessage(new TextComponent(red + "A lingua " + args[0] + " não foi encontrada!"));
                }
            } else {
                player.sendMessage(new TextComponent(red + "Use: /linguagem <linguagem>"));
            }
        } else {
            sender.sendMessage(new TextComponent(red + "Hé, Apenas jogadores dentro do jogo podem setar sua linguagem! :o"));
        }
    }
}
