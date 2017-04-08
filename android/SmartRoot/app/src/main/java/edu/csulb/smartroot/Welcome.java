package edu.csulb.smartroot;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    private Context context;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Get reference to this context and inflater to use in dialog listeners
        context = this;
        inflater = getLayoutInflater();
    }

    /////////////////////
    // ONCLICK METHODS //
    /////////////////////

    /**
     * Creates a login dialog when the Login button is clicked/tapped.
     * @param view The button that this method is assigned to in dialog_login.xml layout.
     */
    public void login(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = inflater.inflate(R.layout.dialog_login, null);

        // Set the characteristics of the dialog
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.login, null);
        builder.setNegativeButton(R.string.cancel, null);

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Override login button listeners on dialog. This is so the dialog will remain
        // open when credentials are not valid.
        Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setOnClickListener(new LoginButton(dialog));
    }

    /**
     * Creates a register dialog when the Register button is clicked/tapped.
     * @param view The button that this method is assigned to in dialog_register.xml layout.
     */
    public void register(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = inflater.inflate(R.layout.dialog_register, null);

        // Set the characteristics of the dialog
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.create, null);
        builder.setNegativeButton(R.string.cancel, null);

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Override login button listeners on dialog. This is so the dialog will remain
        // open when credentials are not valid.
        Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setOnClickListener(new RegisterButton(dialog));
    }

    //////////////////////
    // BUTTON LISTENERS //
    //////////////////////

    /**
     * A button listener for Login. This will attempt to login the user into the database.
     */
    private class LoginButton implements Button.OnClickListener {
        private AlertDialog dialog;

        /**
         * Constructor that will pass the reference to the Login Dialog.
         * @param dialog References the Login Dialog.
         */
        public LoginButton(AlertDialog dialog){
            this.dialog = dialog;
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

            System.out.println("Trying to login...");
            System.out.println(userName);
            System.out.println(password);
            dialog.dismiss();
        }
    }

    private class RegisterButton implements Button.OnClickListener{
        private AlertDialog dialog;

        public RegisterButton(AlertDialog dialog){
            this.dialog = dialog;
        }

        @Override
        public void onClick(View view){
            // Get the references to EditText from dialog_register.xml layout.
            EditText eUserName = (EditText) dialog.findViewById(R.id.username);
            EditText eEmail = (EditText) dialog.findViewById(R.id.email);
            EditText ePassword = (EditText) dialog.findViewById(R.id.password);
            EditText eConfirmPassword = (EditText) dialog.findViewById(R.id.confirm_password);

            // Get the credentials from the EditText
            String userName = eUserName.getText().toString();
            String email = eEmail.getText().toString();
            String password = ePassword.getText().toString();
            String confirmPassword = eConfirmPassword.getText().toString();

            // Validate user input for username, email, password, and confirm password
            if (userName.equals("")) {
                Toast.makeText(context, R.string.username_empty, Toast.LENGTH_SHORT).show();
                eUserName.requestFocus();
                return;
            }
            if (email.equals("")) {
                Toast.makeText(context, R.string.email_empty, Toast.LENGTH_SHORT).show();
                eEmail.requestFocus();
                return;
            }
            if (password.equals("")) {
                Toast.makeText(context, R.string.password_empty, Toast.LENGTH_SHORT).show();
                ePassword.requestFocus();
                return;
            }
            if (confirmPassword.equals("")) {
                Toast.makeText(context, R.string.confirm_password_empty, Toast.LENGTH_SHORT).show();
                eConfirmPassword.requestFocus();
                return;
            }
            if (!password.equals(confirmPassword)){
                Toast.makeText(context, R.string.password_mismatch, Toast.LENGTH_SHORT).show();
                eConfirmPassword.requestFocus();
                return;
            }

            System.out.println("Trying to register...");
            System.out.println(userName + "\n" +
                    email + "\n" +
                    password + "\n" +
                    confirmPassword + "\n");

            dialog.dismiss();
        }
    }
}