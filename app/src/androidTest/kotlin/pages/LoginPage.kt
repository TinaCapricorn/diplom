package pages

import androidx.test.uiautomator.*

class LoginPage(device: UiDevice) : Page(device) {

    private val loginPageTitle = "Authorization"
    private val loginSelector = "Login"
    private val passwordSelector = "Password"
    private val loginButtonSelector = "enter_button"

    fun onPage() : Boolean {
        return getElementByText(loginPageTitle) is UiObject2
    }

    fun setLogin(login: String) : LoginPage {
        getElementByText(loginSelector)?.text = login
        return this
    }

    fun setPassword(password: String) : LoginPage {
        getElementByText(passwordSelector)?.text = password
        return this
    }

    fun clickLogin() : MainPage {
        getElementById(loginButtonSelector)?.click()
        return MainPage(this.device)
    }
}