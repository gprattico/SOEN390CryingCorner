/*
 The MIT License

 Copyright (c) 2013 Nitesh Patel http://niteshpatel.github.io/ministocks

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */

package nitezh.ministock.mocks;

import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nitezh.ministock.Storage;
import nitezh.ministock.domain.Widget;


public class MockWidget implements Widget {
    @Override
    public Storage getStorage() {
        return new MockStorage();
    }

    @Override
    public void setWidgetPreferencesFromJson(JSONObject jsonPrefs) {
    }

    @Override
    public JSONObject getWidgetPreferencesAsJson() {
        return new JSONObject();
    }

    @Override
    public boolean isNarrow() {
        return false;
    }

    @Override
    public String getStock(int i) {
        return null;
    }

    @Override
    public void setStock(int i,String key) {

    }

    @Override
    public int getPreviousView() {
        return 0;
    }

    @Override
    public void setView(int view) {

    }

    @Override
    public List<String> getSymbols() {
        return null;
    }

    @Override
    public int getSymbolCount() {
        return 0;
    }

    @Override
    public String getBackgroundStyle() {
        return null;
    }


    @Override
    public boolean getHideSuffix() {
        return false;
    }

    /*@Override
    public boolean getTextStyle() {
        return false;
    }*/

    @Override
    public boolean useBold() {
        return false;
    }

    @Override
    public boolean useUnderlined() {
        return false;
    }

    @Override
    public boolean useItalic() {
        return false;
    }

    @Override
    public boolean getColorsOnPrices() {
        return false;
    }

    @Override
    public String getFooterVisibility() {
        return null;
    }

    @Override
    public String getHeaderVisibility() {
        return null;
    }

    @Override
    public String getFontSize() {
        return null;
    }

    @Override
    public int getFooterColor() {
        return 0;
    }

    @Override
    public int getStockNameColor() {
        return 0;
    }

    @Override
    public int getStockPriceColor() {
        return 0;
    }

    @Override
    public int getPriceIncreaseColor() {
        return 0;
    }

    @Override
    public int getPriceDecreaseColor() {
        return 0;
    }

    @Override
    public int getHighAlertColor() {
        return 0;
    }

    @Override
    public int getLowAlertColor() {
        return 0;
    }

    @Override
    public boolean showShortTime() {
        return false;
    }

    @Override
    public boolean hasDailyChangeView() {
        return false;
    }

    @Override
    public boolean hasTotalPercentView() {
        return false;
    }

    @Override
    public boolean hasDailyPercentView() {
        return false;
    }

    @Override
    public boolean hasTotalChangeView() {
        return false;
    }

    @Override
    public boolean hasTotalChangeAerView() {
        return false;
    }

    @Override
    public boolean hasDailyPlChangeView() {
        return false;
    }

    @Override
    public boolean hasDailyPlPercentView() {
        return false;
    }

    @Override
    public boolean hasTotalPlPercentView() {
        return false;
    }

    @Override
    public boolean hasTotalPlChangeView() {
        return false;
    }

    @Override
    public boolean hasTotalPlPercentAerView() {
        return false;
    }

    @Override
    public boolean alwaysUseShortName() {
        return false;
    }

    @Override
    public boolean shouldUpdateOnRightTouch() {
        return false;
    }

    @Override
    public boolean updateOnWifi() {
        return true;
    }

    @Override
    public boolean isUsingWifi() {
        return true;
    }

    @Override
    public void enablePercentChangeView() {
    }

    @Override
    public void enableDailyChangeView() {

    }

    @Override
    public void setStock1() {
    }

    @Override
    public void setStock1Summary() {
    }

    @Override
    public void save() {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void setSize(int size) {
    }

    @Override
    public int getHeaderColor() {
        return 0;
    }

    @Override
    public String getFont() {
        return null;
    }


    @Override
    public void sendNotification(Context context, String title, String text, int notificationId) {

    }


    @Override
    public  List<String> checkSPStock(){
        List <String> spListInWidget = new ArrayList<String>();


        return spListInWidget;
    }
    @Override
    public boolean updateOnCurrency() {
        return false;
    }
    @Override
    public String getCurrencyChange() {
        return null;

    }

    @Override
    public String historicalData() {
        return null;

    }
}
