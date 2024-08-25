package it.unibs.ing.elaborato.model.proposal;

import it.unibs.ing.elaborato.model.user.Consumer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExchangeProposals implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;
	private final List<ExchangeProposal> exchangeProposals;

	public ExchangeProposals() 
	{
		this.exchangeProposals = new ArrayList<>();
	}

	public List<ExchangeProposal> getExchangeProposals() 
	{
		return exchangeProposals;
	}

	public void add(ExchangeProposal exchangeProposal) 
	{
		exchangeProposals.add(exchangeProposal); 
	}

	public List<ExchangeProposal> activeProposalBelongOneConsumer(Consumer consumer)
	{
		return exchangeProposals.stream().filter(s -> s.getOwner().getUsername().equals(consumer.getUsername()) && s.getState().equals(State.OPEN)).toList();
	}
	
	public List<ExchangeProposal> closedProposalBelongOneConsumer(Consumer consumer)
	{
		return exchangeProposals.stream().filter(s -> s.getOwner().getUsername().equals(consumer.getUsername()) && s.getState().equals(State.CLOSED)).toList();
	}
	
	public List<ExchangeProposal> withdrawnProposalBelongOneConsumer(Consumer consumer)
	{
		return exchangeProposals.stream().filter(s -> s.getOwner().getUsername().equals(consumer.getUsername()) && s.getState().equals(State.WITHDRAWN)).toList();
	}

	public boolean contains(ExchangeProposal exchangeProposal) 
	{
		return exchangeProposals.contains(exchangeProposal);
	}

	public boolean verifyClosedProposals(ExchangeProposal exchangeProposal, ExchangeProposal fixed, ExchangeProposals candidateClosedSet)
	{
		for (ExchangeProposal elem : exchangeProposals) 
		{
			if (exchangeProposal.getCouple().getFirstLeaf().equals(elem.getCouple().getSecondLeaf()) &&
					exchangeProposal.getHoursRequest() == elem.getHoursOffered() &&
					!(exchangeProposal.getOwner().getUsername().equals(elem.getOwner().getUsername())) &&
					exchangeProposal.getOwner().getDistrict().equals(elem.getOwner().getDistrict()) &&
					exchangeProposal.getState().equals(State.OPEN) &&
					elem.getState().equals(State.OPEN))
			{
				candidateClosedSet.add(exchangeProposal);
				if (elem.getCouple().getFirstLeaf().equals(fixed.getCouple().getSecondLeaf()) &&
						elem.getHoursRequest() == fixed.getHoursOffered() &&
						elem.getOwner().getDistrict().equals(fixed.getOwner().getDistrict()) &&
						exchangeProposal.getOwner().getDistrict().equals(elem.getOwner().getDistrict()) &&
						exchangeProposal.getState().equals(State.OPEN) &&
						elem.getState().equals(State.OPEN)) 
				{
					candidateClosedSet.add(elem);
					candidateClosedSet.changeStatusToClosed();
					return true;
				}
				if (verifyClosedProposals(elem, fixed, candidateClosedSet)) {
					return true;
				}
			}
		}
		candidateClosedSet.clear();
		return false;
	}

	public void clear()
	{
		exchangeProposals.clear();
	}

	public void changeStatusToClosed() 
	{
		for(ExchangeProposal elem : exchangeProposals) 
			elem.changeStatusToClosed();
	}

}
