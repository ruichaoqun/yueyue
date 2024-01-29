package com.ruichaoqun.yueyue.core.model

data class SystemDataBean(
    var id:Int,
    var name:String,
    var children:List<SystemDataChildBean>
) {
    override fun toString(): String {
        return name
    }
}

data class SystemDataChildBean(
    var id:Int,
    var name:String
) {
    override fun toString(): String {
        return name
    }
}
