package org.example.zhanymoon.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramService {

  @Value("${telegram.chat-id}")
  private String chatId;

  private final TelegramLongPollingBot bot;  // Автоконфиг из стартера

  public TelegramService(TelegramLongPollingBot bot) {
    this.bot = bot;
  }

  @Async
  public void sendNotification(String text) {
    SendMessage message = SendMessage.builder()
            .chatId(chatId)
            .text(text)
            .build();

    try {
      bot.execute(message);
    } catch (TelegramApiException e) {
      e.printStackTrace();  // Логируй ошибку
    }
  }
}