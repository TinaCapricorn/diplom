package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class MainPage(device: UiDevice) : Page(device) {

    private val mainPageSelector = "main_swipe_refresh";
    private val menuButtonSelector = "main_menu_image_button"
    private val menuItemClaimsText = "Claims"
    private val menuItemNewsText = "News"
    private val missionButtonSelector = "our_mission_image_button"
    private val toggleMaterialButton = "expand_material_button"
    private val mainPageNewsContainer = "container_list_news_include_on_fragment_main"
    private val mainPageClaimsContainer = "container_list_claim_include_on_fragment_main"
    private val allNewsButton = "all_news_text_view"
    private val allClaimsButton = "all_claims_text_view"
    private val newsItemArrowButton = "view_news_item_image_view"
    private val claimItemArrowButton = "claim_bottom_divider_image_view"
    private val newsItemTitleSelector = "news_item_title_text_view"
    private val claimItemTitleSelector = "title_material_text_view"
    private val claimItemDetailsSelector = "plan_date_material_text_view"
    private val newsItemTextSelector = "news_item_description_text_view"
    private val addNewClaimButton = "add_new_claim_material_button"


    fun onPage() {
        step("Проверить переход на главную страницу приложения")
        assertEquals(true, getElementById(mainPageSelector) is UiObject2)
    }

    fun clickMainMenu() : MainPage {
        step("Нажать кнопку главного меню")
        getElementById(menuButtonSelector)?.click()
        return this
    }

    fun clickClaimsButton() : ClaimsPage {
        step("Нажать пункт главного меню 'Заявки'")
        getElementByText(menuItemClaimsText)?.click()
        return ClaimsPage(device)
    }

    fun clickNewsButton() : NewsPage {
        step("Нажать пункт главного меню 'Новости'")
        getElementByText(menuItemNewsText)?.click()
        return NewsPage(device)
    }

    fun clickMissionButton() : MissionPage {
        step("Нажать кнопку перехода на страницу 'Миссия'")
        getElementById(missionButtonSelector)?.click()
        return MissionPage(device)
    }

    fun toggleNews() : MainPage {
        step("Нажать кнопку свернуть/развернуть блок 'Новости'")
        val news = getElementById(mainPageNewsContainer)
        val button = getElementById(toggleMaterialButton, news)
        button?.click();
        return this
    }

    fun isNewsVisible(isVisible: Boolean) : MainPage {
        step("Проверить видимость блока 'Новости'")
        assertEquals(isVisible, getElementById(allNewsButton) is UiObject2)
        return this
    }

    fun toggleClaims() : MainPage {
        step("Нажать кнопку свернуть/развернуть блок 'Заявки'")
        val claims = getElementById(mainPageClaimsContainer)
        val button = getElementById(toggleMaterialButton, claims)
        button?.click();
        return this
    }

    fun isClaimsVisible(isVisible: Boolean) : MainPage {
        step("Проверить видимость блока 'Заявки'")
        assertEquals(isVisible, getElementById(allClaimsButton) is UiObject2)
        return this
    }

    fun clickAllNews() : NewsPage {
        step("Нажать кнопку 'Все новости'")
        getElementById(allNewsButton)?.click()
        return NewsPage(device);
    }

    fun clickAllClaims() : ClaimsPage {
        step("Нажать кнопку 'Все заявки'")
        getElementById(allClaimsButton)?.click()
        return ClaimsPage(device);
    }

    fun clickNewsItemArrow() : MainPage {
        step("Нажать кнопку развернуть на отдельной новости")
        getElementById(newsItemArrowButton)?.click()
        return this
    }

    fun clickNewsItemTitle() : MainPage {
        step("Нажать на заголовок отдельной новости")
        getElementById(newsItemTitleSelector)?.click()
        return this
    }

    fun newsItemTextIsVisible(isVisible: Boolean) : MainPage {
        step("Проверить видимость описания новости")
        assertEquals(isVisible, getElementById(newsItemTextSelector) is UiObject2)
        return this
    }

    fun clickClaimItemArrow() : ClaimsItemPage {
        step("Нажать кнопку развернуть отдельной заявки")
        getElementById(claimItemArrowButton)?.click()
        return ClaimsItemPage(device)
    }

    fun clickClaimItemTitle() : ClaimsItemPage {
        step("Нажать на заголовок отдельной заявки")
        getElementById(claimItemTitleSelector)?.click()
        return ClaimsItemPage(device)
    }

    fun clickClaimItemDetails() : ClaimsItemPage {
        step("Нажать на описание отдельной заявки")
        getElementById(claimItemDetailsSelector)?.click()
        return ClaimsItemPage(device)
    }

    fun clickAddNewClaim() : ClaimsEditPage {
        step("Нажать кнопку добавления новой заявки")
        getElementById(addNewClaimButton)?.click()
        return ClaimsEditPage(device)
    }
}