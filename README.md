# Menu Framework
<img align="right" src="https://media.discordapp.net/attachments/517334221660880907/908178316572377168/ezgif-2-8ced1ea5b417.gif" height="170" width="170" title="a">

A menu / GUIs framework made in kotlin with **[Core](https://github.com/arturpttp/core)**, menu framework depends on it, you should have it in mind.
<br>
_Please see the **[Core](https://github.com/arturpttp/core)** for more information about minecraft plugins._
## Creating your first menu

To create your first menu, you have many ways, to simple menus / GUIs you could do it: 

**Simple Menu/GUIs Creation**

```kotlin
val menu = menu("Menu Test", MenuRows.FIVE, "openMenu") {
    addItem(MenuItem(ItemBuilder(Material.DIAMOND), {//this is the event
        it.isCanceled = false //Can pick up the item
        it.player.sendMessage("You clicked in a Diamond")
    }));
    onOpen()
}
menu.open(player);
```

**Menu/GUIs in separated classes**

```kotlin
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
}

//to access it in a different class
val testMenu: TestMenu = TestMenu()
testMenu.open(player)
``` 