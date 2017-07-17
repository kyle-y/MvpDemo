package com.example.administrator.mvpdemo.m.database;

import android.content.Context;

import com.demo.greendao.DaoMaster;
import com.demo.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/7/12.
 */

public class GreendaoManager {

    private Context mContext;
    private volatile static GreendaoManager mGreendaoManager;
    private DaoSession mainSession;

    public static GreendaoManager getInstance() {
        if (mGreendaoManager == null) {
            synchronized (GreendaoManager.class) {
                if (mGreendaoManager == null) {
                    mGreendaoManager = new GreendaoManager();
                }
            }
        }
        return mGreendaoManager;
    }

    public void init(Context context) {
        this.mContext = context;
        mainSession = openDb("main_db");
    }

    public DaoSession getMainSession(){
        return mainSession;
    }

    private DaoSession openDb(String dbName) {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(mContext, dbName);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }
}
