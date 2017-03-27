package com.example.mypackage.parse;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    XMLGettersSetters data;
    ProgressDialog waitProgress;
    int count = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BackgroundTask().execute();
    }

    public class BackgroundTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (waitProgress != null) {
                waitProgress.dismiss();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewSaxData();
            if (waitProgress != null) {
                waitProgress.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                synchronized (this) {
                    saxParser();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    private void saxParser() {

        try {
            /**
             * Create a new instance of the SAX parser
             **/
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            SAXParser saxP = saxPF.newSAXParser();
            XMLReader xmlR = saxP.getXMLReader();

            // URL of the XML
            URL url = new URL("http://www.papademas.net/cd_catalog3.xml");

            /**
             * Create the Handler to handle each of the XML tags.
             **/
            XMLHandler myXMLHandler = new XMLHandler();
            xmlR.setContentHandler(myXMLHandler);
            xmlR.parse(new InputSource(url.openStream()));

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    private void viewSaxData()
    {
        /**
         * Get the view of the layout within the main layout, so that we can
         add TextViews.
         **/
        View scroll = findViewById(R.id.scroll);
        View layout = findViewById(R.id.layout);
        /**
         * Create TextView Arrays to add the retrieved data to.
         **/
        TextView title[];

        TextView artist[];

        TextView country[];

        TextView company[];

        TextView price[];

        TextView year[];

        TextView attr[]; //Sold out?

        data = XMLHandler.data;

        /**
         * Makes the TextView length the size of the TextView arrays by
         getting the size of the
         **/
        title = new TextView[data.getTitle().size()];
        artist = new TextView[data.getArtist().size()];
        country = new TextView[data.getCountry().size()];
        company = new TextView[data.getCompany().size()];
        price = new TextView[data.getPrice().size()];
        year = new TextView[data.getYear().size()];
        attr = new TextView[data.getAttribute().size()];

        /**
         * Run a for loop to set All the TextViews with text until
         * the size of the array is reached.
         *
         **/

        for (int i = 0; i < data.getTitle().size(); i++) {
            title[i] = new TextView(this);
            title[i].setText("Title = "+data.getTitle().get(i));

            artist[i] = new TextView(this);
            artist[i].setText("Artist = "+data.getArtist().get(i));

            country[i] = new TextView(this);
            country[i].setText("Country = "+data.getCountry().get(i));

            company[i] = new TextView(this);
            company[i].setText("Company = "+data.getCompany().get(i));

            price[i] = new TextView(this);
            price[i].setText("Price = "+data.getPrice().get(i));

            year[i] = new TextView(this);
            year[i].setText("Year = "+data.getYear().get(i));

            attr[i] = new TextView(this);
            attr[i].setText("Sold out? " + data.getAttribute().get(i) + "\n\n");

            title[i].setTextSize(18); //larger size of letter
            title[i].setTextColor(Color.BLACK);
            if(Float.parseFloat(data.getPrice().get(i)) > 10) {
                price[i].setTextColor(Color.BLUE);
            }
            attr[i].setTextColor(Color.RED);

            if(data.getAttribute().get(i).equals("yes")) { //to show only CD's that are marked "Sold out"
                ((ViewGroup) layout).addView(title[i]);
                ((ViewGroup) layout).addView(artist[i]);
                ((ViewGroup) layout).addView(country[i]);
                ((ViewGroup) layout).addView(price[i]);
                ((ViewGroup) layout).addView(year[i]);
                ((ViewGroup) layout).addView(attr[i]);
                count++;
            }
        }

        TextView tvCount = new TextView(this, null, 0);
        tvCount.setText("amount of CD’s that were sold out : " + String.valueOf(count));
        ((ViewGroup) layout).addView(tvCount);
        setContentView(scroll);

    }
}
