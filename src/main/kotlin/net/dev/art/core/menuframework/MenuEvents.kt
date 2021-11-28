package net.dev.art.core.menuframework

import net.dev.art.core.libs.systems.events.Events
import net.dev.art.core.menuframework.system.event.MenuCloseEvent
import net.dev.art.core.menuframework.system.menu.MenuHolder
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class MenuEvents() : Events() {

    @EventHandler
    fun command(event: PlayerCommandPreprocessEvent) {
        val command = "${event.message} ".split(" ")[0].replaceFirst("/", "");
        MenuFramework.menus.firstOrNull { it.command != null && it.command.equals(command, true) }?.open(event.player)
    }

    @EventHandler
    fun click(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        if (event.inventory.holder is MenuHolder) {
            event.isCancelled = true
            val holder = event.inventory.holder as MenuHolder
            holder.menu.onInventoryClick(event)
        }
    }

    @EventHandler
    fun close(event: InventoryCloseEvent) {
        if (event.player !is Player) return
        if (event.inventory.holder is MenuHolder) {
            val holder = event.inventory.holder as MenuHolder
            MenuFramework.instance.pluginManager.callEvent(MenuCloseEvent(holder.menu, event))
        }
    }

}