package com.restapps.HousingAPI.repo;

import org.springframework.stereotype.Repository;

import com.restapps.HousingAPI.model.Agent;
import com.restapps.HousingAPI.model.Listing;

import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface AgentRepo extends MongoRepository<Agent,Integer>{

	
}
