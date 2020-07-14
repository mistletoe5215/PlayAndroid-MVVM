package com.mistletoe15.playandroid_mvvm.data.bean

/**
 * Created by Mistletoe on 2020/7/14
 **/
data class HomeTopRankModel (
    var apkLink:String="",
    var audit:Int = 1,
    var author:String = "",
    var canEdit:Boolean =false,
    var chapterId:Int  = -1,
    var chapterName:String ="",
    var collect:Boolean =false,
    var courseId:Int  =13,
    var desc:String ="",
    var descMd:String="",
    var envelopePic:String="",
    var fresh:Boolean = true,
    var id:Int =12554,
    var link:String="",
    var niceDate:String="",
    var niceShareDate:String="",
    var origin:String="",
    var prefix:String="",
    var projectLink:String="",
    var publishTime:Long = 0,
    var realSuperChapterId:Int= -1,
    var selfVisible:Int=0,
    var shareDate:Long = 0,
    var shareUser:String = "",
    var superChapterId:Int= -1,
    var superChapterName:String ="",
    var tags:Array<Any>,
    var title:String = "",
    var type:Int = -1,
    var userId:Int = -1,
    var visible:Int = 1,
    var zan:Int = 0
)
