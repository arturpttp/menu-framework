package net.dev.art.core.menuframework.system.event

import net.dev.art.core.libs.systems.events.EventBase
import net.dev.art.core.menuframework.system.item.MenuItem
import net.dev.art.core.menuframework.system.menu.IMenu
import net.dev.art.core.menuframework.system.menu.MenuHolder
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.inventory.InventoryClickEvent

class MenuItemClickEvent(
    override val menu: IMenu,
    var player: Player,
    var slot: Int,
    var item: MenuItem,
    var raw: InventoryClickEvent
) : MenuEvent(menu), Cancellable {

    var isCanceled = true

    fun closeMenuToPlayer() {
        menu.close(player)
    }

    override fun isCancelled(): Boolean {
        return isCanceled
    }

    override fun setCancelled(b: Boolean) {
        raw.isCancelled = b
        isCanceled = b
    }
}