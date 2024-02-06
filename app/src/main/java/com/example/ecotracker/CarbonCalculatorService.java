package com.example.ecotracker;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;
import java.util.Map;

public class CarbonCalculatorService extends Service {

    private final IBinder binder = new CarbonCalculatorBinder();
    private int totalValue = 0;

    public class CarbonCalculatorBinder extends Binder {
        CarbonCalculatorService getService() {
            return CarbonCalculatorService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int calculateTotalValue(int[] result) {
        totalValue = 0;
        for(int i=0;i<result.length;i++){
            totalValue+=result[i];
        }
        return totalValue;
    }
}
