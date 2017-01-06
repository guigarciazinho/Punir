package me.guigarciazinho.Punir;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class Principal extends JavaPlugin implements Listener, CommandExecutor {

	public static File configFile;
	public static File configFile2;
	public static YamlConfiguration configBans;
	public static YamlConfiguration configUsers;
	private static Principal instance;

	@Override
	public void onEnable() {
		instance = this;

		getCommand("despunir").setExecutor(new Despunir());
		getCommand("puniradd").setExecutor(new GUI());
		getServer().getPluginManager().registerEvents(new GUI(), this);
		getServer().getPluginManager().registerEvents(this, this);
		configFile = new File(getDataFolder(), "Banidos.yml");
		if (!configFile.exists()) {
			saveResource("Banidos.yml", false);
		}
		configBans = YamlConfiguration.loadConfiguration(configFile);
		try {
			configBans.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		configFile2 = new File(getDataFolder(), "Superusuarios.yml");
		if (!configFile2.exists()) {
			saveResource("Superusuarios.yml", false);
		}
		configUsers = YamlConfiguration.loadConfiguration(configFile2);
		try {
			configUsers.save(configFile2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		saveDefaultConfig();
		getLogger().info("-------------------------------");
		getLogger().info("Habilitado com sucesso");
		getLogger().info("Criado por guigarciazinho, kami");
		getLogger().info("-------------------------------");

	}

	public static Principal getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll();
		getLogger().info("-------------------------------");
		getLogger().info("Desabilitado com sucesso");
		getLogger().info("Criado por guigarciazinho, kami");
		getLogger().info("-------------------------------");
	}
	public String exclamacao(){
		if(getConfig().getString("Exclamacao").replace("&", "§") != null){
			return exclamacao();
		}
		return null;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player sn = (Player) sender;
		if (command.getName().equalsIgnoreCase("punir")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§") + "§e Somente para players");
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("punir")) {
			if (sender.hasPermission("punir.punir")) {
				if (args.length == 0 || args.length == 1) {
					if (sender instanceof Player) {
						sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§")
								+ getConfig().getString("Uso incorreto").replace("&", "§"));
						return true;
					}
				}
			}
		}
		if (command.getName().equalsIgnoreCase("punir")) {
			if (!(sender.hasPermission("punir.punir"))) {
				if (args.length == 0) {
					if (sender instanceof Player) {
						sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§")
								+ "§e Plugin criado por guigarciazinho §d<3");
						return true;
					}
				}
			}
		}
		

		// Online
		if (command.getName().equalsIgnoreCase("punir")) {
			Player punido = Bukkit.getPlayer(args[0]);
			if (punido != null) {
				if (sender instanceof Player) {
					if (args.length >= 2) {
						String mot = "";
						for (int i = 1; i < args.length; i++) {
							mot = mot + args[i] + " ";
						}
						if (sender.hasPermission("punir.punir")) {

							if (punido.getName().equalsIgnoreCase(args[0])) {
								if (!configUsers.contains("Liberados." + punido.getUniqueId())) {
									if (!configBans.contains("Ban." + punido.getUniqueId())) {
										Bukkit.broadcastMessage(configBans.getString("Mensagem"));
										for (Player all : Bukkit.getOnlinePlayers()) {
											punido.kickPlayer(getConfig().getString("Prefixo").replace("&", "§") + "\n§cVoce foi banido por: §b" + sender.getName() + "\n§eMotivo: §d" + mot);
											Bukkit.getServer()
													.broadcastMessage(exclamacao());
											Bukkit.getServer()
													.broadcastMessage(getConfig().getString("Prefixo").replace("&", "§")
															+ getConfig().getString("Baniu").replace("&", "§")
																	.replace("@player", punido.getName())
																	.replace("@adm", sn.getName()));

											Bukkit.getServer()
													.broadcastMessage(getConfig().getString("Prefixo").replace("&", "§")
															+ getConfig().getString("Motivo").replace("&", "§") + mot);

											Bukkit.getServer()
											.broadcastMessage(exclamacao());
											configBans.set("Ban." + punido.getUniqueId() + ".Nome", args[0]);
											configBans.set("Ban." + punido.getUniqueId() + ".Staff", sn.getName());
											configBans.set("Ban." + punido.getUniqueId() + ".Motivo",
													mot.toString().replace("&", "§"));
											sn.getPlayer().playSound(sn.getLocation(), Sound.LEVEL_UP, 2, 3);
											all.playSound(all.getLocation(), Sound.WOLF_HOWL, 2, 3);
											try {
												configBans.save(configFile);
											} catch (IOException e) {
												e.printStackTrace();
											}
											return true;
										}
									}else if (configBans.contains("Ban." + punido.getUniqueId())) {
								sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§") + getConfig()
										.getString("Ja punido").replace("&", "§").replace("@player", punido.getName()));
								sn.getPlayer().playSound(sn.getLocation(), Sound.LEVEL_UP, 1, 1);
								return true;
							}

								}else if (configUsers.contains("Liberados." + punido.getUniqueId())) {// Tentou
									// punir
									// algum
									// superior
									sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§")
											+ getConfig().getString("Nao permitido").replace("&", "§")
													.replace("@player", args[0]));
									return true;
								}

							}

						}else if (!(sender.hasPermission("punir.punir"))) {
							sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§")
									+ getConfig().getString("Sem permissao").replace("&", "§"));
							return true;

						}
					}
				}
			}
		}

		// Offline
		if (sender.hasPermission("punir.punir")) {
			if (command.getName().equalsIgnoreCase("punir")) {
				if (args.length >= 2) {
					String mot = "";
					for (int i = 1; i < args.length; i++) {
						mot = mot + args[i] + " ";
					}
					if (sender instanceof Player) {
						for (OfflinePlayer punido : Bukkit.getServer().getOfflinePlayers()) {
							if (!configBans.contains("Ban." + punido.getUniqueId())) {
								for (Player all : Bukkit.getServer().getOnlinePlayers()) {
									if (punido.getName().equalsIgnoreCase(args[0])) {
										if (!configUsers.contains("Liberados." + punido.getUniqueId())) {
											if (punido.hasPlayedBefore() == true) {
												
												Bukkit.getServer()
												.broadcastMessage(exclamacao());
													Bukkit.getServer().broadcastMessage(
															getConfig().getString("Prefixo").replace("&", "§")
																	+ getConfig().getString("Baniu").replace("&", "§")
																			.replace("@player", punido.getName())
																			.replace("@adm", sn.getName()));

													Bukkit.getServer().broadcastMessage(
															getConfig().getString("Prefixo").replace("&", "§")
																	+ getConfig().getString("Motivo").replace("&", "§")
																	+ mot);

													Bukkit.getServer()
													.broadcastMessage(exclamacao());
													configBans.set("Ban." + punido.getUniqueId() + ".Nome", args[0]);
													configBans.set("Ban." + punido.getUniqueId() + ".Staff",
															sn.getName());
													configBans.set("Ban." + punido.getUniqueId() + ".Motivo",
															mot.toString().replace("&", "§"));
													sn.getPlayer().playSound(sn.getLocation(), Sound.LEVEL_UP, 2, 3);
													all.playSound(all.getLocation(), Sound.WOLF_HOWL, 2, 3);
													try {
														configBans.save(configFile);
													} catch (IOException e) {
														e.printStackTrace();
													}
													return true;
											} else if (punido.hasPlayedBefore() == false) {
												sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§")
														+ getConfig().getString("Nunca jogou").replace("&", "§")
																.replace("@player", args[0]));

											}
										}else if (configUsers.contains("Liberados." + punido.getUniqueId())) {// Tentou
											// punir
											// algum
											// superior
											sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§")
													+ getConfig().getString("Nao permitido").replace("&", "§")
															.replace("@player", args[0]));
											return true;
										}
									} 
								}

							} else if (configBans.contains("Ban." + punido.getUniqueId())) {
								sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§") + getConfig()
										.getString("Ja punido").replace("&", "§").replace("@player", punido.getName()));
								sn.getPlayer().playSound(sn.getLocation(), Sound.WOLF_HOWL, 1, 1);
								return true;
							}

						}
					}
				}

			} 
		}else if (!(sender.hasPermission("punir.punir"))) {
			sender.sendMessage(getConfig().getString("Prefixo").replace("&", "§")
					+ getConfig().getString("Sem permissao").replace("&", "§"));
			return true;

		}

		return false;
	}

	public boolean Banido(UUID uuid) {
		if (configBans.contains("Ban." + uuid)) {
			return true;
		}
		return false;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void taBan(PlayerPreLoginEvent e) {
		if (Banido(e.getUniqueId()) == true) {
			e.setResult(org.bukkit.event.player.PlayerPreLoginEvent.Result.KICK_BANNED);
			e.setKickMessage(getConfig().getString("Prefixo").replace("&", "§") + "\n§eVocê foi banido por:§b "
					+ configBans.getString("Ban." + e.getUniqueId() + ".Staff") + "\n§cMotivo: "
					+ configBans.getString("Ban." + e.getUniqueId() + ".Motivo".replace("&", "§")));
		}

	}


}
