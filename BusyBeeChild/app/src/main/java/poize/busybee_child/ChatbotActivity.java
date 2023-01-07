package poize.busybee_child;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChatbotActivity extends AppCompatActivity {
//    private RecyclerView chatRecyclerView;
    private EditText inputMessage;
    private AppCompatImageView iv_send;
    private TextView[] tv;
    private String inMessage;
    private String outMessage;
    int i=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        tv = new TextView[6];
        tv[0] = findViewById(R.id.textMessage1);
        tv[1] = findViewById(R.id.textMessage2);
        tv[2] = findViewById(R.id.textMessage3);
        tv[3] = findViewById(R.id.textMessage4);
        tv[4] = findViewById(R.id.textMessage5);
        tv[5] = findViewById(R.id.textMessage6);

        iv_send = findViewById(R.id.iv_send);
        inputMessage = findViewById(R.id.inputMessage);

        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inMessage = inputMessage.getText().toString();
                if(i<6){
                    if(inMessage.contains("fun")||inMessage.contains("Fun")){
                        outMessage = "So Happy to here that! How about you share it with your parents as well.";
                    }
                    else if(inMessage.contains("sad")){
                        outMessage = "So sorry to here that, how about playing some games?";
                    }
                    else if(inMessage.contains("happy")){
                        outMessage = "Your parents will be happy for that too.";
                    }
                    else
                        outMessage = "I see, tell me more on that sugar.";

                    tv[i].setVisibility(View.VISIBLE);
                    tv[i].setText(outMessage);
                    tv[i+1].setVisibility(View.VISIBLE);
                    tv[i+1].setText(inMessage);
                    i+=2;
                }



            }
        });

    }
}