package com.ruichaoqun.yueyue.core.model

data class ArticleItemBean(
    var title:String,
    var id:Int,
    var chapterName:String,
    var superChapterName:String,
    var collect:Boolean,
    var link:String,
    var publishTime:Long,
    var shareDate:String,
    var author:String,
    var shareUser:String
)
