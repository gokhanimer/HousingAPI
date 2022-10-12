package com.restapps.HousingAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restapps.HousingAPI.exception.AgentCommonException;
import com.restapps.HousingAPI.exception.ListingCommonException;
import com.restapps.HousingAPI.model.Agent;
import com.restapps.HousingAPI.model.Listing;
import com.restapps.HousingAPI.service.AgentService;

@CrossOrigin
@RestController
@RequestMapping("Listing/")
public class AgentController {
	//ListingCommonException
	@Autowired
	AgentService service;
	
	@PostMapping("/addAgent")
    public ResponseEntity<?> addAgent(@RequestBody Agent agent) {
        try {

            return new ResponseEntity<>(service.addAgent(agent), HttpStatus.OK);

        } catch (AgentCommonException e) {

            return new ResponseEntity<>(e.getMessage("Agent already exists"), HttpStatus.CONFLICT);

        }
    }
	
	@PostMapping("/addLisitng/{agentId}")
    public ResponseEntity<?> addListing(@PathVariable int agentId, @RequestBody Listing listing) {
        try {

            return new ResponseEntity<>(service.addListing(agentId, listing), HttpStatus.OK);

        } catch (AgentCommonException e){
            return new ResponseEntity<>(e.getMessage("Agent does not exist"), HttpStatus.NOT_FOUND);

        }catch (ListingCommonException e) {

            return new ResponseEntity<>(e.getMessage("Listing already exists"), HttpStatus.CONFLICT);

        }
    }
	
    @DeleteMapping("/deleteObject/{agentId}")
    public ResponseEntity<?> deleteObject(@PathVariable int agentId, @RequestBody String objectId) {
        try {
            return new ResponseEntity<>(service.deleteListing(agentId, objectId), HttpStatus.OK);

        }catch (ListingCommonException| AgentCommonException e) {
        	
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    


    @PutMapping("/updateListing/{agentId}")
    public ResponseEntity<?> updateDish(@PathVariable int agentId, @RequestBody  Listing listing){

        try {
        	service.updateListing(agentId, listing);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ListingCommonException| AgentCommonException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/viewAllListings")
    public ResponseEntity<?> viewAllListings(){
        List<Agent> menuList = service.viewAllListings();
        return new ResponseEntity<>(menuList,HttpStatus.OK);
    }
    
    
    @GetMapping("/viewAllListingsByAgent/{agentId}")
    public ResponseEntity<?> viewAllListingsByAgent(@PathVariable int agentId){
    	try {
            return new ResponseEntity<>(service.viewAllListingsByAgent(agentId), HttpStatus.OK);
        } catch (AgentCommonException e) {
            return new ResponseEntity<>(e.getMessage("Agent does not exist"),HttpStatus.NOT_FOUND);
        }

    }

	

}
