package edu.csulb.smartroot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An adapter that will generate a card for each garden. These cards will be displayed in a list
 * in GardenView.java.
 */
public class GardenHolder extends RecyclerView.Adapter<GardenHolder.ViewHolder>
    implements Toolbar.OnMenuItemClickListener{

    private ArrayList<Garden> gardens;

    /**
     * Constructor that will get a reference to an ArrayList of gardens to create each individual
     * card.
     * @param gardens An ArrayList of gardens.
     */
    public GardenHolder(ArrayList<Garden> gardens){
        this.gardens = gardens;

        // Adding empty gardens for testing purposes
        gardens.add(new Garden());
        gardens.add(new Garden());
        gardens.add(new Garden());
    }

    /**
     * An implementation of RecyclerView.Adapter. It will create the card view for each individual
     * garden from card_garden.xml.
     * @param parent The ViewGroup which the card view will be added to. In this case,
     *               from GardenView.java.
     * @param viewType The view type of the generated card.
     * @return A ViewHolder containing the garden card view.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_garden, parent, false);

         return new ViewHolder(v);
    }

    /**
     * An implementation of RecyclerView.Adapter. It will set up all of the TextView, Button, and
     * Overflow Menu.
     * @param holder The ViewHolder to set up the Views it contains. In this case, a garden card view.
     * @param position The position of the ViewHolder in the adapter.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Context context = holder.view.getContext();

        //***Set up overflow menu***//

        Toolbar toolbar = (Toolbar) holder.view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_overflow_garden);
        toolbar.setOnMenuItemClickListener(this);

        //***Set up text***//

        // TODO: Replace placeholders with actual data from garden ArrayList.
        // using position as the current index in the adapter.

        // Set garden name
        TextView tx = (TextView) holder.view.findViewById(R.id.name);
        tx.setText(context.getString(R.string.label_garden_name));

        // Set temperature
        tx = (TextView) holder.view.findViewById(R.id.temperature);
        tx.setText(context.getString(R.string.label_temperature, 85));

        // Set moisture
        tx = (TextView) holder.view.findViewById(R.id.moisture);
        tx.setText(context.getString(R.string.label_moisture, 35));

        // Set humidity
        tx = (TextView) holder.view.findViewById(R.id.humidity);
        tx.setText(context.getString(R.string.label_humidity, 50));

        // Set last updated
        tx = (TextView) holder.view.findViewById(R.id.updated);
        tx.setText(context.getString(R.string.label_updated, 2));

        //** Set up buttons***//

        // Set Update button
        Button button = (Button) holder.view.findViewById(R.id.button_update);
        button.setOnClickListener(new UpdateButton());

        // Set View History button
        button = (Button) holder.view.findViewById(R.id.button_history);
        button.setOnClickListener(new HistoryButton());

        // Set Water Now button
        button = (Button) holder.view.findViewById(R.id.button_water);
        button.setOnClickListener(new WaterButton());

        // Set Setup button
        button = (Button) holder.view.findViewById(R.id.button_setup);
        button.setOnClickListener(new SetupButton());
    }

    /**
     * An implementation of RecyclerView.Adapter. It will return the amount of garden card views
     * in the adapter.
     * @return The amount of garden card views in the adapter.
     */
    @Override
    public int getItemCount() {
        return gardens.size();
    }

    ///////////////
    // LISTENERS //
    ///////////////

    /**
     * Implementation of MenuItem.OnMenuItemClickListener. It will process the user's menu selections
     * and the appropriate actions.
     * @param menuItem The menu item that was selected by the user.
     * @return True, to consume the current click and prevent others from executing.
     */
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.menu_push) {
            // TODO: Implement dialog for push notification settings.
            System.out.println("Push Notification");
        }
        if(menuItem.getItemId() == R.id.menu_shutdown) {
            // TODO: Implement sending shut down message garden.
            System.out.println("Shut down");
        }
        return true;
    }

    //////////////////////
    // BUTTON LISTENERS //
    //////////////////////

    /**
     * A button listener for Update. This will update the sensor readings of the garden.
     */
    private class UpdateButton implements Button.OnClickListener {
        /**
         * An implementation of Button.OnClickListener. This will retrieve the current
         * sensor readings from the garden.
         * @param view References the Update button.
         */
        @Override
        public void onClick(View view){
            // TODO: Implement retrieval of sensor readings from garden.
            System.out.println("Update button pressed.");
        }
    }

    /**
     * A button listener for View History. This will display the sensor reading history of the garden.
     */
    private class HistoryButton implements Button.OnClickListener {
        /**
         * An implementation of Button.OnClickListener. This will retrieve the sensor reading history
         * of the garden and display the results in a dialog.
         * @param view References the View History button.
         */
        @Override
        public void onClick(View view){
            // TODO: Implement retrieval of sensor reading history of garden and display in dialog.
            System.out.println("View History button pressed.");
        }
    }

    /**
     * A button listener for Water Now. This will water the garden.
     */
    private class WaterButton implements Button.OnClickListener {
        /**
         * An implementation of Button.OnClickListener. This will send a message to the garden
         * to begin watering.
         * @param view References the Water Now button.
         */
        @Override
        public void onClick(View view){
            // TODO: Implement sending message to garden to start watering.
            System.out.println("Water Now button pressed.");
        }
    }

    /**
     * A button listener for Setup. This will display the automatic watering settings.
     */
    private class SetupButton implements Button.OnClickListener {
        /**
         * An implementation of Button.OnClickListener. This will display the automatic watering
         * settings in a dialog.
         * @param view References the Setup button.
         */
        @Override
        public void onClick(View view){
            // TODO: Implement dialog with automatic watering settings.
            System.out.println("Setup button pressed.");
        }
    }

    ///////////////////
    // INNER CLASSES //
    ///////////////////

    /**
     * An inner class that references the card view. It inherits from ViewHolder and is used with
     * RecyclerView.Adapter.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        /**
         * Constructor that will pass the card view.
         * @param v The card view to pass to this class.
         */
        public ViewHolder(View v){
            super(v);
            view = v;
        }
    }
}
