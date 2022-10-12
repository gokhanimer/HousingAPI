package com.restapps.HousingAPI.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapps.HousingAPI.exception.AgentCommonException;
import com.restapps.HousingAPI.exception.ListingCommonException;
import com.restapps.HousingAPI.model.Agent;
import com.restapps.HousingAPI.model.Listing;
import com.restapps.HousingAPI.repo.AgentRepo;

@Service
public class AgentServiceImpl implements AgentService{

	
	@Autowired
	AgentRepo repo;
	
	@Override
	public Agent addAgent(Agent agent) throws AgentCommonException {
		Optional<Agent> optAgent = repo.findById(agent.getAgentId());
		if(optAgent.isEmpty()) {
			repo.save(agent);
			return agent;
		}
		throw new AgentCommonException("Agent already exists");
	}

	@Override
	public Agent addListing(int agentId, Listing listing) throws AgentCommonException, ListingCommonException {
		Optional<Agent> optAgent = repo.findById(agentId);

        if (optAgent.isEmpty()) {
            throw new AgentCommonException("Agent already exists");
        }

        Agent newAgent = optAgent.get();
        List<Listing> listings = newAgent.getObjects();

        if (listings.contains(listing)) {
            throw new ListingCommonException("Listing already exists");
        }

        listings.add(listing);
        newAgent.setObjects(listings);
        repo.save(newAgent);
        return newAgent;
	}

	@Override
	public boolean deleteListing(int agentId, String objectId) throws AgentCommonException, ListingCommonException {
		Optional<Agent> optAgent = repo.findById(agentId);
		
		if(optAgent.isEmpty()) {
			throw new AgentCommonException("The agent is not availible");
		}
		
		Agent newAgent = optAgent.get();
		List<Listing> listings = newAgent.getObjects();
		
		for (Listing listing: listings ) {
			if(objectId.contains(listing.getObjectId())) {
				listings.remove(listing);
				newAgent.setObjects(listings);
				repo.save(newAgent);
				return true;
				
			}
		}
		throw new ListingCommonException("The listing is not availible");
	}

	@Override
	public boolean deleteAgent(int agentId) throws AgentCommonException {
		Optional<Agent> optAgent = repo.findById(agentId);
		
		if(optAgent.isEmpty()) {
			throw new AgentCommonException("The agent is not availible");
		}
		repo.delete(optAgent.get());
		return true;
	}

	@Override
	public void updateListing(int agentId, Listing listing) throws AgentCommonException, ListingCommonException {
		Optional<Agent> optAgent = repo.findById(agentId);
		
		if(optAgent.isEmpty()) {
			throw new AgentCommonException("The agent is not availible");
		}
		
		Agent newAgent = optAgent.get();
		List<Listing> listings = newAgent.getObjects();
		
		if(!listings.contains(listing)) {
			throw new ListingCommonException("The listing you are looking for does not exist");
		}
		
		listings.remove(listing);
		listings.add(listing);
		newAgent.setObjects(listings);
		repo.save(newAgent);
	}

	@Override
	public Agent updateAgent(Agent agent) throws AgentCommonException {
		Optional<Agent> optAgent = repo.findById(agent.getAgentId());
		if(optAgent.isPresent()) {
			repo.save(agent);
			return agent;
		}
		return null;
		
	}

	@Override
	public List<Agent> viewAllListings() {
		return repo.findAll();
	}



	/*@Override
	public List<Listing> viewListingByResidentalType(String residentalType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Listing> viewListingByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public Agent viewAllListingsByAgent(int agentId) throws AgentCommonException {
		Optional<Agent> optAgent = repo.findById(agentId);
		if(optAgent.isEmpty()) {
			throw new AgentCommonException("The agent is not availible");
		}
		return optAgent.get();
	}


}
