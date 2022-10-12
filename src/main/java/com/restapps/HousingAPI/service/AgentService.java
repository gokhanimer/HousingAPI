package com.restapps.HousingAPI.service;

import java.util.List;

import com.restapps.HousingAPI.exception.AgentCommonException;
import com.restapps.HousingAPI.exception.ListingCommonException;
import com.restapps.HousingAPI.model.Agent;
import com.restapps.HousingAPI.model.Listing;

public interface AgentService {

	public Agent addAgent(Agent agent) throws AgentCommonException; //--
	public Agent addListing(int agentId, Listing listing) throws AgentCommonException, ListingCommonException;//--
	
	public boolean deleteListing(int agentId, String objectId) throws AgentCommonException, ListingCommonException;	//--
	public boolean deleteAgent(int agentId) throws AgentCommonException;
	
	public void updateListing(int agentId, Listing listing) throws AgentCommonException, ListingCommonException;//--
	public Agent updateAgent(Agent agent) throws AgentCommonException;
	
	public List<Agent> viewAllListings();//--
	//public List<Listing> viewListingByResidentalType(String residentalType);
	//public List<Listing> viewListingByCity(String city);
	public Agent viewAllListingsByAgent(int agentId)  throws AgentCommonException;
	
	
}
