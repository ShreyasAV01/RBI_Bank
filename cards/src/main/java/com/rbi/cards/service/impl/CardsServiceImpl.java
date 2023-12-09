package com.rbi.cards.service.impl;

import com.rbi.cards.constants.CardsConstants;
import com.rbi.cards.dto.CardsDto;
import com.rbi.cards.entity.Cards;
import com.rbi.cards.exception.CardAlreadyExistsException;
import com.rbi.cards.exception.ResourceNotFoundException;
import com.rbi.cards.mapper.CardsMapper;
import com.rbi.cards.repository.CardsRepository;
import com.rbi.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards=  cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));

    }
/**
* @param mobileNumber of customer
 * @return new card details*/
    private Cards createNewCard(String mobileNumber) {
        Cards newCard =new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).
                orElseThrow( ()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    @Override
    public Boolean updateCard(CardsDto cardDto) {
        Cards cards = cardsRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(() -> new ResourceNotFoundException("Card", "CardNumber", cardDto.getCardNumber()));
        CardsMapper.mapToCards(cardDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public Boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
