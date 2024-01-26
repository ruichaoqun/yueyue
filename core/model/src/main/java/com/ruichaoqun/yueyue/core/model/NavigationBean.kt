package com.ruichaoqun.yueyue.core.model

data class NavigationBean(
    var cid:Int,
    var name:String,
    var articles:List<NavigationChildBean>
)

data class NavigationChildBean(
    var title:String,
    var link:String
)
