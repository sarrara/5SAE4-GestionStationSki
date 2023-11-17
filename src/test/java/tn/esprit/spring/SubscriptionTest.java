package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class SubscriptionTest {
	@InjectMocks
	private SubscriptionServicesImpl subscriptionService;

	@Mock
	ISubscriptionRepository subscriptionRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddSubscription() {
		Subscription subscription = new Subscription();
		subscription.setStartDate(LocalDate.now());
		subscription.setTypeSub(TypeSubscription.ANNUAL);

		when(subscriptionRepository.save(subscription)).thenReturn(subscription);

		Subscription result = subscriptionService.addSubscription(subscription);

		assertEquals(TypeSubscription.ANNUAL, result.getTypeSub());
		assertEquals(LocalDate.now().plusYears(1), result.getEndDate());
	}

	@Test
	void testUpdateSubscription() {
		Subscription subscription = new Subscription();
		subscription.setNumSub(1L);

		when(subscriptionRepository.save(subscription)).thenReturn(subscription);

		Subscription result = subscriptionService.updateSubscription(subscription);

		assertEquals(1L, result.getNumSub());
	}

	@Test
	void testRetrieveSubscriptionById() {
		Long subscriptionId = 1L;
		Subscription subscription = new Subscription();
        subscription.setNumSub(subscriptionId);

		when(subscriptionRepository.findById(subscriptionId)).thenReturn(Optional.of(subscription));

		Subscription result = subscriptionService.retrieveSubscriptionById(subscriptionId);

		assertEquals(subscriptionId, result.getNumSub());
	}

	@Test
	void testGetSubscriptionByType() {
		TypeSubscription type = TypeSubscription.ANNUAL;
		Set<Subscription> subscriptions = new HashSet<>();
		Subscription subscription = new Subscription();
		subscription.setTypeSub(type);
		subscriptions.add(subscription);

		when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(type)).thenReturn(subscriptions);

		Set<Subscription> result = subscriptionService.getSubscriptionByType(type);


		assertEquals(1, result.size());
		assertEquals(type, result.iterator().next().getTypeSub());
	}

	@Test
	void testRetrieveSubscriptionsByDates() {
		LocalDate startDate = LocalDate.of(2023, 1, 1);
		LocalDate endDate = LocalDate.of(2023, 12, 31);

		List<Subscription> subscriptions = new ArrayList<>();
		Subscription subscription = new Subscription();
		subscription.setStartDate(startDate);
		subscription.setEndDate(endDate);
		subscriptions.add(subscription);

		when(subscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate)).thenReturn(subscriptions);

		List<Subscription> result = subscriptionService.retrieveSubscriptionsByDates(startDate, endDate);

		assertEquals(1, result.size());
		assertEquals(startDate, result.get(0).getStartDate());
		assertEquals(endDate, result.get(0).getEndDate());
	} 
}