package com.ruichaoqun.yueyue.core.model

data class SystemDataBean(
    var id:Int,
    var name:String,
    var children:MutableList<SystemDataChildBean>
):SimpleSelect() {
    override fun toString(): String {
        return name
    }
}

data class SystemDataChildBean(
    var id:Int,
    var name:String
):SimpleSelect() {
    override fun toString(): String {
        return name
    }
}
