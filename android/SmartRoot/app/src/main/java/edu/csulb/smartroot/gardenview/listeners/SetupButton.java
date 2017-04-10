package edu.csulb.smartroot.gardenview.listeners;

import android.view.View;
import android.widget.Button;

/**
 * A button listener for Setup. This will display the automatic watering settings.
 */
public class SetupButton implements Button.OnClickListener {
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