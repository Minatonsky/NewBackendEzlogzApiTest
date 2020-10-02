package singleTest.logbookTest;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static libs.DataForTests.DrivingTime8;
import static libs.Utils.dateWithMinusDay;

public class RestBreak extends ParentTest {
    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");
    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();

    public RestBreak() throws IOException {
    }

    @Test
    public void restBreak() throws SQLException {

        int cycleType = 0;
        int cargoType = 0;

        String userId = utilsForDB.getUserIdByEmail(login);

        logsPage.cleanStatusesAndViolation(userId);
        logsPage.setCycle(userId, cycleType);
        utilsForDB.setCargoTypeId(userId, cargoType);

        loginPage.userValidLogIn(login, pass);
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("00:00:00 AM", "01:00:00 AM", "Dr");
        logsPage.addStatus("01:15:00 AM", "01:30:00 AM", "On");
        logsPage.addStatus("01:30:00 AM", "02:45:00 AM", "Dr");
        logsPage.addStatus("02:45:00 AM", "03:00:00 AM", "On");
        logsPage.addLastStatus("03:00:00 AM", "09:45:00 AM", "Dr");

        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime8), false);

        //        add last minute for get violation
        dashboardPage.goToLogsPage();
        logsPage.clickOnRowDay(dateWithMinusDay(3));
        logsPage.clickOnCorrectionButton();
        logsPage.clickOnInsertStatusButton();
        logsPage.addStatus("09:45:00 AM", "09:46:00 AM", "Dr");
        logsPage.clickOnSaveInfoButton();
        logsPage.closeCorrectionSavePopUp();
        checkAC("Break Violation(8) failed", logsPage.checkAlertsId(userId, dateWithMinusDay(3), DrivingTime8), logsPage.isRestBreakRequired(cycleType, cargoType));

    }
}
