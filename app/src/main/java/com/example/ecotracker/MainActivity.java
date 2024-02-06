package com.example.ecotracker;
import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

        import java.util.HashMap;
        import java.util.Map;

        import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.GsonBuilder;
public class MainActivity extends AppCompatActivity {

    int[] image = new int[16];
    int rotate = 0;
    int Max_size = image.length - 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image[0] = R.drawable.avatar1;
        image[1] = R.drawable.avatar2;
        image[2] = R.drawable.avatar3;
        image[3] = R.drawable.avatar4;
        image[4] = R.drawable.avatar5;
        image[5] = R.drawable.avatar6;
        image[6] = R.drawable.avatar7;
        image[7] = R.drawable.avatar8;
        image[8] = R.drawable.avatar9;
        image[9] = R.drawable.avatar10;
        image[10] = R.drawable.avatar11;
        image[11] = R.drawable.avatar12;
        image[12] = R.drawable.avatar13;
        image[13] = R.drawable.avatar14;
        image[14] = R.drawable.avatar15;
        image[15] = R.drawable.avatar16;

        DataPublic.userData.put("venki",200);
        DataPublic.userData.put("Boo",800);
    }

    public void opencarbonCalculator(View view) {
        EditText usernameEditText = findViewById(R.id.name);
        String username = "";
        username=usernameEditText.getText().toString();
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        ImageView imageView = findViewById(R.id.avatarimg);
        int selectedAvatar = 0;
//        getResources().getIdentifier(imageView.getTag().toString(), "drawable", getPackageName());

        if(username.equals("")){
            username="User"+DataPublic.userData.size()+1;
        }

        DbManager db=new DbManager(this);
        String res=db.add_record(username);
        Toast.makeText(this,res,Toast.LENGTH_LONG).show();

        DataPublic.userData.put(username, selectedAvatar);
        Intent intent = new Intent(this, CarbonCalculator.class);
        startActivity(intent);
        usernameEditText.setText("");
    }

    public void next(View view) {
        if (rotate < Max_size) {
            rotate++;
            updateActivity(rotate);
        }
    }

    private void updateActivity(int rotate) {
        ImageView image1 = findViewById(R.id.avatarimg);
        image1.setImageResource(image[rotate]);
    }

    public void previous(View view) {

        if (rotate > 0) {
            rotate--;
        }
        updateActivity(rotate);
    }

}