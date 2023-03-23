package com.cst2335.xie00076;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Data.ChatMessage;
@Dao
public interface ChatMessageDAO {
    @Insert
     void insertMessage(ChatMessage m);

    @Query("Select*from ChatMessage")
    List<ChatMessage> getAllMessages();

    @Delete
    void deleteMessage(ChatMessage m);

    @Query("DELETE FROM ChatMessage WHERE message= :message and timeSent = :timeSent and sendOrReceive = :sentOrReceive")
    void deleteMessage( String message, String timeSent, boolean sentOrReceive);
}
