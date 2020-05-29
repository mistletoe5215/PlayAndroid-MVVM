package com.mistletoe15.playandroid_mvvm.bean

/**
 * 首页文章 item 内容实体bean
 * Created by Mistletoe on 2020/5/29
 **/
data class HomeArticleBean(
    var apkLink: String = "",
    var audit: Int = 1,
    var author: String = "",
    var canEdit: Boolean = false,
    var chapterId: Int = 0,
    var chapterName: String = "",
    var collect: Boolean = false,
    var courseId: Int = 13,
    var desc: String = "",
    var descMd: String = "",
    var envelopePic: String = "",
    var fresh: Boolean = false,
    var id: Int = 0,
    var link: String = "",
    var niceDate: String = "",
    var niceShareDate: String = "",
    var origin: String = "",
    var prefix: String = "",
    var projectLink: String = "",
    var publishTime: Int = 0,
    var selfVisible: Int = 0,
    var shareDate: Int = 0,
    var shareUser: String = "",
    var superChapterId: Int = 0,
    var superChapterName: String = "",
    var tags: Array<String>,
    var title: String = "",
    var type: Int = 0,
    var userId: Int = 0,
    var visible: Int = 0,
    var zan: Int = 0
)