package dev.olegthelilfix.telegram.dictionary.models

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "UserWords")
class UserWords(): Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

    @Column(name="userId")
    var userId: Long = 0

    @Column(name="word")
    lateinit var word: String

    constructor(userId: Long, word: String) : this() {
        this.userId = userId
        this.word = word
    }
}