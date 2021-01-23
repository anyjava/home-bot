package dev.anyjava.bot.adapter.adapter;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class FooTest {

    @Test
    void test() {

        AccountView accountView = new AccountView();
        accountView.click();

        log.info("accountView" + accountView);
    }
}


@ToString
class UiDetail {
    String stringVar;

    AccountView accountView;
}

@Slf4j
@ToString
class AccountView {
    int index;

    UiDetail uiDetail;

    public void click() {
        uiDetail = new UiDetail();
        uiDetail.stringVar = "UI DB";
        uiDetail.accountView = this; // self


        log.info("click");
    }
}
