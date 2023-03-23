package com.cst2335.xie00076;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Data.ChatMessage;

    @Database(entities = {ChatMessage.class}, version = 1)
    public abstract class MessageDatabase extends RoomDatabase {
        private static MessageDatabase minstance;
        private static final String DB_NAME = "ChatMessage";

        public abstract ChatMessageDAO chatMessageDAO();
        public static synchronized MessageDatabase getInstance(Context ctx) {
            if(minstance == null) {
                minstance = Room.databaseBuilder(ctx.getApplicationContext(),
                                MessageDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return minstance;
        }
    }

