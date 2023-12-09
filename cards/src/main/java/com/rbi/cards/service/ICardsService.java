package com.rbi.cards.service;

import com.rbi.cards.dto.CardsDto;
import com.rbi.cards.entity.Cards;

public interface ICardsService {


    void createCard(String mobileNumber);
    CardsDto fetchCard(String mobileNumber);
    Boolean updateCard(CardsDto cardDto);
    Boolean deleteCard(String mobileNumber);
}
