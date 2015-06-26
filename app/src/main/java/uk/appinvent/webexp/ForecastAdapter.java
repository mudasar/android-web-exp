package uk.appinvent.webexp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mudasar on 26/06/2015.
 */
public class ForecastAdapter extends BaseAdapter {


    Context context;
    List<Forecast> forecastList;

    public ForecastAdapter(Context context, List<Forecast> list) {
        this.context = context;
        forecastList = list;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        if (forecastList != null){
            return forecastList.size();
        }
        return 0;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        if (forecastList != null){
            return forecastList.get(position);
        }
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.forecast_view_item,parent,false);
        TextView weatherView = (TextView) rowView.findViewById(R.id.forecast_text_view);
        TextView detailView = (TextView) rowView.findViewById(R.id.forecast_detail_text_view);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.weather_image);

        Forecast forecast = forecastList.get(position);

        weatherView.setText(forecast.getForecast());
        detailView.setText(forecast.description);
        switch (forecast.imageName){
            case "coudy":
                    imageView.setImageResource(R.drawable.weather_clouds);
                break;
            case "shiny":
                imageView.setImageResource(R.drawable.weather_clear);
                break;
            case "rainy":
                imageView.setImageResource(R.drawable.weather_showers_day);
                break;
            case "snow":
                imageView.setImageResource(R.drawable.weather_hail);
                break;
            default:
                imageView.setImageResource(R.drawable.weather_clouds_night);
                break;
        }

        return rowView;
    }
}
