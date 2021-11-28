package net.dev.art.core.menuframework

import net.dev.art.core.Test
import net.dev.art.core.libs.utils.ItemBuilder
import net.dev.art.core.menuframework.system.item.MenuItem
import net.dev.art.core.menuframework.system.menu.IMenu
import net.dev.art.core.menuframework.system.menu.MenuRows
import net.dev.art.core.menuframework.types.SimpleMenu
import org.bukkit.Material
import org.bukkit.entity.Player

/*Created in 09:55 at 25/11/2021 in Programming dreams By artur*/
class TestMenu : SimpleMenu("Test menu", MenuRows.THREE.rows) {


    init {
        item(1, MenuItem(ItemBuilder(Material.DIAMOND)))
        item(7, MenuItem(ItemBuilder(Material.BARRIER).name("§cClose"), {
            it.player.closeInventory()
        }))
    }

    override fun onOpen(menu: IMenu, player: Player) {
        //When the menu open to player
    }

}/*

//to access it in a different class
val testMenu: TestMenu = TestMenu()
testMenu.open(player)*/