package com.shintheo.willonhair.service.fidelity;

import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.fidelity.CardDao;

public interface CardService {
	CardDao initUserCard(UserDao user);
	
	CardDao getUserCard(UserDao user);
	
	CardDao incrementUserFidelityPoint(UserDao user, int value);
}
