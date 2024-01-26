package com.ruichaoqun.yueyue.core.model

data class ProjectBean(
    var collect:Boolean,
    var id:Int,
    var desc:String,
    var author:String,
    var envelopePic:String,
    var link:String,
    var niceDate:String,
    var niceShareDate:String,
    var projectLink:String,
    var publishTime:Long,
    var title:String
)