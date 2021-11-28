package net.dev.art.core.menuframework.system.item

import net.dev.art.core.libs.utils.ItemBuilder
import net.dev.art.core.menuframework.system.event.MenuItemClickEvent
import net.dev.art.core.menuframework.system.event.MenuItemClickEventExecutor
import net.dev.art.core.menuframework.system.menu.IMenu
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

class MenuItem(
    private val itemStack: ItemStack,
    public val consumer: Consumer<MenuItemClickEvent>? = null,
    var slot: Int = -1
) : ItemBuilder(itemStack) {

    lateinit var event: MenuItemClickEvent
    lateinit var menu: IMenu

    init {
    }

    fun complete(menu: IMenu): IMenu {
        this.menu = menu
        if (slot == -1) {
            menu.inventory.addItem(this)
            slot = menu.inventory.first(this)
        } else menu.inventory.setItem(slot, this)
        return menu
    }

    fun execute(menuItemClickEvent: MenuItemClickEvent) {
        this.event = menuItemClickEvent
    }

    fun onInventoryClick(ev: MenuItemClickEvent) {
        consumer?.accept(ev)
        this.event = ev
    }

}