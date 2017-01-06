package me.guigarciazinho.Punir;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GUI implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("puniradd")) {
			if (args.length == 2) {
				if ("kick".equalsIgnoreCase(args[1])) {
					Player punido = Bukkit.getPlayer(args[0]);
					if (punido.getName().equalsIgnoreCase(args[0])) {
						punido.kickPlayer("a");
					}

				}
			}
		}

		if (command.getName().equalsIgnoreCase("puniradd")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
						+ "§e Somente para players");
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("puniradd")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
						+ "§e Somente para players");
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("puniradd")) {
			if (!(sender.hasPermission("punir.add"))) {
				if (args.length == 0) {
					if (sender instanceof Player) {
						sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
								+ "§e Plugin criado por guigarciazinho §d<3");
						return true;
					}
				}
			}

			if (command.getName().equalsIgnoreCase("puniradd")) {
				if (sender instanceof Player) {
					if (sender.hasPermission("punir.add")) {
						if (args.length == 0) {
							sender.sendMessage(
									Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
											+ "§c Ops...§e Parece que você cometeu algum erro de sintaxe");
							return true;
						}
					}
				}
			}
		}

		if (command.getName().equalsIgnoreCase("puniradd")) {
			if (args.length == 1) {
				if ("list".equalsIgnoreCase(args[0])) {
					if (sender.hasPermission("punir.add")) {
						if (sender instanceof Player) {
							if (Principal.configUsers.contains("Liberados")) {
								Inventory inv = Bukkit.createInventory(null, 9 * 5,Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§"));

								Player p = (Player) sender;
								for (String s : Principal.configUsers.getConfigurationSection("Liberados")
										.getKeys(false)) {

									ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
									SkullMeta m = (SkullMeta) item.getItemMeta();
									m.setOwner(Principal.configUsers.getString("Liberados." + s + ".Nome"));
									m.setDisplayName(Principal.configUsers.getString("Liberados." + s + ".Nome"));
									item.setItemMeta(m);
									inv.addItem(item);

								}
								p.openInventory(inv);
								return true;
							} else if (!(Principal.configUsers.contains("Liberados."))) {
								sender.sendMessage(
										Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
												+ "§e Não existem jogadores neste arquivo.");
								return true;

							}

						}

					}
				}
			}
		}

		if (command.getName().equalsIgnoreCase("puniradd")) {
			Player add = Bukkit.getPlayer(args[0]);
			if (add != null) {
				if (sender.hasPermission("punir.add")) {
					if (sender instanceof Player) {
						if (args.length == 1) {
							if (add.getName().equalsIgnoreCase(args[0])) {
								if (!(Principal.configUsers.contains("Liberados." + add.getUniqueId()))) {

									Principal.configUsers.set("Liberados." + add.getUniqueId() + ".Nome", args[0]);
									sender.sendMessage(
											Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
													+ "§c Voce adicionou: §e" + args[0]
													+ "§c como um super usuario no plugin Punir");
									try {
										Principal.configUsers.save(Principal.configFile2);
									} catch (IOException e) {
										e.printStackTrace();
									}
									return true;

								} else if (Principal.configUsers.contains("Liberados." + add.getUniqueId())) {
									sender.sendMessage(
											Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
													+ "§c Este usuário já se encontra Liberado");
									return true;
								}

							} else if (args.length != 1) {
								sender.sendMessage(
										Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
												+ "§c Uso incorreto. Use /puniradd Steve");
								return true;
							}
						}
					}

				} else if (!(sender.hasPermission("punir.add"))) {
					sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
							+ Principal.getInstance().getConfig().getString("Sem permissao").replace("&", "§"));
					return true;
				}
			}
		}
		if (command.getName().equalsIgnoreCase("puniradd")) {
			for (OfflinePlayer add : Bukkit.getServer().getOfflinePlayers()) {
				if (sender.hasPermission("punir.add")) {
					if (sender instanceof Player) {
						if (args.length == 1) {
							if (add.getName().equalsIgnoreCase(args[0])) {
								if (!(Principal.configUsers.contains("Liberados." + add.getUniqueId()))) {
									if (add.hasPlayedBefore() == true) {

										Principal.configUsers.set("Liberados." + add.getUniqueId() + ".Nome", args[0]);
										sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo")
												.replace("&", "§") + "§c Voce adicionou: §e" + args[0]
												+ "§c como um super usuario no plugin Punir");
										try {
											Principal.configUsers.save(Principal.configFile2);
										} catch (IOException e) {
											e.printStackTrace();
										}
										return true;
									} else if (add.hasPlayedBefore() == false) {
										sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo")
												.replace("&", "§")
												+ Principal.getInstance().getConfig().getString("Nunca jogou")
														.replace("@player", args[0]));
										return true;
									}

								} else if (Principal.configUsers.contains("Liberados." + add.getUniqueId())) {
									sender.sendMessage(
											Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
													+ "§c Este usuário já se encontra Liberado");
									return true;

								}
							}

						} else if (args.length != 1) {
							sender.sendMessage(
									Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
											+ "§c Uso incorreto. Use /puniradd Steve");
							return true;
						}

					}
				} else if (!(sender.hasPermission("punir.add"))) {
					sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
							+ Principal.getInstance().getConfig().getString("Sem permissao").replace("&", "§"));
					return true;
				}
			}
		}

		return false;
	}
	
	

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase(
				Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§"))) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != null) {
				e.setCancelled(true);

				Player p = (Player) e.getWhoClicked();
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
					Player c = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().trim());

					if (p.hasPermission("punir.add.remove")) {
						if (c != null) {
							if (c.isOnline() == true) {
								Principal.configUsers.set("Liberados." + c.getUniqueId(), null);
								p.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
										+ "§e Você removeu: §b" + c.getName()
										+ "§e dos super usuarios do plugin Punir");
								p.closeInventory();
								p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.LEVEL_UP, 1, 1);
								c.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
										+ "§e Você foi removido(a) da lista de super usuarios do plugin punir por: §b"
										+ p.getName());
								c.getPlayer().playSound(c.getPlayer().getLocation(), Sound.WOLF_HOWL, 1, 1);

								try {
									Principal.configUsers.save(Principal.configFile2);
								} catch (IOException d) {
									d.printStackTrace();
								}
								
							}else if(c.isOnline() == false || c == null){
								p.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§") + "§e Você não pôde remover: §b" + c.getName() + "§e dos super usuarios do plugin punir, pois o mesmo se encontra offline.");
								
							}
						}
					} else if (p.hasPermission("puniradd.remove")) {
						p.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
								+ Principal.getInstance().getConfig().getString("Sem permissao").replace("&", "§"));

					}
				}
			}
		}
	}

	@EventHandler
	public void aoFecharInv(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if (e.getInventory().getName().equalsIgnoreCase(
				Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§"))) {
			p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.CHEST_CLOSE, 2, 1);

		}
	}
}
