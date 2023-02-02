package com.shintheo.willonhair.serviceImpl.fidelity;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.config.SettingsDao;
import com.shintheo.willonhair.entity.fidelity.BonusIncrementDao;
import com.shintheo.willonhair.entity.fidelity.CardDao;
import com.shintheo.willonhair.entity.fidelity.PointIncrementDao;
import com.shintheo.willonhair.repository.config.SettingsRepository;
import com.shintheo.willonhair.repository.fidelity.BonusIncrementRepository;
import com.shintheo.willonhair.repository.fidelity.CardRepository;
import com.shintheo.willonhair.repository.fidelity.PointIncrementRepository;
import com.shintheo.willonhair.service.fidelity.CardService;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private SettingsRepository settingsRepo;
	
	@Autowired
	private PointIncrementRepository pointRepo;
	
	@Autowired
	private BonusIncrementRepository bonusRepo;
	
	final int DEFAULT_MAX_PENDING_POINTS = 10;

	@Override
	public CardDao initUserCard(UserDao user) {
		Optional<CardDao> opCard = cardRepo.findByUser(user);
		if(opCard.isPresent()) {
			return opCard.get();
		}
		// Create new card
		return cardRepo.save(CardDao.builder().user(user).build());
	}
	
	@Override
	public CardDao getUserCard(UserDao user) {
		return cardRepo.findByUser(user).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public CardDao incrementUserFidelityPoint(UserDao user, int value) {
		if(value < 0) {
			value = 0;
		}
		// Find card
		CardDao card = cardRepo.findByUser(user).orElseThrow(() -> new EntityNotFoundException("No card present for the given user"));
		// Create new point increment and increment this card
		pointRepo.save(PointIncrementDao.builder()
				.fidelityCard(card)
				.value(value)
				.lastPendingPoint(card.getPendingPoints())
				.lastTotalPoint(card.getTotalPoints())
				.build());
		// Increment pending points
		int newPendingPoints = card.incrementPoints(value);
		int configMaxPendingPoints = DEFAULT_MAX_PENDING_POINTS;
		// Take max pending points from settings 
		Optional<SettingsDao>  opSettings = settingsRepo.findBySectionNameAndSettingName(SettingsDao.SECTION_NAME_FIDELITY_CARD, SettingsDao.SETTING_NAME_POINT_TO_BONUS);
		if(opSettings.isPresent()) {
			SettingsDao settings = opSettings.get();
			if(settings.getSettingType() == SettingsDao.INT_TYPE_VALUE) {
				configMaxPendingPoints = Integer.parseInt(settings.getSettingValue());				
			}
		}
		while(newPendingPoints >= configMaxPendingPoints) {
			// Create new bonus and update pending points
			bonusRepo.save(BonusIncrementDao.builder().fidelityCard(card).points(configMaxPendingPoints).build());
			card.addPendingOnTotal(configMaxPendingPoints, true); // True to increment bonus
			newPendingPoints = card.getPendingPoints();
		}
		return cardRepo.save(card);
	}

}
