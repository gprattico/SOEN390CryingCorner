
package nitezh.ministock.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.androidplot.ui.Anchor;
import com.androidplot.ui.HorizontalPositioning;
import com.androidplot.ui.Size;
import com.androidplot.ui.SizeMode;
import com.androidplot.ui.VerticalPositioning;
import com.androidplot.ui.widget.Widget;
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.StepMode;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import nitezh.ministock.R;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;


public class GraphTools {

    public static void drawGraph(Context context,String symbol, Integer appWidgetId, String beginning){
        XYPlot plot = new XYPlot(context, symbol);
        final int h = (int) context.getResources().getDimension(R.dimen.sample_widget_height);
        final int w = (int) context.getResources().getDimension(R.dimen.sample_widget_width);

        plot.getGraph().setMargins(0, 0, 20 , 30);
        plot.getGraph().setPadding(50, 0, 0, 60);



        plot.getGraph().position(0, HorizontalPositioning.ABSOLUTE_FROM_CENTER, 0,
                VerticalPositioning.ABSOLUTE_FROM_CENTER, Anchor.CENTER);
        
        plot.getGraph().setSize(new Size(PixelUtils.dpToPix(50), SizeMode.FILL,
                PixelUtils.dpToPix(50), SizeMode.FILL));

        plot.getLayoutManager().moveToTop(plot.getTitle());

        plot.getGraph().setLineLabelEdges(XYGraphWidget.Edge.LEFT, XYGraphWidget.Edge.BOTTOM);
        plot.getGraph().getLineLabelInsets().setLeft(PixelUtils.dpToPix(10));
        plot.getGraph().getLineLabelInsets().setBottom(PixelUtils.dpToPix(-5));
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.LEFT).getPaint().setColor(Color.RED);
        plot.getGraph().getGridInsets().setTop(PixelUtils.dpToPix(12));
        plot.getGraph().getGridInsets().setRight(PixelUtils.dpToPix(12));
        plot.getGraph().getGridInsets().setLeft(PixelUtils.dpToPix(36));
        plot.getGraph().getGridInsets().setBottom(PixelUtils.dpToPix(16));

        plot.measure(w, h);
        plot.layout(0, 0, w, h);

        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();



        List<Date> calendars = new ArrayList();
        List<BigDecimal> close = new ArrayList();
        try {

            Stock stock;
            switch(beginning) {
                case "past_six_months":
                    from.add(Calendar.MONTH, -6);
                    stock = YahooFinance.get(symbol, from, to, Interval.MONTHLY);
                    break;
                case "past_month":
                    from.add(Calendar.MONTH, -1);
                    stock = YahooFinance.get(symbol, from, to, Interval.DAILY);
                    break;
                case "past_two_weeks":
                    from.add(Calendar.WEEK_OF_MONTH, -2);
                    stock = YahooFinance.get(symbol, from, to, Interval.DAILY);
                    break;
                case "past_week":
                    from.add(Calendar.WEEK_OF_MONTH, -1);
                    stock = YahooFinance.get(symbol, from, to, Interval.DAILY);
                    break;
                default:
                    stock = YahooFinance.get(symbol, true);


            }

            List<HistoricalQuote> historicalQuotes = stock.getHistory();

            for (HistoricalQuote historicalQuote : historicalQuotes) {
                if (historicalQuote.getClose() == null || historicalQuote.getDate() == null) {
                    continue;
                }
                close.add(historicalQuote.getClose());
                calendars.add(historicalQuote.getDate().getTime());

            }
            
        }catch (IOException ignored){

        }

        

        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
                close,          // SimpleXYSeries takes a List so turn our array into a List
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Series1");                             // Set the display title of the series

        

        // Create a formatter to use for drawing a series using LineAndPointRenderer:
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),                   // line color
                Color.rgb(0, 100, 0),                   // point color
                null, null);                            // fill color (none)

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        
        final Date[] stockDates = calendars.toArray(new Date[calendars.size()]);

        plot.setDomainStep(StepMode.SUBDIVIDE, stockDates.length);
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).getPaint().setTextSize(20);
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.LEFT).getPaint().setTextSize(35);
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).
                setFormat(new Format() {

                    // create a simple date format that draws on the year portion of our timestamp.
                    // see http://download.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html
                    // for a full description of SimpleDateFormat.


                    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                    @Override
                    public StringBuffer format(Object obj,
                                               @NonNull StringBuffer toAppendTo,
                                               @NonNull FieldPosition pos) {

                        // this rounding is necessary to avoid precision loss when converting from
                        // double back to int:

                        int yearIndex = (int) Math.round(((Number) obj).doubleValue());
                       
                        return dateFormat.format(stockDates[yearIndex], toAppendTo, pos);
                    }

                    @Override
                    public Object parseObject(String source, @NonNull ParsePosition pos) {
                        return null;

                    }
                });

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setRotation(-45);
        // reduce the number of range labels
        plot.setLinesPerRangeLabel(3);
        plot.setLinesPerDomainLabel(2);


        plot.setDomainStep(StepMode.SUBDIVIDE, stockDates.length);
        // hide the legend:
        plot.getLegend().setVisible(false);

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_4x4_graph);

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);


        plot.draw(new Canvas(bitmap));
        rv.setImageViewBitmap(R.id.imgView, bitmap);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }
}
