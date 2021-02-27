package pl.marek.core.domain

data class Note (val id: Int, val title: String, val body: String) {
    constructor(title: String, body: String) : this(-1, title, body)
}
