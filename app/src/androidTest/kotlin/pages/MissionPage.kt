package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class MissionPage(device: UiDevice) : Page(device) {

    private val missionPageTitleText = "Love is all";

    fun onPage() {
        step("Проверить переход на страницу миссии")
        assertEquals(true, getElementByText(missionPageTitleText) is UiObject2)
    }
}