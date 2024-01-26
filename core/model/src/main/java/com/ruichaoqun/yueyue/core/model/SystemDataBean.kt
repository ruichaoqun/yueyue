package com.ruichaoqun.yueyue.core.model

data class SystemDataBean(
    var id:Int,
    var name:String,
    var children:List<SystemDataChildBean>
)

data class SystemDataChildBean(
    var id:Int,
    var name:String
)
