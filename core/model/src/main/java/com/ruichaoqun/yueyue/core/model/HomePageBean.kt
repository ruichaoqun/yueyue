package com.ruichaoqun.yueyue.core.model

data class HomePageBean(
    var curPage:Int?,
    var offset:Int?,
    var over:Boolean?,
    var pageCount:Int?,
    var size:Int?,
    var total:Int?,
    var datas:List<HomePageItemBean>
)

data class HomePageItemBean(
    var apkLink:String?,
    var audit:Int?,
    var author:String?,
    var chapterId:Int?,
    var chapterName:String?,
    var collect:Boolean?,
    var courseId:Int?,
    var desc:String?,
    var envelopePic:String?,
    var fresh:Boolean?,
    var id:Int?,
    var link:String?,
    var niceDate:String?,
    var niceShareDate:String?,
    var origin:String?,
    var prefix:String?,
    var projectLink:String?,
    var publishTime:Long?,
    var selfVisible:Int?,
    var shareDate:Long?,
    var shareUser:String?,
    var superChapterId:Int?,
    var superChapterName:String?,
    var tags:List<HomePageTagItemBean>?,
    var title:String?,
    var type:Int?,
    var userId:Int?,
    var visible:Int?,
    var zan:Int?,
)

data class HomePageTagItemBean(
    var name:String?,
    var url:String?
)

