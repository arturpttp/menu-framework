package net.dev.art.core.menuframework.types.selector

import net.dev.art.core.menuframework.system.event.MenuItemClickEvent
import org.bukkit.inventory.ItemStack

interface Selector<T> {

    fun onResult(event: MenuItemClickEvent, t: T)

    fun getByItem(item: ItemStack): T?
    fun onResult(result: T)
    fun onResult(result: Array<T>)

}