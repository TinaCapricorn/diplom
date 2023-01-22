package tests

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import pages.LoginPage

const val MODEL_PACKAGE = "ru.iteco.fmhandroid"

const val TIMEOUT = 5000L

open class TestBase {

    /**
     * Девайс
     */
    protected lateinit var device: UiDevice

    /**
     * Ожидание открытия приложения [packageName]
     */
    private fun waitForPackage(packageName: String) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), TIMEOUT)
    }

    /**
     * проверка необходимости авторизации и авторизация в приложение если нужно
     */
    private fun checkLogin() {
        val loginPage = LoginPage(device)
        if (loginPage.onPage()) {
            loginPage.setLogin("login2")
                .setPassword("password2")
                .clickLogin()
                .onPage()
        }
    }

    /**
     * загрузка приложения
     */
    protected fun loadPackage() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage)), TIMEOUT)
        waitForPackage(MODEL_PACKAGE)
        checkLogin()
    }

}