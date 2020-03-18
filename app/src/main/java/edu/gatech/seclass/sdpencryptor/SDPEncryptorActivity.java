package edu.gatech.seclass.sdpencryptor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.Z;

public class SDPEncryptorActivity extends AppCompatActivity {


    private EditText inputMessage;
    private EditText shiftNumber;
    private String inputMessageString;
    public int shiftNumberInt;
    private TextView encryptedMessage;
    // for spinner case selection
    final int[] alphaType = new int[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdpencryptor);

        inputMessage = findViewById(R.id.messageInput);
        shiftNumber = findViewById(R.id.shiftNumber);
        encryptedMessage = findViewById(R.id.cipherText);



        // setting the target alphabet type spinner

        final Spinner targetAlphabet = findViewById(R.id.targetAlphabet);

        String[] alphabets = {"Normal","Reverse","QWERTY"};

        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,alphabets);
        targetAlphabet.setAdapter(sAdapter);

        targetAlphabet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = targetAlphabet.getSelectedItemPosition();
                switch (index) {
                    case 0:
                        alphaType[0] = 1;
                        break;
                    case 1:
                        alphaType[0] = 2;
                        break;
                    case 2:
                        alphaType[0] = 3;
                        break;

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        //end of spinner

        // encrypt button functionality

        Button encryptButton = findViewById(R.id.encryptButton);
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // input validation
                if(inputMessage.getText().toString().equals(""))
                {
                    encryptedMessage.setText("");
                    inputMessage.setError("Missing Message");

                }
                else
                if(!inputMessage.getText().toString().matches(".*[a-zA-Z].*"))
                {
                    encryptedMessage.setText("");
                    inputMessage.setError("Invalid Message");
                }
                else
                if (Integer.parseInt(shiftNumber.getText().toString()) < 1 || Integer.parseInt(shiftNumber.getText().toString()) >= 26 ||shiftNumber.getText().toString().equalsIgnoreCase(""))
                {
                    encryptedMessage.setText("");
                    shiftNumber.setError("Invalid Shift Number");
                }
                else
                    {

                // encryption

                inputMessageString = inputMessage.getText().toString();
                shiftNumberInt = Integer.parseInt(shiftNumber.getText().toString());
                String cipheredText = "";
                switch (alphaType[0])
                {

                    case 1:

                        String normalAlphabet = "abcdefghijklmnopqrstuvwxyz";
                        for (int i = 0; i < inputMessageString.length(); i++)
                        {
                            if(Character.isLetter(inputMessageString.charAt(i)))
                            {
                                if(Character.isUpperCase(inputMessageString.charAt(i)))
                                {
                                    int mapToIndex = normalAlphabet.indexOf(Character.toLowerCase(inputMessageString.charAt(i)));
                                    int newIndex = (mapToIndex + shiftNumberInt) % 26;
                                    char shiftedChar = Character.toUpperCase(normalAlphabet.charAt(newIndex));
                                    cipheredText += shiftedChar;

                                }else
                                    {
                                        int mapToIndex = normalAlphabet.indexOf(inputMessageString.charAt(i));
                                    int newIndex = (mapToIndex + shiftNumberInt) % 26;
                                    char shiftedChar = normalAlphabet.charAt(newIndex);
                                    cipheredText += shiftedChar;
                                    }
                            }
                            else{
                                cipheredText += inputMessageString.charAt(i);
                            }

                        }

                        encryptedMessage.setText(cipheredText);
                        break;

                    case 2:


                        normalAlphabet = "abcdefghijklmnopqrstuvwxyz";
                        String reverseAlphabet = "zyxwvutsrqponmlkjihgfedcba";
                        for (int i = 0; i < inputMessageString.length(); i++)
                        {
                            if(Character.isLetter(inputMessageString.charAt(i)))
                            {

                                if(Character.isUpperCase(inputMessageString.charAt(i)))
                                {
                                    int mapToIndex = normalAlphabet.indexOf(Character.toLowerCase(inputMessageString.charAt(i)));
                                    int newIndex = (mapToIndex + shiftNumberInt) % 26;
                                    char shiftedChar = Character.toUpperCase(reverseAlphabet.charAt(newIndex));
                                    cipheredText += shiftedChar;

                                }else
                                {
                                    int mapToIndex = normalAlphabet.indexOf(inputMessageString.charAt(i));
                                    int newIndex = (mapToIndex + shiftNumberInt) % 26;
                                    char shiftedChar = reverseAlphabet.charAt(newIndex);
                                    cipheredText += shiftedChar;
                                }
                            }
                            else{
                                cipheredText += inputMessageString.charAt(i);
                            }

                        }

                        encryptedMessage.setText(cipheredText);
                        break;


                    case 3:

                        normalAlphabet = "abcdefghijklmnopqrstuvwxyz";
                        String qwertyAlphabet = "qwertyuiopasdfghjklzxcvbnm";
                        for (int i = 0; i < inputMessageString.length(); i++)
                        {
                            if(Character.isLetter(inputMessageString.charAt(i)))
                            {
                                if(Character.isUpperCase(inputMessageString.charAt(i)))
                                {
                                    int mapToIndex = normalAlphabet.indexOf(Character.toLowerCase(inputMessageString.charAt(i)));
                                    int newIndex = (mapToIndex + shiftNumberInt) % 26;
                                    char shiftedChar = Character.toUpperCase(qwertyAlphabet.charAt(newIndex));
                                    cipheredText += shiftedChar;

                                }else
                                {
                                    int mapToIndex =normalAlphabet.indexOf(inputMessageString.charAt(i));
                                    int newIndex = (mapToIndex + shiftNumberInt) % 26;
                                    char shiftedChar = qwertyAlphabet.charAt(newIndex);
                                    cipheredText += shiftedChar;
                                }
                            }
                            else{
                                cipheredText += inputMessageString.charAt(i);
                            }

                        }

                        encryptedMessage.setText(cipheredText);
                        break;

                    default:
                        Toast toast0 = Toast.makeText(getApplicationContext(), "The cipher did'nt work", Toast.LENGTH_SHORT);
                        toast0.show();


                }//end of switch statement


            }//end of else statement
            }// end of Onclick
        });





    }

}
