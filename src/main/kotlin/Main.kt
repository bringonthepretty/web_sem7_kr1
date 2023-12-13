import com.wah.kr1.view.View

fun main() {
    val session = View()
    var authorized = false

    while (!authorized) {
        authorized = session.login()
    }

    session.mainPage()
}
