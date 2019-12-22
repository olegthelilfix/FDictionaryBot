package dev.olegthelilfix.telegram.dictionary.models

import org.telegram.telegrambots.api.objects.User
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "ServiceUser")
public class ServiceUser(): Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(name="telegramId")
    var telegramId: Int = 0

    @Column(name="chatId")
    var chatId: Long = 0

    @Column(name="firstName")
    lateinit var firstName: String

    @Column(name="lastName")
    lateinit var lastName: String

    @Column(name="userName")
    lateinit var userName: String

    @Column(name="languageCode")
    lateinit var languageCode: String

    constructor(user: User, chatId: Long) : this() {
        telegramId = user.id
        this.chatId = chatId
        firstName = user.firstName
        lastName = user.lastName
        userName = user.userName
        languageCode = user.languageCode
    }

    override fun toString(): String {
        return "ServiceUser(id=$id, telegramId=$telegramId, chatId=$chatId, firstName='$firstName', lastName='$lastName', userName='$userName', languageCode='$languageCode')"
    }


}