package me.guigarciazinho.Punir;

import org.bukkit.entity.Player;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.*;

public class Despunir implements CommandExecutor{
	public String exclamacao(){
		if(Principal.getInstance().getConfig().getString("Exclamacao").replace("&", "§") != null){
			return exclamacao();
		}
		return null;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
	
	if (command.getName().equalsIgnoreCase("despunir")) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§") + "§e Somente para players");
			return true;
		}
	}

	
	if (command.getName().equalsIgnoreCase("despunir")) {
		if (!(sender.hasPermission("punir.despunir"))) {
			if (args.length == 0) {
				if (sender instanceof Player) {
					sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
							+ "§e Plugin criado por guigarciazinho §d<3");
					return true;
				}
			}
		}
	} 
	
	if (command.getName().equalsIgnoreCase("despunir")) {
		if (sender.hasPermission("punir.despunir")) {
			if (args.length == 0) {
				if (sender instanceof Player) {
					sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
							+ Principal.getInstance().getConfig().getString("Uso incorreto despunir").replace("&", "§"));
					return true;
				}
			}
		}
	} 
	
	Player sn = (Player) sender;
	// Despunir jogadores punidos
			if (command.getName().equalsIgnoreCase("despunir")) {
				if (sn.hasPermission("punir.despunir")) {
					if (sender instanceof Player) {
						if (args.length == 1) {
							for (OfflinePlayer punido : Bukkit.getServer().getOfflinePlayers()) {
								for (Player all : Bukkit.getServer().getOnlinePlayers()) {
									if (punido.getName().equalsIgnoreCase(args[0])) {
										if (Principal.configBans.contains("Ban." + punido.getUniqueId())) {
											Principal.configBans.set("Ban." + punido.getUniqueId(), null);
											Bukkit.getServer()
											.broadcastMessage(exclamacao());
											Bukkit.getServer()
													.broadcastMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
															+ Principal.getInstance().getConfig().getString("Desbanido").replace("&", "§")
																	.replace("@player", punido.getName()).replace("@adm",
																			sn.getName()));
											Bukkit.getServer()
											.broadcastMessage(exclamacao());
											sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
													+ Principal.getInstance().getConfig().getString("Desbaniu").replace("&", "§"));
											sn.getPlayer().playSound(sn.getLocation(), Sound.LEVEL_UP, 1, 1);
											all.getPlayer().playSound(all.getLocation(), Sound.WOLF_GROWL, 1, 1);
											try {
												Principal.configBans.save(Principal.configFile);
											} catch (IOException e) {
												e.printStackTrace();
											}
											return true;

										} else if (!(Principal.configBans.contains("Ban." + punido.getUniqueId()))) { // Tentou
																											// despunir
																											// um
																											// jogador
																											// que
																											// nao
																											// esta
																											// punido
											sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
													+ Principal.getInstance().getConfig().getString("Jogador nao banido").replace("&", "§")
															.replace("@player", punido.getName()));
											return true;

										}
									}
								}
							}
						}

					}

				} else if (!(sn.hasPermission("punir.despunir"))) { // Tentou
																	// despunir, mas
																	// nao tem
																	// permissao
					sender.sendMessage(Principal.getInstance().getConfig().getString("Prefixo").replace("&", "§")
							+ Principal.getInstance().getConfig().getString("Sem permissao").replace("&", "§"));

					return true;
				}
			}
	
	return false;
	}

}
