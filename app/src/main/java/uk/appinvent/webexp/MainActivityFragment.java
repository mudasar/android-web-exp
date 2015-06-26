package uk.appinvent.webexp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private ListView forecastListView;
    private List<Forecast> forecastList ;
    private ForecastAdapter forecastAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


      //  tweetListView = (ListView) container.findViewById(R.id.forecase_list_view);


        LoadForecastDataTaskAsync loadForecastDataTaskAsync = new LoadForecastDataTaskAsync();
        AsyncTask<String, String, String> task = loadForecastDataTaskAsync.execute("http://api.openweathermap.org/data/2.5/forecast/daily?q=london,uk&mode=json&cnt=7&units=metric");

        String data = "";

        try {
             data = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.e("data",data);

        forecastList = new ArrayList<Forecast>();

        for(int i=0; i < 100; i++){
            Forecast forecast = new Forecast();
            forecast.imageName = "shiny";
            forecast.forecast = "min 15 / max 25 shiny " + i;
            forecast.description = "clear shiny day" + i;

            forecastList.add(forecast);
        }
        final Context context = rootView.getContext();
        forecastAdapter = new ForecastAdapter(context,forecastList);
        forecastListView = (ListView) rootView.findViewById(R.id.forecase_list_view);
        forecastListView.setAdapter(forecastAdapter);
        forecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Forecast forecast = (Forecast) forecastAdapter.getItem(position);

                Toast.makeText(context, forecast.forecast, Toast.LENGTH_LONG).show();
            }
        });

    return rootView;
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
