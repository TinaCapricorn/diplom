package pages

import androidx.test.uiautomator.*

const val MODEL_PACKAGE = "ru.iteco.fmhandroid"

const val TIMEOUT = 5000L

open class Page (
    protected val device: UiDevice
) {
    /**
     * Полуичить элемент с [id]
     */
    protected fun getElementById(id: String): UiObject2? {
        val resId = MODEL_PACKAGE.plus(":id/").plus(id)
        device.wait(Until.hasObject(By.res(resId)), TIMEOUT)
        if (device.findObject(By.res(resId)) !is UiObject2) {
            scrollToById(resId)
        }
        return device.findObject(By.res(resId))

    }

    /**
     * Полуичить элемент с [id] внутри [parent]
     */
    protected fun getElementById(id: String, parent: UiObject2?): UiObject2? {
        val resId = MODEL_PACKAGE.plus(":id/").plus(id)
        device.wait(Until.hasObject(By.res(resId)), TIMEOUT)
        if (parent?.findObject(By.res(resId)) !is UiObject2) {
            scrollToById(resId)
        }
        return parent?.findObject(By.res(resId))
    }

    /**
     * Полуичить элемент содержащий [text]
     */
    protected fun getElementByText(text: String): UiObject2? {
        device.wait(Until.hasObject(By.text(text)), TIMEOUT)
        if (device.findObject(By.text(text)) !is UiObject2) {
            scrollToByText(text)
        }
        return device.findObject(By.text(text))
    }


    /**
     * Скролл к блоку содержащему [text]
     */
    private fun scrollToByText(text: String) {
        val appViews = UiScrollable(UiSelector().scrollable(true))
        appViews.scrollIntoView(UiSelector().text(text))
    }

    /**
     * Скролл к блоку с [id]
     */
    private fun scrollToById(id: String) {
        val appViews = UiScrollable(UiSelector().scrollable(true))
        appViews.scrollIntoView(UiSelector().resourceId(id))
    }

}