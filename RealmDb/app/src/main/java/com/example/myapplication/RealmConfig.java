package com.example.myapplication;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfig extends Application {
    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("userData.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);


    }
}