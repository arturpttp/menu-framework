package net.dev.art.core.menuframework.types.selector

import net.dev.art.core.menuframework.system.event.MenuItemClickEvent
import net.dev.art.core.menuframework.system.item.MenuItem
import net.dev.art.core.menuframework.types.pagination.Pagination
import org.bukkit.inventory.ItemStack

abstract class PaginatedSelector<T>(
    override var title: String,
) : Pagination<T>(title, false, ""), Selector<T> {
    override fun onClickItem(event: MenuItemClickEvent) {
        val item = getByItem(event.item)
        if (item == null) {
            event.player.sendMessage("§cAn error has occurred, send a message to §cSupport")
            return
        }
        onResult(event, item)
        onResult(item)
    }
}