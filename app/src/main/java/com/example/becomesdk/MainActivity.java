package com.example.becomesdk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeCallBackManager;
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeInterfaseCallback;
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeResponseManager;
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.LoginError;
import com.becomedigital.sdk.identity.becomedigitalsdk.models.BDIVConfig;
import com.becomedigital.sdk.identity.becomedigitalsdk.models.ResponseIV;

public class MainActivity extends AppCompatActivity {

    //Con el fin de manejar las respuestas de inicio de sesión, debe crear un callback utilizando el siguiente fragmento de código
    private final BecomeCallBackManager mCallbackManager = BecomeCallBackManager.createNew ( );

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Parámetros de configuración: El valor de los parámetros debe ser solicitado al contratar el servicio
        String validatiopnTypes =  "PASSPORT/LICENSE/DNI/VIDEO" ;
        String clientSecret =  "AK5OZ59W2EV61GSM0FQXNBRU4DTJH3PI" ;
        String clientId =  "jamarprod" ;
        String contractId =  "72";
        String userId = "1140894625";

        //Instancia para iniciar la interfaz
        BecomeResponseManager.getInstance ( ).startAutentication (MainActivity.this,
        new BDIVConfig(clientId,
                clientSecret,
                contractId,
                validatiopnTypes,
                true,
                null,
                userId
        ));
        BecomeResponseManager.getInstance ( ).registerCallback (mCallbackManager, new BecomeInterfaseCallback( ) {


            @Override
            public void onSuccess(ResponseIV responseIV) {
                Log.i("SdkBecomeResponse",responseIV.toString());
            }

            @Override
            public void onCancel() {
                Log.i("SdkBecomeResponse","Usuario cancelo el proceso");
            }

            @Override
            public void onError(LoginError loginError) {
                Log.i("SdkBecomeResponse",loginError.toString());
            }
        });
    }
}