package edu.csulb.smartroot.gardenview.listeners;

import android.view.View;
import android.widget.Button;

/**
 * A button listener for View History. This will display the sensor reading history of the garden.
 */
public class HistoryButton implements Button.OnClickListener {
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