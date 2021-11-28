package net.dev.art.core.menuframework.system.event

import net.dev.art.core.libs.systems.events.EventBase
import net.dev.art.core.menuframework.system.menu.IMenu

open class MenuEvent(open val menu: IMenu) : EventBase(){
}