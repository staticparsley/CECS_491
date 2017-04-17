package edu.csulb.smartroot.gardenview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import edu.csulb.smartroot.R;
import edu.csulb.smartroot.gardenview.listeners.*;

/**
 * An adapter that will generate a card for each garden. These cards will be displayed in a list
 * in GardenView.java.
 */
public class GardenHolder extends RecyclerView.Adapter<GardenHolder.ViewHolder>
    implements Toolbar.OnMenuItemClickListener{

    private ArrayList<Garden> gardens;
    private ViewGroup viewGroup;
    private AlertDialog dialog;

    /**
     * Constructor that will get a reference to an ArrayList of gardens to create each individual
     * card.
     * @param gardens An ArrayList of gardens.
     */
    public GardenHolder(ArrayList<Garden> gardens) {

        this.gardens = gardens;
        this.viewGroup = null;
        this.dialog = null;

        // Adding empty gardens for testing purposes
        gardens.add(new Garden());
        gardens.add(new Garden());
        gardens.add(new Garden());
    }

    /**
     * An implementation of RecyclerView.Adapter. It will create the card view for each individual
     * garden using card_garden.xml layout.
     * @param parent The ViewGroup which the card view will be added to. In this case, it will be
     *               added to the activity in GardenView.java.
     * @param viewType The view type of the generated card.
     * @return A ViewHolder containing the garden card view.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewGroup = parent;
        View v = LayoutInflater.from(viewGroup.getContext())
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
    public void onBindViewHolder(ViewHolder holder, int position) {
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
            AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());

            final View dialogView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.dialog_push_notification, viewGroup, false);

            builder.setView(dialogView);
            builder.setPositiveButton(R.string.button_done, null);

            dialog = builder.create();
            dialog.show();

            Switch pushNotification = (Switch) dialogView.findViewById(R.id.switch_push_notification);
            pushNotification.setOnCheckedChangeListener(new PushSwitch());

            Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            button.setOnClickListener(new DoneButton());

            System.out.println("Push Notification");
        }
        if(menuItem.getItemId() == R.id.menu_shutdown) {
            // TODO: Implement sending shut down message garden.
            System.out.println("Shut down");
        }
        return true;
    }

    /**
     * A button listener for Done in the Push Notification Settings dialog. This will configure
     * the push notification settings according to the user's preference.
     */
    private class DoneButton implements Button.OnClickListener {

        /**
         * An implementation of Button.OnClickListener. This will get the push notification settings
         * specified by the user in the the Push Notification Settings Dialog.
         * @param view References the Done button in the Push Notification Settings Dialog.
         */
        @Override
        public void onClick(View view) {
            // TODO: Process the push notification settings
            dialog.dismiss();
        }
    }

    /**
     * A switch listener for Push Notification in the Push Notification Settings dialog. This will
     * enabled and disable the Push Notification Settings.
     */
    private class PushSwitch implements CompoundButton.OnCheckedChangeListener {
        /**
         * An implementation of CompoundButton.OnCheckedChangeListener. This will update the state
         * of all the Push Notification Settings.
         * @param buttonView References to the Push Notification Switch.
         * @param isChecked The current state of the switch when pressed.
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            System.out.println("Switch has changed");

            if (isChecked) {
                // Set the state for temperature warning
                setState(false,
                        (Switch) dialog.findViewById(R.id.switch_temperature_warning),
                        (EditText) dialog.findViewById(R.id.temperature_min),
                        (EditText) dialog.findViewById(R.id.temperature_max));

                // Set the state for moisture warning
                setState(
                        false,
                        (Switch) dialog.findViewById(R.id.switch_moisture_warning),
                        (EditText) dialog.findViewById(R.id.moisture_min),
                        (EditText) dialog.findViewById(R.id.moisture_max));

                // Set the state for humidity
                setState(false,
                        (Switch) dialog.findViewById(R.id.switch_humidity_warning),
                        (EditText) dialog.findViewById(R.id.humidity_min),
                        (EditText) dialog.findViewById(R.id.humidity_max));
            } else {
                // Set the state for temperature warning
                setState(true,
                        (Switch) dialog.findViewById(R.id.switch_temperature_warning),
                        (EditText) dialog.findViewById(R.id.temperature_min),
                        (EditText) dialog.findViewById(R.id.temperature_max));

                // Set the state for moisture warning
                setState(
                        true,
                        (Switch) dialog.findViewById(R.id.switch_moisture_warning),
                        (EditText) dialog.findViewById(R.id.moisture_min),
                        (EditText) dialog.findViewById(R.id.moisture_max));

                // Set the state for humidity
                setState(true,
                        (Switch) dialog.findViewById(R.id.switch_humidity_warning),
                        (EditText) dialog.findViewById(R.id.humidity_min),
                        (EditText) dialog.findViewById(R.id.humidity_max));
            }
        }

        /**
         * A helper method that will assign the checked and enabled state of a push notification
         * settings in the dialog.
         * @param state The state of the setting.
         * @param rSwitch The Switch of the setting.
         * @param min The minimum limit of the setting.
         * @param max The maximum limit of the setting.
         */
        public void setState(boolean state, Switch rSwitch, EditText min, EditText max) {
            rSwitch.setChecked(false);
            rSwitch.setEnabled(state);

            min.setText("");
            min.setEnabled(state);

            max.setText("");
            max.setEnabled(state);
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
