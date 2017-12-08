package e.hoda.digi_smartclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.*;

//Main activity for the Android App
//Author - Dalia Matar
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Datagram classes used for the UDP connection
    private DatagramSocket senderSocket, receiverSocket;
    private DatagramPacket senderPacket, receiverPacket;
    private final static int PACKETSIZE = 100 ;

    // Fields used for GUI
    private TextView location, status, alarmView;
    private EditText country, city, alarmText;
    private Switch alarmSwitch;
    private Button locBtn, alarmBtn; //send information to server using this button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextViews, sends their variables to the respective IDs.
        location = (TextView) findViewById(R.id.location);
        alarmView = (TextView) findViewById(R.id.alarmView);
        status = (TextView) findViewById(R.id.status);

        //EditTexts, can be modified by the users.
        country = (EditText) findViewById(R.id.country);
        city = (EditText) findViewById(R.id.city);
        alarmText = (EditText) findViewById(R.id.alarmText);

        //Buttons and Switches Active Listeners
        locBtn = (Button) findViewById(R.id.locBtn);
        locBtn.setOnClickListener(this); //initializes active listener
        alarmBtn = (Button) findViewById(R.id.alarmBtn);
        alarmBtn.setOnClickListener(this);

        //Switch doesnt need an active listener, it works as a boolean
        //default is false, unless its switched on.
        alarmSwitch = (Switch) findViewById(R.id.alarmSwitch);
    }

    //Handles active listener with the buttons.
    @Override
    public void onClick(View view) {
        String strMessage = ""; //empty string
        //View is used to
        switch (view.getId()) {

            case R.id.locBtn: //if set location buttons pressed

               country = (EditText) findViewById(R.id.country);
               city = (EditText) findViewById(R.id.city);

                //Convert EditText fields to strings.
                String strCountry = country.getText().toString();
                String strCity = city.getText().toString();

                //Server will process data with the help of the delimers and C character;
                //call udpSender method
                udpSender("1");
                if (isReceived()) {
                    udpSender(strCountry);
                }
                if(isReceived()) {
                    udpSender(strCity);
                }
                break;

            // alarm clock button, same procedure
            case R.id.alarmBtn:

                alarmText = (EditText) findViewById(R.id.alarmText);
                //Convert to string
                String strAlarm = alarmText.getText().toString();

                alarmSwitch = (Switch) findViewById(R.id.alarmSwitch);
                //Check current status of the Switch
                Boolean isPM = alarmSwitch.isChecked();
                //convert boolean to string, so it can be sent to the server.
                String PM = String.valueOf(isPM);

                //call method to send both strings.
                udpSender("2"); //allowing the app to send only alarms
                                     // without having to put in a location
                if (isReceived()) {
                    udpSender(strAlarm);
                }
                if(isReceived()){
                    udpSender(PM);
                }
                break;
        }
    }

    //Sender method that communicates with main RPi server via UDP
    private void udpSender(String msg) {
        senderSocket = null;
        try { //hardcored, the IP address of the router being used.
            InetAddress host = InetAddress.getByName("192.168.43.224"); //XXX is any number b/w 0 - 255
            int port = 1025;

            //create a DatagramSocket object
            senderSocket = new DatagramSocket();
            byte[] data = msg.getBytes();

            senderPacket = new DatagramPacket(data, data.length, host, port);
            status.setText(host.toString());

            //Use thread to send the packet over.
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try  {
                        senderSocket.send(senderPacket);
                        status.setText("Sent"); //confirm status of packet.
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

        } catch (Exception e) {
            status.setText("ERROR");  //if the function fails,
            // it will output error to the user on the app
            e.printStackTrace();
        }

    }

    //receive confirmation of package from the server
    //when true, this will allow us to send the next packet of information
    private boolean isReceived(){

        receiverSocket = null;
        try{
            int port = 1024;

            //create a DatagramSocket object
            receiverSocket = new DatagramSocket(port);
            receiverPacket = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE );
            receiverSocket.receive( receiverPacket ) ;

            byte[] data = receiverPacket.getData();

            return (data.length > 0); //if a message is received

        } catch(Exception e) {
            status.setText("ERROR");  //if the function fails,
            // it will output error to the user on the app
            e.printStackTrace();
        }
        return true;
    }

}




