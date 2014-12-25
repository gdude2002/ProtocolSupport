package protocolsupport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import protocolsupport.commands.CommandHandler;
import protocolsupport.injector.NettyInjector;
import protocolsupport.injector.ServerInjector;
import protocolsupport.listeners.PlayerListener;

public class ProtocolSupport extends JavaPlugin {

	@Override
	public void onLoad() {
		try {
			NettyInjector.inject();
			ServerInjector.inject();
		} catch (Throwable e) {
			e.printStackTrace();
			Bukkit.shutdown();
		}
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		getCommand("protocolsupport").setExecutor(new CommandHandler());
	}

	@Override
	public void onDisable() {
		Bukkit.shutdown();
	}

}
