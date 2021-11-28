package net.dev.art.core.menuframework

import net.dev.art.core.libs.PluginCore
import net.dev.art.core.libs.utils.ItemBuilder
import net.dev.art.core.menuframework.system.item.MenuItem
import net.dev.art.core.menuframework.system.menu.IMenu
import net.dev.art.core.menuframework.system.menu.MenuRows
import net.dev.art.core.menuframework.types.SimpleMenu
import org.bukkit.Material

class MenuFramework : PluginCore(
    "Menu Framework",
    MenuFramework::class.java
) {

    companion object {
        lateinit var instance: MenuFramework
        val menus: MutableList<IMenu> = mutableListOf()
    }

    init {
        instance = this
    }

    override fun onEnable() {
 /*       val menu = menu("Menu Test", MenuRows.FIVE, "openMenu") {
            addItem(
                MenuItem(
                    ItemBuilder(Material.DIAMOND), {
                        it.isCanceled = false // Can pick up the item
                        it.player.sendMessage("You clicked in a Diamond")
                    }
                )
            )
        }*/
//        menu.open(player);
    }
}

fun menu(title: String, rows: MenuRows, command: String? = null): IMenu = SimpleMenu(title, rows.rows, command)
inline fun menu(title: String, rows: MenuRows, command: String? = null, menu: IMenu.() -> Unit): IMenu = SimpleMenu(title, rows.rows, command).apply(menu)
