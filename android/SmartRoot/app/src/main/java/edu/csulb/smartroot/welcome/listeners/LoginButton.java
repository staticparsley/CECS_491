package edu.csulb.smartroot.welcome.listeners;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csulb.smartroot.R;

/**
 * A button listener for Login. This will attempt to login the user into the database.
 */
public class LoginButton implements Button.OnClickListener {

    private AlertDialog dialog;
    private Context context;

    /**
     * Constructor that will pass the reference to the Login Dialog.
     * @param dialog References the Login Dialog.
     */
    public LoginButton(AlertDialog dialog, Context context){
        this.dialog = dialog;
        this.context = context;
    }

    /**
     * An implementation of Button.OnClickListener. This will validate the user input and
     * attempt to login the user into the database.
     * @param view References the Login button.
     */
    @Override
    public void onClick(View view){
        // Get the references to EditText from dialog_login.xml layout.
        EditText eUserName = (EditText) dialog.findViewById(R.id.username);
        EditText ePassword = (EditText) dialog.findViewById(R.id.password);

        // Get the credentials from the EditText
        String userName = eUserName.getText().toString();
        String password = ePassword.getText().toString();

        // Validate user input for username and password
        if (userName.equals("")) {
            Toast.makeText(context, R.string.username_empty, Toast.LENGTH_SHORT).show();
            eUserName.requestFocus();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(context, R.string.password_empty, Toast.LENGTH_SHORT).show();
            ePassword.requestFocus();
            return;
        }

        // TODO: Implement logging in user to the database.
        // Create new intent to start GardenView activity upon validation.
        System.out.println("Trying to login...");
        System.out.println(userName);
        System.out.println(password);
        dialog.dismiss();
    }
}