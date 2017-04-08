package edu.csulb.smartroot;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class Welcome extends AppCompatActivity {

    private LayoutInflater inflater;

    private View viewLogin;
    private View viewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Get a reference to this layout inflater
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
        DialogLogin dialogLogin = new DialogLogin();
        viewLogin = inflater.inflate(R.layout.dialog_login, null);

        // Set the characteristics of the dialog
        builder.setView(viewLogin);
        builder.setPositiveButton(R.string.login, dialogLogin);
        builder.setNegativeButton(R.string.cancel, dialogLogin);

        // Create and show the dialog
        builder.create();
        builder.show();
    }

    /**
     * Creates a register dialog when the Register button is clicked/tapped.
     * @param view The button that this method is assigned to in dialog_register.xml layout.
     */
    public void register(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DialogRegister dialogRegister = new DialogRegister();
        viewRegister = inflater.inflate(R.layout.dialog_register, null);

        // Set the characteristics of the dialog
        builder.setView(viewRegister);
        builder.setPositiveButton(R.string.create, dialogRegister);
        builder.setNegativeButton(R.string.cancel, dialogRegister);

        // Create and show the dialog
        builder.create();
        builder.show();
    }

    //////////////////////
    // DIALOG LISTENERS //
    //////////////////////

    /**
     * A dialog listener for Login. This will attempt to login the user into the database.
     */
    private class DialogLogin implements DialogInterface.OnClickListener{
        /**
         * An implementation of DialogInterface. This will handle logging in the user with
         * the credentials provided.
         * @param dialogInterface The login dialog interface.
         * @param id The reference to the button that has been clicked/tapped.
         */
        @Override
        public void onClick(DialogInterface dialogInterface, int id){
            // When the user selects login...
            if (id == DialogInterface.BUTTON_POSITIVE){
                //... attempt to login the user

                // Get the references to EditText from dialog_login.xml layout.
                EditText eUserName = (EditText) viewLogin.findViewById(R.id.username);
                EditText ePassword = (EditText) viewLogin.findViewById(R.id.password);

                // Get the credentials from the EditText
                String userName = eUserName.getText().toString();
                String password = ePassword.getText().toString();

                // Attempt to log user into the system
                System.out.println("Trying to login...");
                System.out.println(userName);
                System.out.println(password);
                dialogInterface.dismiss();
            }
            /// When the user selects cancel...
            else if (id == DialogInterface.BUTTON_NEGATIVE) {
                //... cancel the attempt to login the user
                System.out.println("Cancelling login...");
                dialogInterface.cancel();
            }
        }
    }

    /**
     * A dialog listener for Register. This will attempt to register a new user into the database.
     */
    private class DialogRegister implements DialogInterface.OnClickListener{
        /**
         * An implementation of DialogInterface. This will handle registering the user with the
         * credentials provided.
         * @param dialogInterface The register dialog interface.
         * @param id The reference to the button that has been clicked/tapped.
         */
        @Override
        public void onClick(DialogInterface dialogInterface, int id){
            // Get the references to EditText from dialog_register.xml layout.
            EditText eUserName = (EditText) viewRegister.findViewById(R.id.username);
            EditText eEmail = (EditText) viewRegister.findViewById(R.id.email);
            EditText ePassword = (EditText) viewRegister.findViewById(R.id.password);
            EditText eConfirmPassword = (EditText) viewRegister.findViewById(R.id.confirm_password);

            // Get the credentials from the EditText
            String userName = eUserName.getText().toString();
            String email = eEmail.getText().toString();
            String password = ePassword.getText().toString();
            String confirmPassword = eConfirmPassword.getText().toString();

            if (id == DialogInterface.BUTTON_POSITIVE){
                System.out.println("Trying to register...");
                System.out.println(userName + "\n" +
                                    email + "\n" +
                                    password + "\n" +
                                    confirmPassword + "\n");
            }
            else if (id == DialogInterface.BUTTON_NEGATIVE){
                System.out.println("Cancelling register...");
            }
        }
    }
}
