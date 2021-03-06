package com.example.noticiasconcursos.data.retrofit


// related to
// https://jobconcursosbr.com/wp-json/wp/v2/posts?_fields=title,content.rendered,_links.self


class PojoData : ArrayList<TextDataItem>()

data class TextDataItem(
    val content: Content,
    var title: Title,
    var guid: Guid,
    var date: String
)

data class Guid(
    val rendered: String
)

data class Content(
    val rendered: String
)

data class Title(
    val rendered: String
)

data class Self(
    val href: String
)

class ImgData : ArrayList<ImgDataItem>()

data class ImgDataItem(
    val media_details: MediaDetails
)

data class MediaDetails(
    val sizes: Sizes,
)

data class Sizes(
    val medium: Medium,
    val thumbnail: Thumbnail
)

data class Medium(
    val source_url: String,
)

data class Thumbnail(
    val source_url: String,
)

data class FetchedData(
    var myTitles: ArrayList<String>,
    var myDescription: ArrayList<String>,
    var myImages: ArrayList<String>,
    var myLinks: ArrayList<String>,
    var myDate: ArrayList<String>
)



