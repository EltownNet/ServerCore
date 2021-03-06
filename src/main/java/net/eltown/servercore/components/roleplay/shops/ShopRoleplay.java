package net.eltown.servercore.components.roleplay.shops;

import net.eltown.servercore.ServerCore;
import net.eltown.servercore.components.api.intern.SyncAPI;
import net.eltown.servercore.components.event.PlayerBuyItemEvent;
import net.eltown.servercore.components.event.PlayerSellItemEvent;
import net.eltown.servercore.components.forms.custom.CustomWindow;
import net.eltown.servercore.components.forms.modal.ModalWindow;
import net.eltown.servercore.components.forms.simple.SimpleWindow;
import net.eltown.servercore.components.language.Language;
import net.eltown.servercore.components.roleplay.ChainExecution;
import net.eltown.servercore.components.roleplay.ChainMessage;
import net.eltown.servercore.components.roleplay.Cooldown;
import net.eltown.servercore.components.roleplay.RoleplayID;
import net.eltown.servercore.listeners.RoleplayListener;
import net.eltown.servercore.utils.Sound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public record ShopRoleplay(ServerCore serverCore) {

    public static HashMap<RoleplayID, Shop> availableShops = new HashMap<>();

    public ShopRoleplay(final ServerCore serverCore) {
        this.serverCore = serverCore;

        availableShops.put(RoleplayID.SHOP_LUMBERJACK, new Shop("Darick", "§a",
                new LinkedList<>(List.of(
                        Material.OAK_LOG,
                        Material.SPRUCE_LOG,
                        Material.BIRCH_LOG,
                        Material.JUNGLE_LOG,
                        Material.ACACIA_LOG,
                        Material.DARK_OAK_LOG
                )),
                new LinkedList<>(List.of(
                        new ChainMessage("Guten Tag, §a%p§7! Ich habe viele hochwertige Holzsorten in meinem Sortiment. Schau dich gerne um!", 4),
                        new ChainMessage("Man sagt, es gäbe bei mir das beste Holz!", 3),
                        new ChainMessage("Lass dich nicht von mir stören!", 3),
                        new ChainMessage("Heute gibt es tolle Angebote!", 2),
                        new ChainMessage("Treue Kunden sind immer gut!", 2),
                        new ChainMessage("Was ein schöner Tag zum Kaufen von gutem Holz!", 3),
                        new ChainMessage("Qualitativ ist mein Holz definitiv das beste!", 3)
                ))
        ));
        availableShops.put(RoleplayID.SHOP_MINER, new Shop("Patrick", "§b",
                new LinkedList<>(List.of(
                        Material.COAL,
                        Material.IRON_INGOT,
                        Material.COPPER_INGOT,
                        Material.GOLD_INGOT,
                        Material.REDSTONE,
                        Material.LAPIS_LAZULI,
                        Material.DIAMOND,
                        Material.EMERALD
                )),
                new LinkedList<>(List.of(
                        new ChainMessage("Hallo, §a%p§7! Schau, wie viel tolle Erze ich anbiete. So viele hat niemand!", 4),
                        new ChainMessage("Für gute Qualität muss man auch zahlen!", 3),
                        new ChainMessage("Rohstoffe wie diese braucht man immer, stimmts?", 3),
                        new ChainMessage("Ich habe so viel in meinem Sortiment wie noch nie!", 3),
                        new ChainMessage("Eine treue Kundschaft zahlt sich aus!", 2),
                        new ChainMessage("Hast du gekauft, so hast du gewonnen; verkaufe, und du wirst verlieren.", 3),
                        new ChainMessage("Schau dich ruhig um, ich habe Zeit!", 3)
                ))
        ));
        availableShops.put(RoleplayID.SHOP_EXPLORER, new Shop("Maya", "§e",
                new LinkedList<>(List.of(
                        Material.POPPY,
                        Material.DANDELION,
                        Material.AZURE_BLUET,
                        Material.SUNFLOWER,
                        Material.WITHER_ROSE,
                        Material.BLUE_ORCHID,
                        Material.ALLIUM,
                        Material.RED_TULIP,
                        Material.ORANGE_TULIP,
                        Material.WHITE_TULIP,
                        Material.PINK_TULIP,
                        Material.OXEYE_DAISY,
                        Material.CORNFLOWER,
                        Material.LILY_OF_THE_VALLEY,
                        Material.DEAD_BUSH,
                        Material.LILY_PAD,
                        Material.BAMBOO,
                        Material.ROSE_BUSH,
                        Material.PEONY,
                        Material.LARGE_FERN,
                        Material.FERN,
                        Material.AZALEA,
                        Material.FLOWERING_AZALEA,
                        Material.SPORE_BLOSSOM
                )),
                new LinkedList<>(List.of(
                        new ChainMessage("Schön dich zu sehen, §a%p§7! Ich erkunde die Welt und verkaufe gefundene Pflanzen!", 4),
                        new ChainMessage("Natur ist mein Spezialgebiet. Ich liebe die Natur.", 3),
                        new ChainMessage("Ich pflücke nur ausgewählte Blumen.", 3),
                        new ChainMessage("Bei mir gibt es immer tolle Angebote!", 3),
                        new ChainMessage("Ich erweitere meine Angebote sehr oft!", 3),
                        new ChainMessage("Lass dich nicht von mir stören! Schau dich ruhig um.", 3),
                        new ChainMessage("Schau dich ruhig um, ich habe Zeit!", 3)
                ))
        ));
        availableShops.put(RoleplayID.SHOP_NETHER, new Shop("Lilly", "§4",
                new LinkedList<>(List.of(
                        Material.CRIMSON_STEM,
                        Material.WARPED_STEM,
                        Material.NETHER_WART_BLOCK,
                        Material.WARPED_WART_BLOCK,
                        Material.CRIMSON_NYLIUM,
                        Material.WARPED_NYLIUM,
                        Material.CRIMSON_FUNGUS,
                        Material.WARPED_FUNGUS,
                        Material.SHROOMLIGHT,
                        Material.BASALT,
                        Material.BLACKSTONE,
                        Material.SOUL_SOIL,
                        Material.SOUL_SAND,
                        Material.CRYING_OBSIDIAN,
                        Material.NETHER_WART,
                        Material.QUARTZ,
                        Material.GLOWSTONE_DUST
                )),
                new LinkedList<>(List.of(
                        new ChainMessage("Oh... Hey, §a%p§7! Ich bin immer in ganz anderen Welten unterwegs! Schau dich gerne mal um.", 4),
                        new ChainMessage("Meine Waren sind nicht von Lava beschädigt!", 3),
                        new ChainMessage("Ich spiele sehr oft mit dem Feuer!", 3),
                        new ChainMessage("Nicht so schüchtern, kauf ruhig.", 3),
                        new ChainMessage("Ich weiß wie die Hölle aussieht...", 3),
                        new ChainMessage("Kaufe was du möchtest, du bist frei!", 3),
                        new ChainMessage("Rot ist meine absolute Lieblingsfarbe!", 3)
                ))
        ));
        availableShops.put(RoleplayID.SHOP_MONSTERHUNTER, new Shop("Amanda", "§5",
                new LinkedList<>(List.of(
                        Material.GUNPOWDER,
                        Material.ROTTEN_FLESH,
                        Material.STRING,
                        Material.ENDER_PEARL,
                        Material.SLIME_BALL,
                        Material.SPIDER_EYE,
                        Material.MAGMA_CREAM,
                        Material.BLAZE_ROD,
                        Material.FEATHER,
                        Material.LEATHER,
                        Material.BONE,
                        Material.GHAST_TEAR,
                        Material.NETHER_STAR
                )),
                new LinkedList<>(List.of(
                        new ChainMessage("Hi §a%p§7! Komm her und schau dich um! Ich habe alles, was du dir von Monstern vorstellen kannst.", 4),
                        new ChainMessage("Das Jagen ist kein einfacher Job!", 3),
                        new ChainMessage("Schau dir meine neuen Angebote an!", 3),
                        new ChainMessage("Letztens wurde ich von einer Kuh überrannt!", 3),
                        new ChainMessage("Wenn etwas fehlt, sag mir bescheid!", 3),
                        new ChainMessage("Geringe Preise für tolle Qualität!", 3),
                        new ChainMessage("Man sagt, ich besäße besondere Kräfte...", 3)
                ))
        ));
        availableShops.put(RoleplayID.JOB_FARMER, new Shop("Sofi", "§3",
                new LinkedList<>(List.of(
                        Material.BEETROOT,
                        Material.CARROT,
                        Material.WHEAT,
                        Material.POTATO,
                        Material.APPLE
                )),
                new LinkedList<>(List.of(
                        new ChainMessage("Hallo §a%p§7! Bei mir gibt es das beste Obst und Gemüse aus der ganzen Stadt!", 4),
                        new ChainMessage("Ich besitze die meisten Felder!", 3),
                        new ChainMessage("Ich dünge nur mit natürlichen Mitteln!", 3),
                        new ChainMessage("Bäuerin zu sein ist ein echter Knochenjob.", 3),
                        new ChainMessage("Wenn etwas fehlt, sag mir bescheid!", 3),
                        new ChainMessage("Geringe Preise für tolle Qualität!", 3),
                        new ChainMessage("Ich ernte nur die besten Früchte!", 3)
                ))
        ));
        availableShops.put(RoleplayID.SHOP_FISHERMAN, new Shop("Harvey", "§6",
                new LinkedList<>(List.of(
                        Material.COD,
                        Material.SALMON,
                        Material.TROPICAL_FISH,
                        Material.PUFFERFISH,
                        Material.SEAGRASS,
                        Material.KELP,
                        Material.DRIED_KELP,
                        Material.SEA_PICKLE
                )),
                new LinkedList<>(List.of(
                        new ChainMessage("Ahoi, §a%p§7! Ich verkaufe nur den besten Fisch!", 3),
                        new ChainMessage("Duuu Landratte!", 2),
                        new ChainMessage("Mein alter Kutter heißt Maja!", 3),
                        new ChainMessage("Meine Fischernetze sind immer randvoll.", 3),
                        new ChainMessage("Alternativ verkaufe ich auch Seetang!", 3),
                        new ChainMessage("Günstigere Fische gibt es nicht!", 3),
                        new ChainMessage("Ich begrüße eine treue Kundschaft sehr!", 3)
                ))
        ));
    }

    public void interact(final Player player, final Shop shop) {
        this.smallTalk(shop.messages(), shop.name(), player, message -> {
            if (message == null) {
                this.openShopItems(player, shop);
            } else {
                new ChainExecution.Builder()
                        .append(0, () -> {
                            player.sendMessage("§8» §f" + shop.name() + " §8| §7" + message.message().replace("%p", player.getName()));
                            Sound.MOB_VILLAGER_HAGGLE.playSound(player);
                        })
                        .append(message.seconds(), () -> {
                            this.openShopItems(player, shop);
                            Sound.MOB_VILLAGER_HAGGLE.playSound(player);
                            RoleplayListener.openQueue.remove(player.getName());
                        })
                        .build().start();
            }
        });
    }

    private void openShopItems(final Player player, final Shop shop) {
        final SimpleWindow.Builder shopItemsWindow = new SimpleWindow.Builder("§7» §8Händler " + shop.name(), "§8» §7Wähle eines der aufgelisteten Items aus, welches du kaufen oder verkaufen möchtest.\n\n");
        shop.items().forEach(material -> {
            this.serverCore.getShopAPI().getItemPrice(material.name(), 1, (buy, sell) -> {
                shopItemsWindow.addButton(new ItemStack(material).getI18NDisplayName() + "\n" + shop.color() + "§l1x   §r§a+ §r§f$" + this.serverCore.getMoneyFormat().format(buy) + " §8| §c- §r§f$" + this.serverCore.getMoneyFormat().format(sell), "http://eltown.net:3000/img/shopitems/" + material.name().toUpperCase() + ".png", e -> {
                    this.openItemShop(player, shop, material, buy, sell);
                    Sound.MOB_VILLAGER_HAGGLE.playSound(player);
                });
            });
        });
        shopItemsWindow.build().send(player);
    }

    private void openItemShop(final Player player, final Shop shop, final Material material, final double buy, final double sell) {
        final SimpleWindow.Builder itemShopWindow = new SimpleWindow.Builder("§7» §8" + new ItemStack(material).getI18NDisplayName(), "§8» §7Bitte wähle, ob du das Item kaufen oder verkaufen möchtest.\n\n");

        itemShopWindow.addButton("§8» §aKaufen\n§f$" + this.serverCore.getMoneyFormat().format(buy) + " §8pro Item", "", e -> {
            final CustomWindow selectWindow = new CustomWindow("§7» §8" + new ItemStack(material).getI18NDisplayName());
            selectWindow.form()
                    .input("§8» §fBitte gebe an, wie viel du von diesem Item §akaufen §fmöchtest.", "64", "1");

            selectWindow.onSubmit((g, h) -> {
                try {
                    final int i = Integer.parseInt(h.getInput(0));
                    if (i <= 0) throw new Exception("Invalid item amount");

                    this.serverCore.getShopAPI().getItemPrice(material.name(), i, (finalPrice, sellPrice) -> {
                        final ModalWindow buyWindow = new ModalWindow.Builder("§7» §8Kaufbestätigung", "Möchtest du §9" + i + "x " + new ItemStack(material).getI18NDisplayName() + " §ffür"
                                + " §a$" + this.serverCore.getMoneyFormat().format(finalPrice) + " §fkaufen?",
                                "§8» §aKaufen", "§8» §cAbbrechen")
                                .onYes(v -> {
                                    final ItemStack item = new ItemStack(material, i);
                                    if (!this.serverCore.canAddItem(player.getInventory(), item)) {
                                        player.sendMessage(Language.get("roleplay.shop.item.inventory.full"));
                                        Sound.MOB_VILLAGER_NO.playSound(player);
                                        return;
                                    }

                                    this.serverCore.getEconomyAPI().getMoney(player.getName(), money -> {
                                        if (money >= finalPrice) {
                                            this.serverCore.getEconomyAPI().reduceMoney(player.getName(), finalPrice);
                                            this.serverCore.getShopAPI().sendBought(material.name(), i);
                                            player.getInventory().addItem(item);
                                            player.sendMessage(Language.get("roleplay.shop.item.bought", i, item.getI18NDisplayName(), this.serverCore.getMoneyFormat().format(finalPrice)));
                                            this.serverCore.getServer().getScheduler().runTask(this.serverCore, () -> this.serverCore.getServer().getPluginManager().callEvent(new PlayerBuyItemEvent(player, shop, item, finalPrice)));
                                            Sound.MOB_VILLAGER_YES.playSound(player);
                                        } else {
                                            player.sendMessage(Language.get("roleplay.shop.item.not.enough.money"));
                                            Sound.MOB_VILLAGER_NO.playSound(player);
                                        }
                                    });
                                })
                                .onNo(v -> Sound.MOB_VILLAGER_NO.playSound(player))
                                .build();
                        buyWindow.send(player);
                    });
                } catch (final Exception exception) {
                    player.sendMessage(Language.get("roleplay.shop.item.invalid.amount"));
                    Sound.MOB_VILLAGER_NO.playSound(player);
                }
            });
            selectWindow.send(player);
        });
        itemShopWindow.addButton("§8» §cVerkaufen\n§f$" + this.serverCore.getMoneyFormat().format(sell) + " §8pro Item", "", e -> {
            final ItemStack item = new ItemStack(material, 1);
            final AtomicInteger count = new AtomicInteger(0);

            for (final ItemStack itemStack : player.getInventory().getContents()) {
                if (itemStack == null) continue;
                final ItemStack value = itemStack.clone();
                value.setAmount(1);
                if (SyncAPI.ItemAPI.itemStackToBase64(value).equals(SyncAPI.ItemAPI.itemStackToBase64(item))) count.addAndGet(itemStack.getAmount());
            }

            final CustomWindow selectWindow = new CustomWindow("§7» §8" + new ItemStack(material).getI18NDisplayName());
            selectWindow.form()
                    .input("§8» §fBitte gebe an, wie viel du von diesem Item §cverkaufen §fmöchtest.", "64", "1")
                    .toggle("§8» §fAlle Items dieser Art aus meinem Inventar verkaufen: §7" + count, false);

            selectWindow.onSubmit((g, h) -> {
                try {
                    final boolean b = h.getToggle(1);
                    if (b) {
                        if (count.get() <= 0) throw new Exception("Invalid item amount");

                        this.serverCore.getShopAPI().getItemPrice(material.name(), count.get(), (buyPrice, finalPrice) -> {
                            final ModalWindow buyWindow = new ModalWindow.Builder("§7» §8Verkaufsbestätigung", "Möchtest du §9" + count.get() + "x " + new ItemStack(material).getI18NDisplayName() + " §ffür"
                                    + " §a$" + this.serverCore.getMoneyFormat().format(finalPrice) + " §fverkaufen?",
                                    "§8» §aVerkaufen", "§8» §cAbbrechen")
                                    .onYes(v -> {
                                        item.setAmount(count.get());

                                        this.serverCore.getEconomyAPI().addMoney(player.getName(), finalPrice);
                                        this.serverCore.getShopAPI().sendSold(material.name(), count.get());
                                        player.getInventory().removeItem(item);
                                        player.sendMessage(Language.get("roleplay.shop.item.sold", count.get(), item.getI18NDisplayName(), this.serverCore.getMoneyFormat().format(finalPrice)));
                                        this.serverCore.getServer().getScheduler().runTask(this.serverCore, () -> this.serverCore.getServer().getPluginManager().callEvent(new PlayerSellItemEvent(player, shop, item, finalPrice)));
                                        Sound.MOB_VILLAGER_YES.playSound(player);
                                    })
                                    .onNo(v -> Sound.MOB_VILLAGER_NO.playSound(player))
                                    .build();
                            buyWindow.send(player);
                        });
                    } else {
                        final int i = Integer.parseInt(h.getInput(0));
                        if (i <= 0) throw new Exception("Invalid item amount");

                        this.serverCore.getShopAPI().getItemPrice(material.name(), i, (buyPrice, finalPrice) -> {
                            final ModalWindow buyWindow = new ModalWindow.Builder("§7» §8Verkaufsbestätigung", "Möchtest du §9" + i + "x " + new ItemStack(material).getI18NDisplayName() + " §ffür"
                                    + " §a$" + this.serverCore.getMoneyFormat().format(finalPrice) + " §fverkaufen?",
                                    "§8» §aVerkaufen", "§8» §cAbbrechen")
                                    .onYes(v -> {
                                        if (count.get() < i) {
                                            player.sendMessage(Language.get("roleplay.shop.item.inventory.invalid"));
                                            Sound.MOB_VILLAGER_NO.playSound(player);
                                            return;
                                        }
                                        item.setAmount(i);

                                        this.serverCore.getEconomyAPI().addMoney(player.getName(), finalPrice);
                                        this.serverCore.getShopAPI().sendSold(material.name(), i);
                                        player.getInventory().removeItem(item);
                                        player.sendMessage(Language.get("roleplay.shop.item.sold", i, item.getI18NDisplayName(), this.serverCore.getMoneyFormat().format(finalPrice)));
                                        Sound.MOB_VILLAGER_YES.playSound(player);
                                    })
                                    .onNo(v -> Sound.MOB_VILLAGER_NO.playSound(player))
                                    .build();
                            buyWindow.send(player);
                        });
                    }
                } catch (final Exception exception) {
                    player.sendMessage(Language.get("roleplay.shop.item.invalid.amount"));
                    Sound.MOB_VILLAGER_NO.playSound(player);
                }
            });
            selectWindow.send(player);
        });
        if (player.isOp()) {
            itemShopWindow.addButton("§8» §9Preis bearbeiten", "", e -> {
                this.serverCore.getShopAPI().getMinBuySell(material.name(), (minBuy, minSell) -> {
                    final CustomWindow editShopWindow = new CustomWindow("");
                    editShopWindow.form()
                            .input("§8» §fBitte gebe den neuen Preis für dieses Item an.", "5.95", this.serverCore.getMoneyFormat().format(buy))
                            .input("§8» §fBitte gebe den neuen minimalen Kaufpreis an.", "5.95", this.serverCore.getMoneyFormat().format(minBuy))
                            .input("§8» §fBitte gebe den neuen minimalen Verkaufspreis an.", "5.95", this.serverCore.getMoneyFormat().format(minSell));

                    editShopWindow.onSubmit((g, h) -> {
                        try {
                            final double newPrice = Double.parseDouble(h.getInput(0).replace(",", "."));
                            final double newMinBuy = Double.parseDouble(h.getInput(1).replace(",", "."));
                            final double newMinSell = Double.parseDouble(h.getInput(2).replace(",", "."));

                            if (buy != newPrice) this.serverCore.getShopAPI().setPrice(material.name(), newPrice);
                            if (minBuy != newMinBuy) this.serverCore.getTinyRabbit().send("api.shops.receive", "UPDATE_MIN_BUY", material.name(), newMinBuy + "");
                            if (minSell != newMinSell) this.serverCore.getTinyRabbit().send("api.shops.receive", "UPDATE_MIN_SELL", material.name(), newMinSell + "");
                            player.sendMessage("§8» §fCore §8| §7Die Einstellungen wurden gespeichert. [newPrice: " + newPrice + "; newMinBuy: " + newMinBuy + "; newMinSell: " + newMinSell + "]");
                        } catch (final Exception exception) {
                            player.sendMessage("§8» §fCore §8| §7Bitte gebe gültige Daten an.");
                        }
                    });
                    editShopWindow.send(player);
                });
            });
        }
        itemShopWindow.build().send(player);
    }

    static final Cooldown playerTalks = new Cooldown(TimeUnit.MINUTES.toMillis(15));
    static final Cooldown talkCooldown = new Cooldown(TimeUnit.SECONDS.toMillis(20));

    private void smallTalk(final List<ChainMessage> messages, final String npc, final Player player, final Consumer<ChainMessage> message) {
        if (talkCooldown.hasCooldown(npc + "//" + player.getName())) {
            message.accept(null);
            return;
        }
        if (!playerTalks.hasCooldown(npc + "//" + player.getName())) {
            message.accept(messages.get(0));
        } else {
            int index = ThreadLocalRandom.current().nextInt(1, messages.size());
            message.accept(messages.get(index));
        }
        RoleplayListener.openQueue.add(player.getName());
    }

    public record Shop(String name, String color, LinkedList<Material> items, LinkedList<ChainMessage> messages) {

    }

}
