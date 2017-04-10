package edu.csulb.smartroot.gardenview.listeners;

import android.view.View;
import android.widget.Button;

/**
 * A button listener for Update. This will update the sensor readings of the garden.
 */
public class UpdateButton implements Button.OnClickListener {
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